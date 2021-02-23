package cl.duamit.pharmacy.domain.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Pharmacy {
	private String id;
	private String name;
	private Address address;
	private String phone;
	private LocalDateTime openAt;
	private LocalDateTime closeAt;
	private boolean openNow;
	private Double distanceKmFromOrigin;
}
