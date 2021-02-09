package cl.duamit.pharmacy.api.controller;

import cl.duamit.pharmacy.core.Workflow;
import cl.duamit.pharmacy.model.Person;
import cl.duamit.pharmacy.model.requirement.Requirement;
import cl.duamit.pharmacy.parser.RutParser;
import cl.duamit.pharmacy.service.PersonalInfoService;
import cl.duamit.pharmacy.service.requirement.RequirementExecutorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 04-08-2020
 * @since 1.0.0 - 04-08-2020
 */
@RestController
@RequestMapping("/pharmacy")
public class PharmacyController extends AbstractController {

    private static final String BY_COMMUNE = "/commune/{communeId}";
    private static final String BY_REGION = "/region/{regionId}";
    private static final String BY_POSITION = "/position";

    @GetMapping(value = BY_COMMUNE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getValidUser(@PathParam(value = "communeId") String communeId,
                                               HttpServletRequest request) throws Exception {

        return execute(Workflow.init(request)
                .executor(PersonalInfoService.class)
                //.errors(errors)
                .execute(communeId));
    }


}
