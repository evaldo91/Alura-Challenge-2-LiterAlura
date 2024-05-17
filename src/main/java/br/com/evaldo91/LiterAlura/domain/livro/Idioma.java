package br.com.evaldo91.LiterAlura.domain.livro;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
public enum Idioma {
    ZH("zh", "Chinês"),
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


    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idioma.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Nenhum livro encontrado em " + text);
    }
    public static Idioma fromPortugues(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaEmPortugues.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Nenhum livro encontrado em " + text);
    }
}
