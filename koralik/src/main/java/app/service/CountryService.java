package app.service;

import java.util.List;

import app.model.Country;

public interface CountryService {

	void addCountry(Country country);

	void updateCountry(Country country);

	void removeCountry(Integer id);

	List<Country> countriesList();

}
