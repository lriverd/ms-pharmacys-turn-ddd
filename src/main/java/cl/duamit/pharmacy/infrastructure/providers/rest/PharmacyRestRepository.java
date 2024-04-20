package cl.duamit.pharmacy.infrastructure.providers.rest;

import cl.duamit.pharmacy.domain.entities.Location;
import cl.duamit.pharmacy.domain.entities.Pharmacy;
import cl.duamit.pharmacy.domain.repositories.PharmacyRepository;
import cl.duamit.pharmacy.infrastructure.providers.rest.farmanet.PharmacyTurnClient;
import cl.duamit.pharmacy.infrastructure.providers.rest.mapper.PharmacyRestMapper;
import cl.duamit.pharmacy.infrastructure.providers.rest.minsal.PharmacyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PharmacyRestRepository implements PharmacyRepository {

	@Autowired
	PharmacyRestMapper pharmacyRestMapper;
	@Autowired
	private PharmacyTurnClient pharmacyTurnClient;
	@Autowired
	private PharmacyClient pharmacyClient;

	@Override
	public List<Pharmacy> getPharmacy() {
		List<Pharmacy> pharmacyList = new ArrayList<>();
		pharmacyClient.getAllPharmacy().stream().forEach(prl -> {
			try {
				pharmacyList.add(pharmacyRestMapper.toPharmacy(prl));
			} catch (Exception e) {
				log.error("Cannot parse rest pharmacy", e);
			}
		});
		return pharmacyList;
	}

	@Override
	public List<Pharmacy> getPharmacyByLocation(Location location) {
		List<Pharmacy> pharmacyList = new ArrayList<>();
		pharmacyClient.getAllPharmacy().stream()
			.filter(x -> location.getId().equals(x.getFkRegion()) || location.getId().equals(x.getFkLocalidad()) || location.getId().equals(x.getFkComuna()))
			.forEach(prl -> {
				try {
					pharmacyList.add(pharmacyRestMapper.toPharmacy(prl));
				} catch (Exception e) {
					log.error("Cannot parse rest pharmacy", e);
				}
			});
		return pharmacyList;
	}

	@Override
	public List<Pharmacy> getTurnPharmacy() {
		List<Pharmacy> pharmacyList = new ArrayList<>();
		pharmacyTurnClient.getRestPharmacy().stream().forEach(prl -> {
			try {
				pharmacyList.add(pharmacyRestMapper.toPharmacy(prl));
			} catch (Exception e) {
				log.error("Cannot parse rest pharmacy", e);
			}
		});
		return pharmacyList;
	}
}
