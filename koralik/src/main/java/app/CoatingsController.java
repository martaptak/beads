package app;

import app.model.Coatings;
import app.service.CoatingsService;
import app.service.CoatingsServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CoatingsController {
	private final CoatingsService coatingsService = new CoatingsServiceImpl();

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
		ObservableList<Coatings> list;
		list = FXCollections.observableList(coatingsService.coatingsList());

		return list;
	}
}
