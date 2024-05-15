package br.com.evaldo91.LiterAlura.domain.autor;

import br.com.evaldo91.LiterAlura.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private int nascimento;
    private int falecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor(DadosAutor dados){
        this.nome = dados.nome();
        this.nascimento = dados.nascimento();
        this.falecimento = dados.falecimento();
        this.livros = new ArrayList<>();

    }


    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
    }
}
