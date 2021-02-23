package cl.duamit.pharmacy.infrastructure.providers.rest;

import cl.duamit.pharmacy.infrastructure.providers.rest.model.PharmacyRest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConfigurationProperties(prefix = "pharmacy.turns")
public class PharmacysTurnService extends RestService {
	public List<PharmacyRest> getPharmacys() {
		//this.buildUrl();
		setConverter();
		getHttpHeaders().setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(getHttpHeaders());
		ResponseEntity<List<PharmacyRest>> rateResponse =
			getRestTemplate().exchange(getTmplUrl(),
				HttpMethod.GET,
				requestEntity,
				new ParameterizedTypeReference<List<PharmacyRest>>() {});
		List<PharmacyRest> rates = rateResponse.getBody();
		return rates;
	}

}
