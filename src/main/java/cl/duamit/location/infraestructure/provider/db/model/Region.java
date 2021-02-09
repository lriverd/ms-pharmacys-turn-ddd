package cl.duamit.location.infraestructure.provider.db.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
public class Region {
    @Id
    private String id;

    @NotEmpty
    private String shortName;

    @NotEmpty
    private String longName;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Province> provinces;
}
