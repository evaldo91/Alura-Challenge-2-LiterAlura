package br.com.evaldo91.LiterAlura.app;

import br.com.evaldo91.LiterAlura.domain.Dados;
import br.com.evaldo91.LiterAlura.domain.autor.Autor;
import br.com.evaldo91.LiterAlura.domain.autor.AutorRepository;
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
        obterDadosLivro(busca);
    }

    private void obterDadosLivro(String busca) {
        String json = api.obtenerDados("http://gutendex.com/books/?search=" + busca);
        Dados dados = conversor.obterDados(json, Dados.class);
        Autor autor = new Autor(dados.livros().getFirst().autores());
        Livro livro = new Livro(dados.livros().getFirst(), autor);

        Optional<Autor> autorDb = autorRepository.findByNome(autor.getNome());
        System.out.println(autorDb.isPresent());

        if (autorDb.isPresent()){
            var autorEncontrado = autorDb.get();
            autorEncontrado.setLivros(livro);
            livroRepository.save(livro);




        }else{

            livroRepository.save(livro);
            autorRepository.save(autor);


        }


        var mostraLivro =        "----- LIVRO -----" +
                "\nTitulo: " + livro.getTitulo() +
                "\nAutor: " + autor.getNome() +
                "\nIdioma: " + livro.getIdioma() +
                "\nNumero de downloads: " + livro.getDownloads() +
                "\n-----------------\n";

        System.out.println(mostraLivro);





    }
}
