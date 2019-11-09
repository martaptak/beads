package app;

import java.util.List;

import app.model.ColorFamily;
import app.service.ColorFamilyService;
import app.service.ColorFamilyServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ColorFamilyController {

	private final ColorFamilyService colorFamilyService = new ColorFamilyServiceImpl();


	public void updateColorFamily(ColorFamily colorFamily) {
		colorFamilyService.updateColorFamily(colorFamily);
	}

	public List<ColorFamily> listColorFamily() {
		ObservableList<ColorFamily> colorFamilyList;
		colorFamilyList = FXCollections.observableList(colorFamilyService.listColorFamily());
		return colorFamilyList;
	}

	public ColorFamily getDefaultColorFamily() {
		return colorFamilyService.getDefaultColorFamily();
	}

	public void addColorFamily(ColorFamily colorFamily) {
		colorFamilyService.addColorFamily(colorFamily);

	}

	public void removeColorFamily(ColorFamily colorFamily) {
		colorFamilyService.removeColorFamily(colorFamily);
	}
}
