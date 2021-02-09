package cl.duamit.pharma.entitie;

import cl.duamit.shared.entitie.Coordinates;
import lombok.Data;

@Data
public class Pharmacy {
    private String id;
    private String name;
    private String addresse;
    private String phone;
    private Coordinates coordinates;
}
