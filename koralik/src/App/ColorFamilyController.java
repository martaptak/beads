package App;

import java.util.List;

import App.Model.ColorFamily;
import App.Service.ColorFamilyService;
import App.Service.ColorFamilyServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ColorFamilyController {
	
	private ColorFamilyService colorFamilyService  = new ColorFamilyServiceImpl();
	
	private ObservableList<ColorFamily> colorFamilylist = FXCollections.observableArrayList();

	public void updateColorFamily(ColorFamily colorFamily){
		colorFamilyService.updateColorFamily(colorFamily);
	}
	
	public List<ColorFamily> listColorFamily(){
		if (!colorFamilylist.isEmpty()) {
			colorFamilylist.clear();
		}
		colorFamilylist = FXCollections.observableList((List<ColorFamily>) colorFamilyService.listColorFamily());
		return colorFamilylist;
	}
}
