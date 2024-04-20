package cl.duamit.pharmacy.domain.repositories;

import cl.duamit.pharmacy.domain.entities.Location;
import cl.duamit.pharmacy.domain.entities.Pharmacy;

import java.util.List;

public interface PharmacyRepository {
	List<Pharmacy> getPharmacy();
	List<Pharmacy> getPharmacyByLocation(Location location);
	List<Pharmacy> getTurnPharmacy();
}
