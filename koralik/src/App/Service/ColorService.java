package App.Service;

import java.util.List;

import App.Model.Color;
import App.Model.ColorFamily;

public interface ColorService {
	
	public void addColor(Color color);
	public void mergeColor(Color color1, Color color2);
	public void updateColor(Color color);
	public List<Color> listColors();
	public List<Color> listColorsByName(String name);
	public List<Color> listColorByCode(String code);
	public List<Color> listColorByFamily(int id);
	


}
