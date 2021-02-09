package cl.duamit.pharmacy.api.controller;

import cl.duamit.pharmacy.core.Workflow;
import cl.duamit.pharmacy.service.location.RegionCommuneService;
import cl.duamit.pharmacy.service.location.RegionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/location")
public class LocationController extends AbstractController {
    private static final String LOCATION_REGION = "/region";
    private static final String LOCATION_COMMUNE = "/region/{regionCode}/commune";

    @GetMapping(value = LOCATION_REGION, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getRegionList(HttpServletRequest request) throws Exception {
        return execute(Workflow.init(request)
                .executor(RegionService.class));
    }

    @GetMapping(value = LOCATION_COMMUNE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getCommuneList(@PathVariable("regionCode") String regionCode,
                                                 HttpServletRequest request) throws Exception {
        return execute(Workflow.init(request)
                .executor(RegionCommuneService.class)
                .execute(regionCode));
    }
}
