package br.com.evaldo91.LiterAlura.domain.autor;

import br.com.evaldo91.LiterAlura.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(unique = true)
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livro> livro = new ArrayList<>();



    public Autor(List<DadosAutor> dados) {
        this.nome = dados.getFirst().nome();
        this.nascimento = dados.getFirst().nascimento();
        this.falecimento = dados.getFirst().falecimento();
    }


    public Autor(Autor dados) {
        this.nome = dados.nome;
        this.nascimento = dados.nascimento;
        this.falecimento = dados.falecimento;
    }
}