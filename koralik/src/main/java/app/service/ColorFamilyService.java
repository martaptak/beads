package app.service;

import java.util.List;

import app.model.ColorFamily;

public interface ColorFamilyService {

	void addColorFamily(ColorFamily colorFamily);

	void removeColorFamily(ColorFamily colorFamily);

	void updateColorFamily(ColorFamily colorFamily);

	List<ColorFamily> listColorFamily();

	ColorFamily getDefaultColorFamily();
}
