package cl.duamit.location.usecases;

import cl.duamit.location.domain.entities.Comuna;
import cl.duamit.location.domain.entities.Region;
import cl.duamit.location.domain.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionesUseCase {

	@Autowired
	private RegionRepository regionRepository;

	public List<Region> getRegiones(){
		return regionRepository.getAllRegions(true, true);
	}

}
