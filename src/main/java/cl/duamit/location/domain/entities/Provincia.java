package cl.duamit.location.domain.entities;

import lombok.Data;

import java.util.List;

@Data
public class Provincia {
	private String id;
	private String name;
	private List<Comuna> comunas;
}
