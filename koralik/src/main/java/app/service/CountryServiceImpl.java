package app.service;

import java.util.List;

import app.model.Country;
import app.dao.CountryDAO;
import app.dao.CountryDAOImpl;

public class CountryServiceImpl implements CountryService {

	private final CountryDAO countryDAO = new CountryDAOImpl();

	@Override
	public void addCountry(Country country) {
		countryDAO.addCountry(country);

	}

	@Override
	public void updateCountry(Country country) {
		countryDAO.updateCountry(country);

	}

	@Override
	public void removeCountry(Integer id) {
		countryDAO.removeCountry(id);

	}

	@Override
	public List<Country> countriesList() {
		return countryDAO.countriesList();
	}

}
