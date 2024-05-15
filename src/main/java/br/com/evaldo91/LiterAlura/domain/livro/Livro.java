package br.com.evaldo91.LiterAlura.domain.livro;

import br.com.evaldo91.LiterAlura.domain.autor.Autor;
import br.com.evaldo91.LiterAlura.domain.autor.DadosAutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.List;

import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private int downloads;


    public Livro(DadosLivro dados) {
        this.titulo = dados.titulo();
//        this.autor = new Autor(dados.autores().getFirst());
        this.idioma = Idioma.fromString(dados.idiomas().stream()
                .limit(1).collect(Collectors.joining()));
        this.downloads = dados.downloads();




    }
}
