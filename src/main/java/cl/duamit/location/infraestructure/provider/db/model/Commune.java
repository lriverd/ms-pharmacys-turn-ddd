package cl.duamit.location.infraestructure.provider.db.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Commune {

    @Id
    private String id;

    @NotEmpty
    private String name;
}
