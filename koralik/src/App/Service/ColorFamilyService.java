package App.Service;

import java.util.List;

import App.Model.ColorFamily;

public interface ColorFamilyService {
	public void updateColorFamily(ColorFamily colorFamily);
	public List<ColorFamily> listColorFamily();

}
