package cl.duamit.pharmacy.text;

import cl.duamit.pharmacy.type.Direction;

public class Filler {
    public String fillCollect(String s, String character, String max, String direccion) {
        return fill(s, character.charAt(0), Integer.valueOf(max),  Direction.valueOf(direccion));
    }

    public String fill(String s, char character, int max, Direction direction) {
        if (s == null) {
            return null;
        }
        if (s.length() >= max) {
            return s.substring(0, max);
        } else {
            StringBuilder strBuffer = new StringBuilder(s);
            while (strBuffer.length() < max) {
                if (Direction.LEFT.equals(direction)) {
                    strBuffer.insert(0, character);
                } else {
                    strBuffer.append(character);
                }
            }
            return strBuffer.toString();
        }
    }

    public static Filler build() {
        return new Filler();
    }
}
