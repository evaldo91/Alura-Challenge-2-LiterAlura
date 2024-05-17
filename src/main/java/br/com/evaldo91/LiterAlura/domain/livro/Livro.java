package br.com.evaldo91.LiterAlura.domain.livro;

import br.com.evaldo91.LiterAlura.domain.autor.Autor;
import br.com.evaldo91.LiterAlura.domain.autor.DadosAutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "livros")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    @ManyToOne()
    private Autor autor;
    private Idioma idioma;
    private int downloads;

    public Livro(Livro dados, Autor autor) {
        this.titulo = dados.titulo;
        setAutor(autor);
        this.idioma = dados.idioma;
        this.downloads = dados.downloads;
    }



    public Livro(DadosLivro dados, Autor autor) {
        this.titulo = dados.titulo();
        setAutor(autor);
        this.idioma = Idioma.fromString(dados.idiomas().getFirst());
        this.downloads = dados.downloads();
    }

    public Livro(DadosLivro dados) {
        this.titulo = dados.titulo();
        this.autor = new Autor(dados.autores());
        this.idioma = Idioma.fromString(dados.idiomas().getFirst());
        this.downloads = dados.downloads();
    }

    public Livro(String titulo, Autor autor, List<String> idiomas, Integer downloads) {
        this.titulo = titulo;
        this.autor = new Autor(autor);
        this.idioma = Idioma.fromString(idiomas.getFirst());
        this.downloads = downloads;
    }

    public Livro(String titulo, List<DadosAutor> autores, List<String> idiomas, Integer downloads) {
        this.titulo = titulo;
        this.autor = new Autor(autor);
        this.idioma = Idioma.fromString(idiomas.getFirst());
        this.downloads = downloads;
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


}