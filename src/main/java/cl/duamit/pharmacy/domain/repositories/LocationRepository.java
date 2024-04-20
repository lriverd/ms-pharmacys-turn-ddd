package cl.duamit.pharmacy.domain.repositories;

import cl.duamit.pharmacy.domain.entities.Location;

import java.util.List;

public interface LocationRepository {
	List<Location> getLocalities();
	List<Location> getCommunes();
	List<Location> getAllLocations();
}
