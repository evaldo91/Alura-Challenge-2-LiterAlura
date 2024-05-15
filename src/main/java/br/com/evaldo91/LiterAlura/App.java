package br.com.evaldo91.LiterAlura;

import br.com.evaldo91.LiterAlura.domain.autor.Autor;
import br.com.evaldo91.LiterAlura.domain.cadastro.Dados;
import br.com.evaldo91.LiterAlura.domain.livro.DadosLivro;
import br.com.evaldo91.LiterAlura.domain.livro.Livro;
import br.com.evaldo91.LiterAlura.domain.livro.LivrosRepository;
import br.com.evaldo91.LiterAlura.infra.service.ConsumoApi;
import br.com.evaldo91.LiterAlura.infra.service.ConverteDados;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApi api = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final LivrosRepository repository;

    public App(LivrosRepository repository) {
        this.repository = repository;
    }

    public void iniciar() {
        int opcao = -1;
        while (opcao != 0) {
            String menu = """
                    Selecione o número da opção desejada:
                    1 - Busca livro por título
                    0 - Sair
                    """;
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

    private void obterDadosLivro(String nomeDoLivro) {
        String endereco = "http://gutendex.com/books/?search=" + nomeDoLivro;
        System.out.println(endereco);
        String json = api.obtenerDados(endereco);
        Dados dados = conversor.obterDados(json, Dados.class);

        Optional<DadosLivro> livroAdd = dados.livros().stream().findFirst();

        livroAdd.ifPresent(dadosLivro -> {
            Livro livro = new Livro(dadosLivro);
//            Autor autor = new Autor(livro.getAutor().getNome());
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor().getNome());
            System.out.println("Idioma: " + livro.getIdioma());
            System.out.println("Downloads: " + livro.getDownloads());

            try {
                repository.save(livro);
            } catch (DataIntegrityViolationException e) {
//                System.out.println("Erro: Livro já adicionado.");
            }
        });
    }
}