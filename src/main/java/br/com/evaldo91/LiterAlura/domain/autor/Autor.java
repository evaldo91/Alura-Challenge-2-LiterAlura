package br.com.evaldo91.LiterAlura.domain.autor;

import br.com.evaldo91.LiterAlura.domain.livro.Livro;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;




@Entity
@Table (name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int nascimento;
    private int falecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.PERSIST)
    private List<Livro> livro = new ArrayList<>();

    public Autor(){}

    public Autor(DadosAutor dados){
        this.nome = dados.nome();
        this.nascimento = dados.nascimento();
        this.falecimento = dados.falecimento();


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNascimento() {
        return nascimento;
    }

    public void setNascimento(int nascimento) {
        this.nascimento = nascimento;
    }

    public int getFalecimento() {
        return falecimento;
    }

    public void setFalecimento(int falecimento) {
        this.falecimento = falecimento;
    }







    public void setLivros(Livro livro) {
    }
}
