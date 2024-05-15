package br.com.evaldo91.LiterAlura.domain.cadastro;

import br.com.evaldo91.LiterAlura.domain.livro.Livro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cadastro {
    private List<Livro> livros;


}
