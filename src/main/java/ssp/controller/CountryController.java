package ssp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ssp.model.Country;

public interface CountryController {

	public ResponseEntity<?> getRecepiesOfCountry(@PathVariable("countryId") Long countryId);
	
	public ResponseEntity<?> addCountry(@RequestBody Country country);
	
	public ResponseEntity<?> getAllCountries();
	
	public ResponseEntity<?> getCountryById(@PathVariable("countryId") Long countryId);
	
	public ResponseEntity<?> updateCountryById(@PathVariable("countryId") Long countryId, @RequestBody Country country);
	
	public ResponseEntity<?> deleteCountryById(@PathVariable("countryId") Long countryId);
}
