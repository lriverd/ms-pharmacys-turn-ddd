package cl.duamit.pharmacy.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class Pharmacy {
	private String id;
	private String name;
	private Address address;
	private String phone;
	private LocalDate dia;
	private LocalTime openAt;
	private LocalTime closeAt;
	private boolean openNow;
	private Double distanceKmFromOrigin;
}
