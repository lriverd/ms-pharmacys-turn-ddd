package cl.duamit.location.entitie;

import lombok.Data;

import java.util.List;

@Data
public class Region extends cl.duamit.shared.entitie.Region{
    private List<Province> provinces;

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }
}
