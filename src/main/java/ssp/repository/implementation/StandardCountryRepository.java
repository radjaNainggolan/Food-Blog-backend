package ssp.repository.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ssp.dto.CountryDto;
import ssp.dto.RecepiesShortInfo;
import ssp.dto.row_mapper.CountryDtoRowMapper;
import ssp.dto.row_mapper.RecepieShortInfoRowMapper;
import ssp.model.Country;
import ssp.repository.AppUserRepository;
import ssp.repository.CountryRepository;

@Repository
public class StandardCountryRepository implements CountryRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	RecepieShortInfoRowMapper recepieShortInfoRowMapper;
	
	@Autowired
	CountryDtoRowMapper countryDtoRowMapper;
	
	private final String getRecepiesOfCountry = """
			SELECT DISTINCT r.id, r.name, ri.image
			FROM
				recepie as r
				inner join
				recepie_image as ri
				on ri.recepie_id = r.id
				inner join 
				category as c
				on c.id = r.category_id
			WHERE c.id = ?
			""";
	
	private final String addCountry = """
			INSERT INTO country(name) VALUES (?)
			""";
	
	private final String selectAllCountries = """
			SELECT id, name FROM country
			""";
	
	private final String selectCountryById = """
			SELECT id, name FROM country WHERE id = ?
			""";
	
	private final String updateCountry = """
			UPDATE country SET name = ? WHERE id = ?
			""";
	
	private final String deleteCountry = """
			DELETE FROM country WHERE id = ?
			""";
	
	@Override
	public List<RecepiesShortInfo> getRecepiesOfCountry(Long countryId) {
		List<RecepiesShortInfo> recepies = this.jdbcTemplate.query(getRecepiesOfCountry, recepieShortInfoRowMapper, countryId);
		
		for(RecepiesShortInfo info : recepies) {
			int numOfLikes = this.appUserRepository.numberOfLikesOfRecepieById(info.getId());
			info.setNumberOfLikes(numOfLikes);
		}
		
		return recepies;
	}

	@Override
	public Country addCountry(Country country) {
		int res = this.jdbcTemplate.update(addCountry, country.getName());
		long id = this.jdbcTemplate.queryForObject("SELECT id from country WHERE name = ?", Long.class, country.getName());
		country.setId(id);
		return country;
	}

	@Override
	public List<CountryDto> getAllCountries() {
		List<CountryDto> countries = this.jdbcTemplate.query(selectAllCountries,  countryDtoRowMapper);
		
		for(CountryDto countryDto : countries) {
			List<RecepiesShortInfo> recepiesShortInfos = this.getRecepiesOfCountry(countryDto.getId());
			countryDto.setRecepies(recepiesShortInfos);
			
			for(RecepiesShortInfo info : countryDto.getRecepies()) {
				int numOfLikes = this.appUserRepository.numberOfLikesOfRecepieById(info.getId());
				info.setNumberOfLikes(numOfLikes);
			}
			
		}
		
		return countries;
	}

	@Override
	public CountryDto getCountryById(Long countryId) {
		CountryDto countryDto = this.jdbcTemplate.queryForObject(selectCountryById, countryDtoRowMapper, countryId);
		List<RecepiesShortInfo> recepiesShortInfos = this.getRecepiesOfCountry(countryId);
		countryDto.setRecepies(recepiesShortInfos);
		
		for(RecepiesShortInfo info : countryDto.getRecepies()) {
			int numOfLikes = this.appUserRepository.numberOfLikesOfRecepieById(info.getId());
			info.setNumberOfLikes(numOfLikes);
		}
		
		return countryDto;
		
	}

	@Override
	public int updateCountryById(Long countryId, Country country) {
		int res = this.jdbcTemplate.update(updateCountry, country.getName(), countryId);
		return res;
	}

	@Override
	public int deleteCountryById(Long countryId) {
		int res = this.jdbcTemplate.update(deleteCountry, countryId);
		return res;
	}

}
