package App;

import java.util.List;

import App.Model.ColorFamily;
import App.Service.ColorFamilyService;
import App.Service.ColorFamilyServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ColorFamilyController {
	
	private ColorFamilyService colorFamilyService  = new ColorFamilyServiceImpl();


	public void updateColorFamily(ColorFamily colorFamily){
		colorFamilyService.updateColorFamily(colorFamily);
	}
	
	public List<ColorFamily> listColorFamily(){
		ObservableList<ColorFamily> colorFamilylist = FXCollections.observableArrayList();
		colorFamilylist = FXCollections.observableList((List<ColorFamily>) colorFamilyService.listColorFamily());
		return colorFamilylist;
	}
	
	public ColorFamily getDefaultColorFamily(){
		return colorFamilyService.getDefaultColorFamily();
	}
	
	public void addColorFamily(ColorFamily colorFamily){
		colorFamilyService.addColorFamily(colorFamily);
		
	}
	public void removeColorFamily(ColorFamily colorFamily){
		colorFamilyService.removeColorFamily(colorFamily);
	}
}
