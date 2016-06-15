package App;

import java.util.List;

import App.Model.Country;
import App.Service.CountryService;
import App.Service.CountryServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountryController {

	private CountryService countryService = new CountryServiceImpl();

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
		ObservableList<Country> countryList = FXCollections.observableArrayList();		
		countryList =  FXCollections.observableList(countryService.countriesList());
		
		return countryList;
	}
}
