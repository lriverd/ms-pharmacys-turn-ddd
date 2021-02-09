package cl.duamit.pharma.infraestructure.providers.pharmacy;

import cl.duamit.shared.infraestructure.rest.RestService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 28-11-2020
 * @since 1.0.0 - 28-11-2020
 */

@Service
@ConfigurationProperties(prefix = "pharmacy.turns")
public class PharmacyService extends RestService<List, Object> {
    @Override
    protected Class<List> type() {
        return List.class;
    }
}
