package cl.duamit.bank.entitie;

import cl.duamit.shared.entitie.Rut;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 03-08-2020
 * @since 1.0.0 - 03-08-2020
 */
public class Bank {
    private String code;
    private String name;
    @JsonIgnore
    private Rut rut;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rut getRut() {
        return rut;
    }

    public void setRut(Rut rut) {
        this.rut = rut;
    }
}
