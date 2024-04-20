package cl.duamit.pharmacy.infrastructure.providers.rest.minsal;

import cl.duamit.pharmacy.infrastructure.providers.rest.AbstractRestService;
import cl.duamit.pharmacy.infrastructure.providers.rest.model.PharmacyRest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "pharmacy.premises")
@Slf4j
public class PharmacyClient extends AbstractRestService {

	public List<PharmacyRest> getAllPharmacy(){
		getHttpHeaders().setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(getHttpHeaders());
		log.info("Calling pharmacy premises: " + getUrl());
		ResponseEntity<String> rateResponse =
			getRestTemplate().exchange(getUrl(),
				HttpMethod.GET,
				requestEntity,
				String.class);
		log.info("Response: " + rateResponse.getBody());
		ObjectMapper mapper = new ObjectMapper();
		List<PharmacyRest> rates = new ArrayList<>();
		try {
		rates = mapper.readValue(rateResponse.getBody(), new TypeReference<List<PharmacyRest>>() {});
		}catch (Exception e){
			log.error("Error in getAllPharmacy", e);
		}

		return rates;
	}
}
