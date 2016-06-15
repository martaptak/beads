package App.dao;

import java.util.List;

import App.Model.ColorFamily;

public interface ColorFamilyDAO {

	public void addColorFamily(ColorFamily colorFamily);
	public void removeColorFamily(ColorFamily colorFamily);
	public void updateColorFamily(ColorFamily colorFamily);	
	public List<ColorFamily> listColorFamily();
	public ColorFamily getDefaultColorFamily();
}
