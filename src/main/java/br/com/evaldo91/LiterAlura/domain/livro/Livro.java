package br.com.evaldo91.LiterAlura.domain.livro;

import br.com.evaldo91.LiterAlura.domain.autor.Autor;
import br.com.evaldo91.LiterAlura.domain.autor.DadosAutor;
import br.com.evaldo91.LiterAlura.domain.cadastro.Dados;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livros")
public class Livro implements Comparable<Livro> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Setter
    @ManyToOne(cascade=CascadeType.PERSIST)
    private DadosAutor autor;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private int downloads;

    public Livro(Dados dados) {
        this.autor = dados.livros().get(1).autores();
        this.titulo = dados.livros().get(1).titulo();
//        this.idioma = dados.livros().get(1).idiomas();
        this.downloads =  dados.livros().get(1).downloads();
    }


    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idioma=" + idioma +
                ", downloads=" + downloads +
                '}';
    }

    public Livro(DadosLivro dados, Autor autor) {
        this.titulo = dados.titulo();
//        this.autor = new Autor(dados.autores().getFirst());
        this.idioma = Idioma.fromString(dados.idiomas().stream()
                .limit(1)
                .collect(Collectors.joining()));
        this.downloads = dados.downloads();




    }

    public Livro(DadosLivro dadosLivro) {
    }

    @Override
    public int compareTo(Livro o) {
        return 0;
    }
}
