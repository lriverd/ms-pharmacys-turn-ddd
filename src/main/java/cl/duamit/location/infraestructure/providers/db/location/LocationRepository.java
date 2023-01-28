package cl.duamit.location.infraestructure.providers.db.location;

import cl.duamit.location.domain.entities.Comuna;
import cl.duamit.location.domain.entities.Provincia;
import cl.duamit.location.domain.entities.Region;
import cl.duamit.location.domain.repositories.RegionRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationRepository implements RegionRepository {

	@Autowired
	private RegionMapper regionMapper;

	@Override
	@SneakyThrows
	public List<Region> getAllRegions(Boolean withProvincias, Boolean withComunas) {
		List<Region> regiones = regionMapper.getRegiones();
		if(withProvincias) {
			for (Region r : regiones) {
				r.setProvincias(getProvincias(r.getId(), withComunas));
			}
		}
		return regiones;
	}

	@Override
	public List<Provincia> getProvincias(String regionId, Boolean withComunas) {
		List<Provincia> provincias = regionMapper.getProvincias(regionId);
		if(withComunas){
			for(Provincia p : provincias){
				p.setComunas(getComunas(p.getId()));
			}
		}
		return provincias;
	}

	@Override
	public List<Comuna> getComunas(String provinciaId) {
		return regionMapper.getComunas(provinciaId);
	}
}
