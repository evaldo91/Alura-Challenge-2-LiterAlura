package br.com.evaldo91.LiterAlura.app;

import br.com.evaldo91.LiterAlura.domain.Dados;
import br.com.evaldo91.LiterAlura.domain.autor.Autor;
import br.com.evaldo91.LiterAlura.domain.autor.AutorRepository;
import br.com.evaldo91.LiterAlura.domain.autor.DadosAutor;
import br.com.evaldo91.LiterAlura.domain.livro.DadosLivro;
import br.com.evaldo91.LiterAlura.domain.livro.Livro;
import br.com.evaldo91.LiterAlura.domain.livro.LivroRepository;
import br.com.evaldo91.LiterAlura.infra.service.ConsumoApi;
import br.com.evaldo91.LiterAlura.infra.service.ConverteDados;

import java.util.Optional;
import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApi api = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    String menu = """
                    Selecione o número da opção desejada:
                    1 - Busca livro por título
                    0 - Sair
                    """;

    public App(LivroRepository livrorepository, AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livrorepository;

    }


    public void start() {

        var opcao = -1;
        while (opcao !=0){
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroNaWeb();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }


    }

    private void buscarLivroNaWeb() {
        System.out.println("Digite o nome do livro:");
        String busca = scanner.nextLine().toLowerCase().replace(" ", "+");
        autorJaCadastrado(obterDadosLivro(busca));
    }

    private Dados obterDadosLivro(String busca) {
        String json = api.obtenerDados("http://gutendex.com/books/?search=" + busca);
        return conversor.obterDados(json, Dados.class);
    }

    public void mostraDadoLivro(Livro livro){
        var mostraLivro =        "----- LIVRO -----" +
                "\nTitulo: " + livro.getTitulo() +
                "\nAutor: " + livro.getAutor().getNome() +
                "\nIdioma: " + livro.getIdioma() +
                "\nNumero de downloads: " + livro.getDownloads() +
                "\n-----------------\n";
        System.out.println(mostraLivro);
    }
    public void salvaDados(DadosLivro dados){
        Autor autor = new Autor(dados.autores().getFirst());
        Livro livro = new Livro(dados, autor);

        livroRepository.save(livro);
        System.out.println("\n Livro Cadastrado \n");


    }
    public void autorJaCadastrado(Dados dados){
        Optional<Autor> autor = autorRepository.findByNome(dados.livros().getFirst().autores().getFirst().nome());
        if (autor.isPresent()){
            var autorDb = autor.get();
            Livro livro = new Livro(dados.livros().getFirst(), autorDb);
            livroRepository.save(livro);
            System.out.println(autorDb.getNome());
        }else {
            salvaDados(dados.livros().getLast());
        }
        System.out.println("Cadastro O!");

    }
}
