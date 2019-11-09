package app.dao;

import java.util.List;

import app.model.Country;

public interface CountryDAO {

	void addCountry(Country country);

	void updateCountry(Country country);

	void removeCountry(Integer id);

	List<Country> countriesList();
}
