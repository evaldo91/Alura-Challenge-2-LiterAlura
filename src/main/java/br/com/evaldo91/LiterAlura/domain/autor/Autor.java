package br.com.evaldo91.LiterAlura.domain.autor;

import br.com.evaldo91.LiterAlura.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(of = "id")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private int nascimento;
    private int falecimento;

    @Embedded
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor(List<DadosAutor> dados){
        this.nome = dados.getFirst().nome();
        this.nascimento = dados.getFirst().nascimento();
        this.falecimento = dados.getFirst().falecimento();
        this.livros = new ArrayList<>();

    }



    public void setLivros(Livro livros) {
        livros.setAutor(this);

    }


}
