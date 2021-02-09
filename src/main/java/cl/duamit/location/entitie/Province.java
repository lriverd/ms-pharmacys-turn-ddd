package cl.duamit.location.entitie;

import lombok.Data;

import java.util.List;

@Data
public class Province extends cl.duamit.shared.entitie.Province {
    private List<Commune> communes;

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }
}
