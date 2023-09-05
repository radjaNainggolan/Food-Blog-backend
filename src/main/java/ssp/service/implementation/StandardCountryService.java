package ssp.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ssp.dto.CountryDto;
import ssp.dto.RecepiesShortInfo;
import ssp.model.Country;
import ssp.repository.CountryRepository;
import ssp.service.CountryService;

@Service
public class StandardCountryService implements CountryService {

	@Autowired
	CountryRepository countryRepository;
	
	@Override
	public ResponseEntity<?> getRecepiesOfCountry(Long countryId) {
		List<RecepiesShortInfo> recepies = this.countryRepository.getRecepiesOfCountry(countryId);
		return new ResponseEntity<List<RecepiesShortInfo>>(recepies,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> addCountry(Country country) {
		Country country2 = this.countryRepository.addCountry(country);
		return new ResponseEntity<Country>(country2, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getAllCountries() {
		List<CountryDto> list = this.countryRepository.getAllCountries();
		return new ResponseEntity<List<CountryDto>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getCountryById(Long countryId) {
		CountryDto countryDto = this.countryRepository.getCountryById(countryId);
		return new ResponseEntity<CountryDto>(countryDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateCountryById(Long countryId, Country country) {
		this.countryRepository.updateCountryById(countryId, country);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<?> deleteCountryById(Long countryId) {
		this.countryRepository.deleteCountryById(countryId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	

}
