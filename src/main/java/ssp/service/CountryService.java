package ssp.service;

import org.springframework.http.ResponseEntity;

import ssp.model.Country;

public interface CountryService {

	public ResponseEntity<?> getRecepiesOfCountry(Long countryId);
	
	public ResponseEntity<?> addCountry(Country country);
	
	public ResponseEntity<?> getAllCountries();
	
	public ResponseEntity<?> getCountryById(Long countryId);
	
	public ResponseEntity<?> updateCountryById(Long countryId, Country country);
	
	public ResponseEntity<?> deleteCountryById(Long countryId);
	
}
