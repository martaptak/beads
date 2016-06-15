package App.Service;

import java.util.List;

import App.Model.Country;

public interface CountryService {
	
	public void addCountry(Country country);
	public void updateCountry(Country country);
	public void removeCountry(Integer id);
	public List<Country> countriesList();

}
