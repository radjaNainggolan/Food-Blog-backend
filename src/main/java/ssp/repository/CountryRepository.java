package ssp.repository;

import java.util.List;

import ssp.dto.CountryDto;
import ssp.dto.RecepiesShortInfo;
import ssp.model.Country;

public interface CountryRepository {

	public List<RecepiesShortInfo> getRecepiesOfCountry(Long countryId);
	
	public CountryDto addCountry(Country country);
	
	public List<CountryDto> getAllCountries();
	
	public CountryDto getCountryById(Long countryId);
	
	public int updateCountryById(Long countryId, Country country);
	
	public int deleteCountryById(Long countryId);
	
}
