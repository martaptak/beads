package App;

import java.util.List;

import App.Model.Color;
import App.Service.ColorServiceImpl;
import App.Service.ColorService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ColorController {

	private ColorService colorService = new ColorServiceImpl();
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
		colorList = FXCollections.observableList((List<Color>) colorService.listColors());
		return colorList;
	}

	public List<Color> listColorsByName(String name) {
		if (!colorListByName.isEmpty()) {
			colorListByName.clear();
		}
		colorListByName = FXCollections.observableList((List<Color>) colorService.listColorsByName(name));
		return colorListByName;
	}

	public List<Color> listColorByCode(String code) {
		if (!colorListByCode.isEmpty()) {
			colorListByCode.clear();
		}
		colorListByCode = FXCollections.observableList((List<Color>) colorService.listColorByCode(code));
		return colorListByCode;
	}

	public List<Color> listColorByFamily(int id) {
		if (!colorListByFamily.isEmpty()) {
			colorListByFamily.clear();
		}
		colorListByFamily = FXCollections.observableList((List<Color>) colorService.listColorByFamily(id));
		return colorListByFamily;
	}

}
