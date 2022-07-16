package br.com.model.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.Cambio;
import br.com.repository.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cambio endpoint")
@RestController
@RequestMapping("cambio-service")
public class CambioController {

	@Autowired
	private Environment envinronment;
	@Autowired
	private CambioRepository cambioRepository;
	
	@GetMapping(value = "/{amount}/{from}/{to}")
	@Operation(summary = "Get values of cambio ")
	public Cambio getCambio(@PathVariable("amount") BigDecimal amount, 
			@PathVariable("from") String from,
			@PathVariable("to") String to) {
		
		Cambio cambio = cambioRepository.findByFromAndTo(from, to);		
		if(cambio == null ) throw new RuntimeException("Currency Unsupported");
		

		String port = envinronment.getProperty("local.server.port");
		BigDecimal conversionFactor = cambio.getConverstionFactor();
		BigDecimal convertedValue = conversionFactor.multiply(amount);
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
		cambio.setEnviroment(port);
		return cambio;
	}

}
