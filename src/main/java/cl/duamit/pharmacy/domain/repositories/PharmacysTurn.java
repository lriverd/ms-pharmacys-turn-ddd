package cl.duamit.pharmacy.domain.repositories;

import cl.duamit.pharmacy.domain.entities.Pharmacy;

import java.util.List;

public interface PharmacysTurn {
	List<Pharmacy> getOpenPharmacy();
}
