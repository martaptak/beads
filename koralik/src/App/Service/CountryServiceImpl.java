package App.Service;

import java.util.List;

import App.Model.Country;
import App.dao.CountryDAO;
import App.dao.CountryDAOImpl;

public class CountryServiceImpl implements CountryService {
	
	private CountryDAO countryDAO = new CountryDAOImpl();

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
