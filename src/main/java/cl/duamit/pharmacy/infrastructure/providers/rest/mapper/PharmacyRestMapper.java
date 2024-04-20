package cl.duamit.pharmacy.infrastructure.providers.rest.mapper;

import cl.duamit.pharmacy.domain.entities.Address;
import cl.duamit.pharmacy.domain.entities.Coordinates;
import cl.duamit.pharmacy.domain.entities.Location;
import cl.duamit.pharmacy.domain.entities.Pharmacy;
import cl.duamit.pharmacy.domain.valueobjects.LocationType;
import cl.duamit.pharmacy.infrastructure.providers.rest.model.PharmacyRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class PharmacyRestMapper {

	public Pharmacy toPharmacy(PharmacyRest pharmacyRest) throws Exception{
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("dd-MM-yy");
		Pharmacy response = new Pharmacy();
		response.setId(pharmacyRest.getLocalId());
		response.setName(pharmacyRest.getLocalNombre().replaceAll("FARMACIA ", ""));
		Coordinates c = Coordinates.builder().build();
		try {
			c = Coordinates.builder()
				.latitude(Double.parseDouble(pharmacyRest.getLocalLat().replaceAll("(^\\h*)|(\\h*$)", "")))
				.longitude(Double.parseDouble(pharmacyRest.getLocalLng().replaceAll("(^\\h*)|(\\h*$)", "")))
				.build();
		}catch (Exception e){
			log.error("Error en el formato de las coordenadas", e);
		}

		Address address = new Address();
		address.setAddress(pharmacyRest.getLocalDireccion());
		address.setLocality(Location.builder().type(LocationType.LOCALITY).name(pharmacyRest.getLocalidadNombre()).id(pharmacyRest.getFkLocalidad()).build());
		address.setCommune(Location.builder().type(LocationType.COMMUNE).name(pharmacyRest.getComunaNombre()).id(pharmacyRest.getFkComuna()).build());
		address.setCoordinates(c);
		response.setAddress(address);
		LocalDate date = LocalDate.now();

		try {
			date = LocalDate.parse(pharmacyRest.getFecha(), dateFormatter);
		}catch (Exception e){
			try {
				date = LocalDate.parse(pharmacyRest.getFecha(), dateFormatter2);
			}catch (Exception e2) {
				log.error("Error en el formato de la fecha", e2);
			}
		}

		LocalTime timeFrom = LocalTime.parse(pharmacyRest.getFuncionamientoHoraApertura(), timeFormatter);
		LocalTime timeTo = LocalTime.parse(pharmacyRest.getFuncionamientoHoraCierre(), timeFormatter);

		LocalDateTime from = LocalDateTime.of(date, timeFrom);
		LocalDateTime to = LocalDateTime.of(date, timeTo);

		response.setOpenAt(timeFrom);
		response.setCloseAt(timeTo);
		response.setDia(date);

		response.setPhone(pharmacyRest.getLocalTelefono());

		response.setOpenNow(LocalDateTime.now().isAfter(from) && LocalDateTime.now().isBefore(to));


		return response;
	}
}
