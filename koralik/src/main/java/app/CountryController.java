package app;

import app.model.Country;
import app.service.CountryService;
import app.service.CountryServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountryController {

	private final CountryService countryService = new CountryServiceImpl();

	public void addCountry(Country country) {
		countryService.addCountry(country);
	}

	public void updateCountry(Country country) {
		countryService.updateCountry(country);
	}

	public void removeCountry(Integer id) {
		countryService.removeCountry(id);
	}

	public ObservableList<Country> countriesList() {
		ObservableList<Country> countryList;
		countryList = FXCollections.observableList(countryService.countriesList());

		return countryList;
	}
}
