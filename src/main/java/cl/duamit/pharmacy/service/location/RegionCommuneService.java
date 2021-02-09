package cl.duamit.pharmacy.service.location;

import cl.duamit.pharmacy.model.location.Region;
import cl.duamit.pharmacy.service.AbstractService;
import cl.duamit.pharmacy.repository.requirements.RequirimentsLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionCommuneService extends AbstractService<Region, String> {

    private RequirimentsLocationRepository requirimentsLocationRepository;

    @Override
    public Region execute(String regionCode) {
        Region region = requirimentsLocationRepository.getRegion(regionCode);
        region.setCommunes(requirimentsLocationRepository.getCommunes(regionCode));

        return region;
    }

    @Autowired
    public void setRequirimentsLocationRepository(RequirimentsLocationRepository requirimentsLocationRepository) {
        this.requirimentsLocationRepository = requirimentsLocationRepository;
    }
}
