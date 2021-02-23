package cl.duamit.pharmacy.domain.entities;

import lombok.Data;

@Data
public class Address {
	private String address;
	private String locality;
	private Coordinates coordinates;
}
