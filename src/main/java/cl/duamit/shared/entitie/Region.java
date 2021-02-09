package cl.duamit.shared.entitie;

import lombok.Data;

@Data
public class Region {
    private String id;
    private String longName;
    private String shortName;
    private Integer geolocation;
}
