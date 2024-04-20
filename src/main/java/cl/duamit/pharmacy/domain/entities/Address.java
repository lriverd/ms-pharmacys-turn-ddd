package cl.duamit.pharmacy.domain.entities;

import lombok.Data;

@Data
public class Address {
	private String address;
	private Location locality;
	private Location commune;
	private Coordinates coordinates;
}
