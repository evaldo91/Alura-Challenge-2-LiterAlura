package br.com.evaldo91.LiterAlura.domain.livro;

import br.com.evaldo91.LiterAlura.domain.autor.Autor;
import br.com.evaldo91.LiterAlura.domain.autor.AutorRepository;
import br.com.evaldo91.LiterAlura.domain.autor.DadosAutor;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    @ManyToOne()
    private Autor autor;
    private Idioma idioma;
    private int downloads;

    public Livro() {}



    public Livro(DadosLivro dados, Autor autor) {
        this.titulo = dados.titulo();
        setAutor(autor);
        this.idioma = Idioma.fromString(dados.idiomas().getFirst());
        this.downloads = dados.downloads();
    }


    public String getTitulo() {
        return titulo;
    }


    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }


    public int getDownloads() {
        return downloads;
    }


}