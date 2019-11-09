package app.service;

import java.util.List;

import app.model.Color;

public interface ColorService {

	void addColor(Color color);

	void mergeColor(Color color1, Color color2);

	void updateColor(Color color);

	void removeColor(Color color);

	List<Color> listColors();

	List<Color> listColorsByName(String name);

	List<Color> listColorByCode(String code);

	List<Color> listColorByFamily(int id);


}
