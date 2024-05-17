package br.com.evaldo91.LiterAlura.domain.livro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Enumeração representando idiomas.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Idioma {
    CH("ch", "Chinês"),
    DA("da", "Dinamarquês"),
    NL("nl", "Holandês"),
    EN("en", "Inglês"),
    EO("eo", "Esperanto"),
    FI("fi", "Finlandês"),
    FR("fr", "Francês"),
    DE("de", "Alemão"),
    EL("el", "Grego"),
    HU("hu", "Húngaro"),
    IT("it", "Italiano"),
    LA("la", "Latim"),
    PT("pt", "Português"),
    ES("es", "Espanhol"),
    SV("sv", "Sueco"),
    TL("tl", "Tagalo");

    private String idioma;
    private String idiomaEmPortugues;

    /**
     * Retorna o Idioma correspondente ao texto fornecido.
     *
     * @param text O texto a ser convertido em Idioma.
     * @return O Idioma correspondente ao texto.
     * @throws IllegalArgumentException Se nenhum Idioma corresponder ao texto fornecido.
     */
    public static Idioma fromString(String text) {
        if (text != null) {
            for (Idioma idioma : Idioma.values()) {
                if (idioma.idioma.equalsIgnoreCase(text)) {
                    return idioma;
                }
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado com o código: " + text);
    }

    /**
     * Retorna o Idioma correspondente ao texto em português fornecido.
     *
     * @param text O texto em português a ser convertido em Idioma.
     * @return O Idioma correspondente ao texto em português.
     * @throws IllegalArgumentException Se nenhum Idioma corresponder ao texto em português fornecido.
     */
    public static Idioma fromPortugues(String text) {
        if (text != null) {
            for (Idioma idioma : Idioma.values()) {
                if (idioma.idiomaEmPortugues.equalsIgnoreCase(text)) {
                    return idioma;
                }
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado com o nome em português: " + text);
    }


}