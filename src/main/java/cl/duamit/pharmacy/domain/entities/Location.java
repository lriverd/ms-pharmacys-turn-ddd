package cl.duamit.pharmacy.domain.entities;

import cl.duamit.pharmacy.domain.valueobjects.LocationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {
	private String id;
	private String name;
	private LocationType type;
}
