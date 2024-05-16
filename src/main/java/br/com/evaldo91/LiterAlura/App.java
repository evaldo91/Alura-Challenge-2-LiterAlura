package br.com.evaldo91.LiterAlura;

import br.com.evaldo91.LiterAlura.domain.autor.Autor;
import br.com.evaldo91.LiterAlura.domain.autor.AutorRepository;
import br.com.evaldo91.LiterAlura.domain.autor.DadosAutor;
import br.com.evaldo91.LiterAlura.domain.cadastro.Dados;
import br.com.evaldo91.LiterAlura.domain.livro.DadosLivro;
import br.com.evaldo91.LiterAlura.domain.livro.Livro;
import br.com.evaldo91.LiterAlura.domain.livro.LivrosRepository;
import br.com.evaldo91.LiterAlura.infra.service.ConsumoApi;
import br.com.evaldo91.LiterAlura.infra.service.ConverteDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi api = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private LivrosRepository livrosRepository;
    private AutorRepository autorRepository;


    public App(AutorRepository autorRepository, LivrosRepository livrosRepository) {
        this.livrosRepository = livrosRepository;
        this.autorRepository = autorRepository;
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
        Dados dados = obterDadosLivro();
        motraDado(dados);


    }

    private Dados obterDadosLivro() {
        System.out.println("Digite o nome do livro:");
        var nomeDoLivro = scanner.nextLine().toLowerCase().replace(" ", "+");
        var json = api.obtenerDados("http://gutendex.com/books/?search=" + nomeDoLivro);
        Dados dados = conversor.obterDados(json, Dados.class);
        return dados;

    }

    private void motraDado(Dados dados) {

//--------------------------------- setando dados em variaveis -------------------------
        Optional<DadosLivro> dadosLivro = dados.livros().stream().findFirst();
        if (dadosLivro.isPresent()) {
            var nomeAutor =
                    dadosLivro.get().autores().stream()
                            .map(DadosAutor::nome).limit(1).collect(joining());
            var tituloLivro = dadosLivro.get().titulo();
            var idioma = String.join("", dadosLivro.get().idiomas());
            var downloads = dadosLivro.get().downloads();










//-----------------------------------Imprimir dados ------------------------------------
            System.out.println("\n----- LIVRO -----" +
                    "\nTitulo: " + tituloLivro +
                    "\nAutor: " + nomeAutor +
                    "\nIdioma: " + idioma +
                    "\nDownloads: " + downloads +
                    "\n-----------------\n"

            );

//------------------------------- Criando Lista de livro -------------------------------------

        }
    }
}
