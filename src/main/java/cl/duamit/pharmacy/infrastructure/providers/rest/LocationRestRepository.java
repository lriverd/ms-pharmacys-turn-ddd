package cl.duamit.pharmacy.infrastructure.providers.rest;

import cl.duamit.pharmacy.domain.entities.Location;
import cl.duamit.pharmacy.domain.repositories.LocationRepository;
import cl.duamit.pharmacy.domain.valueobjects.LocationType;
import cl.duamit.pharmacy.infrastructure.providers.rest.minsal.PharmacyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LocationRestRepository implements LocationRepository {

	@Autowired
	private PharmacyClient pharmacyClient;

	@Override
	public List<Location> getLocalities() {
		return getAllLocations().stream().filter(location -> LocationType.LOCALITY.equals(location.getType())).collect(Collectors.toList());
	}

	@Override
	public List<Location> getCommunes() {
		return getAllLocations().stream().filter(location -> LocationType.COMMUNE.equals(location.getType())).collect(Collectors.toList());
	}



	@Override
	public List<Location> getAllLocations() {
		Map<String, Location> locs = new HashMap<>();
		pharmacyClient.getAllPharmacy().forEach(ph -> {
			Location locality= Location.builder()
				.id(ph.getFkLocalidad())
				.name(ph.getLocalidadNombre())
				.type(LocationType.LOCALITY)
				.build();
			Location commune= Location.builder()
				.id(ph.getFkComuna())
				.name(ph.getComunaNombre())
				.type(LocationType.COMMUNE)
				.build();

			locs.put("loc".concat(ph.getFkLocalidad()), locality);
			locs.put("com".concat(ph.getFkComuna()), commune);
		});
		return new ArrayList<Location>(locs.values());
	}
}
