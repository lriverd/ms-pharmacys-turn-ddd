package cl.duamit.location.infraestructure.controllers;

import cl.duamit.location.domain.entities.Region;
import cl.duamit.location.usecases.RegionesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/region")
@Slf4j
public class RegionController {

	@Autowired
	private RegionesUseCase regionesUseCase;

	@Operation(summary = "Get chilean regions")
	@ApiResponse(responseCode = "200", description = "Found a list of regions",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Region.class))})
	@GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> getRegions() throws Exception {
		return ok(regionesUseCase.getRegiones());
	}

	@Operation(summary = "Get chilean regions")
	@ApiResponse(responseCode = "200", description = "Found a list of regions",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Region.class))})
	@GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> findByWord(String pattern) throws Exception {
		return ok(regionesUseCase.getRegiones());
	}
}
