package cl.duamit.pharmacy.infrastructure.providers.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PharmacyRest {
	private String fecha;

	@JsonProperty("local_id")
	private String localId;

	@JsonProperty("fk_region")
	private String fkRegion;

	@JsonProperty("fk_comuna")
	private String fkComuna;

	@JsonProperty("fk_localidad")
	private String fkLocalidad;

	@JsonProperty("local_nombre")
	private String localNombre;

	@JsonProperty("comuna_nombre")
	private String comunaNombre;

	@JsonProperty("localidad_nombre")
	private String localidadNombre;

	@JsonProperty("local_direccion")
	private String localDireccion;

	@JsonProperty("funcionamiento_hora_apertura")
	private String funcionamientoHoraApertura;

	@JsonProperty("funcionamiento_hora_cierre")
	private String funcionamientoHoraCierre;

	@JsonProperty("local_telefono")
	private String localTelefono;

	@JsonProperty("local_lat")
	private String localLat;

	@JsonProperty("local_lng")
	private String localLng;

	@JsonProperty("funcionamiento_dia")
	private String funcionamientoDia;

}
