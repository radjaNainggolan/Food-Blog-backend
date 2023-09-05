package ssp.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssp.controller.CountryController;
import ssp.model.Country;
import ssp.service.CountryService;


@RestController
@RequestMapping("/api")
public class StandardCountryController implements CountryController {

	@Autowired
	CountryService countryService;
	
	@GetMapping("/countries/{countryId}/recepies")
	@Override
	public ResponseEntity<?> getRecepiesOfCountry(@PathVariable("countryId") Long countryId) {
		return this.countryService.getRecepiesOfCountry(countryId);
	}

	@PostMapping("/countries/country")
	@Override
	public ResponseEntity<?> addCountry(@RequestBody Country country) {
		return this.countryService.addCountry(country);
	}

	@GetMapping("/countries")
	@Override
	public ResponseEntity<?> getAllCountries() {
		return this.countryService.getAllCountries();
	}

	@GetMapping("/countries/{countryId}")
	@Override
	public ResponseEntity<?> getCountryById(@PathVariable("countryId") Long countryId) {
		return this.countryService.getCountryById(countryId);
	}

	@PatchMapping("/countries/{countryId}")
	@Override
	public ResponseEntity<?> updateCountryById(@PathVariable("countryId") Long countryId, @RequestBody Country country) {
		return this.countryService.updateCountryById(countryId, country);
	}

	@DeleteMapping("/countries/{countryId}")
	@Override
	public ResponseEntity<?> deleteCountryById(@PathVariable("countryId") Long countryId) {
		return this.countryService.deleteCountryById(countryId);
	}

}
