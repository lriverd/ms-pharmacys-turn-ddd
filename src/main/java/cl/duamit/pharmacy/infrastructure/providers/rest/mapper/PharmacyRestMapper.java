package cl.duamit.pharmacy.infrastructure.providers.rest.mapper;

import cl.duamit.pharmacy.domain.entities.Address;
import cl.duamit.pharmacy.domain.entities.Coordinates;
import cl.duamit.pharmacy.domain.entities.Pharmacy;
import cl.duamit.pharmacy.infrastructure.providers.rest.model.PharmacyRest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class PharmacyRestMapper {

	public Pharmacy toPharmacy(PharmacyRest pharmacyRest) throws Exception{
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Pharmacy response = new Pharmacy();
		response.setId(pharmacyRest.getLocalId());
		response.setName(pharmacyRest.getLocalNombre());

		Coordinates c = Coordinates.builder()
			.latitude(Double.parseDouble(pharmacyRest.getLocalLat().replaceAll("(^\\h*)|(\\h*$)","")))
			.longitude(Double.parseDouble(pharmacyRest.getLocalLng().replaceAll("(^\\h*)|(\\h*$)","")))
			.build();

		Address address = new Address();
		address.setAddress(pharmacyRest.getLocalDireccion());
		address.setLocality(pharmacyRest.getLocalidadNombre());
		address.setCoordinates(c);
		response.setAddress(address);

		LocalDate date = LocalDate.parse(pharmacyRest.getFecha(), dateFormatter);

		LocalTime timeFrom = LocalTime.parse(pharmacyRest.getFuncionamientoHoraApertura(), timeFormatter);
		LocalTime timeTo = LocalTime.parse(pharmacyRest.getFuncionamientoHoraCierre(), timeFormatter);

		LocalDateTime from = LocalDateTime.of(date, timeFrom);
		LocalDateTime to = LocalDateTime.of(date, timeTo);

		if (to.isBefore(from)) {
			to = to.plusDays(1);
		}

		response.setOpenAt(from);
		response.setCloseAt(to);

		response.setPhone(pharmacyRest.getLocalTelefono());

		response.setOpenNow(LocalDateTime.now().isAfter(from) && LocalDateTime.now().isBefore(to));


		return response;
	}
}
