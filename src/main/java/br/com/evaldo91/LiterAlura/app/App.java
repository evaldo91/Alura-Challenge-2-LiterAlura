package br.com.evaldo91.LiterAlura.app;

import br.com.evaldo91.LiterAlura.domain.Dados;
import br.com.evaldo91.LiterAlura.domain.autor.Autor;
import br.com.evaldo91.LiterAlura.domain.autor.AutorRepository;
import br.com.evaldo91.LiterAlura.domain.livro.Idioma;
import br.com.evaldo91.LiterAlura.domain.livro.Livro;
import br.com.evaldo91.LiterAlura.domain.livro.LivroRepository;
import br.com.evaldo91.LiterAlura.infra.service.ConsumoApi;
import br.com.evaldo91.LiterAlura.infra.service.ConverteDados;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApi api = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    String menu = """
            \nSelecione o número da opção desejada:
            1 - Busca livro por título
            2 - Lista livros registrado
            3 - Lista autores registado
            4 - Lista livro com base no idioma
            \n
            \n
            0 - Sair
            \n""";

    public App(LivroRepository livrorepository, AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livrorepository;

    }

    public List<Idioma> criarListaTodosIdiomas() {
        List<Idioma> todosIdiomas = new ArrayList<>();
        for (Idioma idioma : Idioma.values()) {
            List<Livro> livroList = livroRepository.findByIdioma(idioma);
            if (livroList != null && !livroList.isEmpty()) {
                todosIdiomas.add(idioma);
            }
        }
        return todosIdiomas;
    }
    public void start() {

        var opcao = -1;
        while (opcao != 0) {
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroNaWeb();
                    break;
                case 2:
                    listaLivrosRegistado();
                    break;
                case 3:
                    listaAutoresRegistrado();
                    break;
                case 4:
                    listaLivroPorIdioma();
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
        try {
            validarDados(obterDadosLivro(busca));
        } catch (NoSuchElementException e) {
            System.out.println("\nLivro não encontrado!\n");

        }


    }

    private void listaLivrosRegistado() {
        List<Livro> livroList = livroRepository.findAll();
        livroList.forEach(this::mostraDadoLivro);

    }

    private void listaAutoresRegistrado() {
        List<Autor> autorList = autorRepository.findAll();
        autorList.forEach(this::mostraDadosAutor);

    }

    private void listaLivroPorIdioma() {
        List<Idioma> todosIdiomas = criarListaTodosIdiomas();

        // Exibindo a lista de todos os idiomas
        System.out.println("Todos os idiomas cadastrados:");
        for (Idioma idioma : todosIdiomas) {
            System.out.println(idioma.getIdiomaEmPortugues() + " (" + idioma.getIdioma() + ")");
        }
        try {
            System.out.println("Escolha o idioma para pesquisa digitando a abreviação dele:"
            );

            var busca = scanner.nextLine().toLowerCase();

            Idioma idioma = Idioma.fromString(busca);

            List<Livro> livroList = livroRepository.findByIdioma(idioma);
            if (livroList != null && !livroList.isEmpty()) {
                System.out.println("\nLivros em " + busca + "\n");
                livroList.forEach(this::mostraDadoLivro);
            } else {
                System.out.println("\nNenhum livro encontrado no idioma " + busca+"\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }


    }

    private Dados obterDadosLivro(String busca) {
        String json = api.obtenerDados("http://gutendex.com/books/?search=" + busca);
        return conversor.obterDados(json, Dados.class);
    }

    public void mostraDadoLivro(Livro livro) {
        var mostraLivro = "----- LIVRO -----" +
                "\nTitulo: " + livro.getTitulo() +
                "\nAutor: " + livro.getAutor().getNome() +
                "\nIdioma: " + livro.getIdioma() +
                "\nNumero de downloads: " + livro.getDownloads() +
                "\n-----------------\n";
        System.out.println(mostraLivro);
    }

    private void mostraDadosAutor(Autor autor) {
        var motraAutor = "----- AUTOR -----" +
                "\nNome: " + autor.getNome() +
                "\nData de nascimento: " + autor.getNascimento() +
                "\nData de falecimento: " + autor.getFalecimento() +
                "\n-----------------\n";
        System.out.println(motraAutor);

    }

    public void validarDados(Dados dados) {

        Livro livroEntrada = new Livro(dados.livros().getFirst());
        Autor autorEntrada = new Autor(livroEntrada.getAutor());


        Optional<Autor> autorDbOptional = autorRepository.findByNome(autorEntrada.getNome());
        Optional<Livro> livroDbOptional = livroRepository.findByTituloContainingIgnoreCase(livroEntrada.getTitulo());

        if (livroDbOptional.isPresent()) {
            Livro livroDb = livroDbOptional.get();
            if (livroEntrada.getTitulo().equalsIgnoreCase(livroDb.getTitulo())) {
                mostraDadoLivro(livroDb);
                return;
            }
        }

        if (autorDbOptional.isPresent()) {
            Autor autorDb = autorDbOptional.get();
            System.out.println("Salvando novo livro para o autor(a) " + autorDb.getNome());
            Livro novoLivro = new Livro(livroEntrada, autorDb);
            mostraDadoLivro(novoLivro);
            livroRepository.save(novoLivro);
        } else {

            System.out.println("\nNovo livro e novo autor cadastrados\n");

            Livro novoLivro = new Livro(livroEntrada, autorEntrada);
            mostraDadoLivro(novoLivro);
            autorRepository.save(autorEntrada);
            livroRepository.save(novoLivro);
        }

    }
}