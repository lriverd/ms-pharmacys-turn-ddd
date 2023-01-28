package cl.duamit.location.domain.entities;

import lombok.Data;

import java.util.List;

@Data
public class Region {
	private String id;
	private String name;
	private List<Provincia> provincias;
}
