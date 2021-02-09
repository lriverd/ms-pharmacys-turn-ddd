package cl.duamit.pharmacy.service.location;

import cl.duamit.pharmacy.model.location.Region;
import cl.duamit.pharmacy.service.AbstractService;
import cl.duamit.pharmacy.repository.requirements.RequirimentsLocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegionService extends AbstractService<List<Region>, Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionService.class);

    private RequirimentsLocationRepository requirimentsLocationRepository;

    private List<Region> regions;

    /**
     * Initializing bank list on init app
     */
    @PostConstruct
    public void init(){
        LOGGER.info("Init Regions, loading regions ...");
        regions = new ArrayList<>();
        loadRegions();
    }

    @Override
    public List<Region> execute() {
        if(regions.isEmpty()){
            loadRegions();
        }
        return regions;
    }

    private void loadRegions(){
        try {
            regions = requirimentsLocationRepository.getRegions();
        }catch(Exception e){
            LOGGER.error("Error loading regions, this load will be done later");
        }
    }

    @Autowired
    public void setRequirimentsLocationRepository(RequirimentsLocationRepository requirimentsLocationRepository) {
        this.requirimentsLocationRepository = requirimentsLocationRepository;
    }
}
