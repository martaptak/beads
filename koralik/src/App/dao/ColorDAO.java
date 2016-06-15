package App.dao;

import java.util.List;

import App.Model.Color;

public interface ColorDAO {

	public void addColor(Color color);
	public void mergeColor(Color color1, Color color2);
	public void updateColor(Color color);
	public void removeColor(Color color);
	public List<Color> listColors();
	public List<Color> listColorsByName(String name);
	public List<Color> listColorByCode(String code);
	public List<Color> listColorByFamily(int id);
	
}
