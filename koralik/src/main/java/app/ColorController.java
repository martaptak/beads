package app;

import java.util.List;

import app.model.Color;
import app.service.ColorServiceImpl;
import app.service.ColorService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ColorController {

	private final ColorService colorService = new ColorServiceImpl();
	private ObservableList<Color> colorList = FXCollections.observableArrayList();
	private ObservableList<Color> colorListByName = FXCollections.observableArrayList();
	private ObservableList<Color> colorListByCode = FXCollections.observableArrayList();
	private ObservableList<Color> colorListByFamily = FXCollections.observableArrayList();

	public void addColor(Color color) {
		colorService.addColor(color);
	}

	public void mergeColor(Color color1, Color color2) {
		colorService.mergeColor(color1, color2);
	}

	public void updateColor(Color color) {
		colorService.updateColor(color);
	}

	public void removeColor(Color color) {
		colorService.removeColor(color);
	}

	public List<Color> listColors() {
		if (!colorList.isEmpty()) {
			colorList.clear();
		}
		colorList = FXCollections.observableList(colorService.listColors());
		return colorList;
	}

	public List<Color> listColorsByName(String name) {
		if (!colorListByName.isEmpty()) {
			colorListByName.clear();
		}
		colorListByName = FXCollections.observableList(colorService.listColorsByName(name));
		return colorListByName;
	}

	public List<Color> listColorByCode(String code) {
		if (!colorListByCode.isEmpty()) {
			colorListByCode.clear();
		}
		colorListByCode = FXCollections.observableList(colorService.listColorByCode(code));
		return colorListByCode;
	}

	public List<Color> listColorByFamily(int id) {
		if (!colorListByFamily.isEmpty()) {
			colorListByFamily.clear();
		}
		colorListByFamily = FXCollections.observableList(colorService.listColorByFamily(id));
		return colorListByFamily;
	}

}
