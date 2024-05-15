package br.com.evaldo91.LiterAlura.domain.cadastro;



import br.com.evaldo91.LiterAlura.domain.livro.DadosLivro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(
        @JsonAlias("results") List<DadosLivro> livros
        ) {
}
