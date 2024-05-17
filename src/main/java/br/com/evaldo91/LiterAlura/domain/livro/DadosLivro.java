package br.com.evaldo91.LiterAlura.domain.livro;

import br.com.evaldo91.LiterAlura.domain.autor.DadosAutor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
//        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DadosAutor> autores,
        @JsonAlias("languages") List<String> idiomas,
//        @JsonAlias("copyright") String copyright,
        @JsonAlias("download_count") Integer downloads
) {


}