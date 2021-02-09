package cl.duamit.pharmacy.api.controller;

import cl.duamit.pharmacy.core.Workflow;
import cl.duamit.bank.usecase.BankService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BankController extends AbstractController {
    private static final String BANK = "/bank";

    @GetMapping(value = BANK, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getRegionList(HttpServletRequest request) throws Exception {
        return execute(Workflow.init(request)
                .executor(BankService.class));
    }
}
