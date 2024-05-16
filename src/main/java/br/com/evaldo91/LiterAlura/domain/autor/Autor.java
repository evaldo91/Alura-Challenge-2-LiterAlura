package br.com.evaldo91.LiterAlura.domain.autor;

import br.com.evaldo91.LiterAlura.domain.livro.DadosLivro;
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

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nascimento=" + nascimento +
                ", falecimento=" + falecimento +
                ", livros=" + livros +
                '}';
    }

    public Autor(Livro livro) {
        this.nome = livro.getAutor().getNome();
        this.nascimento = livro.getAutor().getNascimento();
        this.falecimento = livro.getAutor().getFalecimento();
        this.livros = new ArrayList<>();

    }

    public Autor(DadosAutor dadosAutor) {
    }


    public void adicionarLivro(List<Livro> livros) {
        livros.forEach(l -> l.setAutor(this));
        this.livros = livros;
    }

}