package App;

import App.Model.Coatings;
import App.Service.CoatingsService;
import App.Service.CoatingsServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CoatingsController {
	private CoatingsService coatingsService = new CoatingsServiceImpl();

	public void addCoating(Coatings coating) {
		coatingsService.addCoating(coating);
	}

	public void updateCoatings(Coatings coating) {
		coatingsService.updateCoatings(coating);
	}

	public void removeCoatings(Coatings coating) {
		coatingsService.removeCoatings(coating);
	}

	public ObservableList<Coatings> coatingsList() {
		ObservableList<Coatings> list = FXCollections.observableArrayList();
		list = FXCollections.observableList(coatingsService.coatingsList());

		return list;
	}
}
