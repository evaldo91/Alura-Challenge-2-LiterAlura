package br.com.evaldo91.LiterAlura.domain.autor;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") int nascimento,
        @JsonAlias("death_year") int falecimento
) {

}