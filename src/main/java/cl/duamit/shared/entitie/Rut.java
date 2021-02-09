package cl.duamit.shared.entitie;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 03-08-2020
 * @since 1.0.0 - 03-08-2020
 */
public class Rut {

    private Integer mantisa;
    private char dv;

    public Rut(Integer mantisa, char dv) {
        this.mantisa = mantisa;
        this.dv = dv;
    }

    public Integer getMantisa() {
        return mantisa;
    }

    public void setMantisa(Integer mantisa) {
        this.mantisa = mantisa;
    }

    public char getDv() {
        return dv;
    }

    public void setDv(char dv) {
        this.dv = dv;
    }

    public String toPlainString(){return String.format("%d%c", mantisa, dv);}

    @Override
    public String toString() {
        return String.format("%d-%c", mantisa, dv);
    }
}
