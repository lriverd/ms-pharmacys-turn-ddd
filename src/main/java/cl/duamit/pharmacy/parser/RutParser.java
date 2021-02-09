package cl.duamit.pharmacy.parser;

import cl.duamit.shared.entitie.Rut;

import java.util.Objects;

public class RutParser {

    public static RutParser build() {
        return new RutParser();
    }

    public Rut parse(String s) throws NumberFormatException {
        if (Objects.toString(s, "").isEmpty()) {
            return null;
        }
        String rut = s.toUpperCase().replace(".", "").replace("-", "");
        return new Rut(Integer.parseInt(rut.substring(0, rut.length() - 1)), rut.substring(rut.length() - 1).charAt(0));

    }
}
