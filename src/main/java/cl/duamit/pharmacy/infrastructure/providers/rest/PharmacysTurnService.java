package cl.duamit.pharmacy.infrastructure.providers.rest;

import cl.duamit.pharmacy.domain.entities.Pharmacy;
import cl.duamit.pharmacy.domain.repositories.PharmacysTurn;
import cl.duamit.pharmacy.infrastructure.providers.rest.mapper.PharmacyRestMapper;
import cl.duamit.pharmacy.infrastructure.providers.rest.model.PharmacyRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@ConfigurationProperties(prefix = "pharmacy.turns")
public class PharmacysTurnService extends RestService implements PharmacysTurn {

	@Autowired
	PharmacyRestMapper pharmacyRestMapper;

	public List<PharmacyRest> getRestPharmacy() {
		setConverter();
		getHttpHeaders().setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(getHttpHeaders());
		ResponseEntity<List<PharmacyRest>> rateResponse =
			getRestTemplate().exchange(getTmplUrl(),
				HttpMethod.GET,
				requestEntity,
				new ParameterizedTypeReference<List<PharmacyRest>>() {
				});
		List<PharmacyRest> rates = rateResponse.getBody();
		return rates;
	}

	@Override
	public List<Pharmacy> getOpenPharmacy() {
		List<Pharmacy> pharmacyList = new ArrayList<>();
		getRestPharmacy().stream().forEach(prl -> {
			try {
				pharmacyList.add(pharmacyRestMapper.toPharmacy(prl));
			} catch (Exception e) {
				log.error("Cannot parse rest pharmacy", e);
			}
		});
		return pharmacyList;
	}
}
