package App.dao;

import java.util.List;

import App.Model.ColorFamily;

public interface ColorFamilyDAO {

	public void updateColorFamily(ColorFamily colorFamily);
	public List<ColorFamily> listColorFamily();
}
