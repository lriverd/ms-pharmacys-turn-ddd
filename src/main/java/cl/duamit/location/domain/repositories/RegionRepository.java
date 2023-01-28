package cl.duamit.location.domain.repositories;

import cl.duamit.location.domain.entities.Comuna;
import cl.duamit.location.domain.entities.Provincia;
import cl.duamit.location.domain.entities.Region;

import java.util.List;

public interface RegionRepository {

	List<Region> getAllRegions(Boolean withProvincias, Boolean withComunas);
	List<Provincia> getProvincias(String regionId, Boolean withComunas);
	List<Comuna> getComunas(String provinciaId);
}
