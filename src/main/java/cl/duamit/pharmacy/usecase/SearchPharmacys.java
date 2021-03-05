package cl.duamit.pharmacy.usecase;

import cl.duamit.pharmacy.domain.entities.Address;
import cl.duamit.pharmacy.domain.entities.Coordinates;
import cl.duamit.pharmacy.domain.entities.Pharmacy;
import cl.duamit.pharmacy.domain.repositories.PharmacysTurn;
import cl.duamit.pharmacy.infrastructure.providers.rest.PharmacysTurnService;
import cl.duamit.pharmacy.infrastructure.providers.rest.model.PharmacyRest;
import cl.duamit.pharmacy.usecase.geolocation.GeolocationCalculator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class SearchPharmacys {

	private PharmacysTurn pharmacysTurnService;

	@SneakyThrows
	public List<Pharmacy> getByGeolocation(Coordinates coordinates, Double maxMtRatio) {
		List<Pharmacy> pharmacyList = new ArrayList<>();
		List<Pharmacy> pharmacyOpenList = pharmacysTurnService.getOpenPharmacy();

		pharmacyOpenList.forEach(pr -> {
			double distance = GeolocationCalculator.distanceBetwenPoints(coordinates, pr.getAddress().getCoordinates());
			if (distance <= maxMtRatio) {
				pr.setDistanceKmFromOrigin(distance / 1000d);
				pharmacyList.add(pr);
			}
		});

		pharmacyList.sort(Comparator.comparing(Pharmacy::getDistanceKmFromOrigin));

		return pharmacyList;
	}

	@Autowired
	public void setPharmacysTurnService(PharmacysTurnService pharmacysTurnService) {
		this.pharmacysTurnService = pharmacysTurnService;
	}
}
