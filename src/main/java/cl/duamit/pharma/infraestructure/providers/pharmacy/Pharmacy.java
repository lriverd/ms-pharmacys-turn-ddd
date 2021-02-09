package cl.duamit.pharma.infraestructure.providers.pharmacy;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pharmacy {
    private String fecha;
    private String localId;
    private String fkRegion;
    private String fkComuna;
    private String fkLocalidad;
    private String localNombre;
    private String comunaNombre;
    private String localidadNombre;
    private String localDireccion;
    private String funcionamientoHoraApertura;
    private String funcionamientoHoraCierre;
    private String localTelefono;
    private String localLat;
    private String localLng;
    private String funcionamientoDia;

}
