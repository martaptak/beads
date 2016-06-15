package App;

import App.Model.Brand;
import App.Service.BrandService;
import App.Service.BrandServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BrandController {

	private BrandService brandService = new BrandServiceImpl();
	
	public void addBrand(Brand brand) {
		brandService.addBrand(brand);
	}
	public void updateBrand(Brand brand) {
		brandService.updateBrand(brand);
	}
	public void removeBrand(Integer id) {
		brandService.removeBrand(id);
	}
	public ObservableList<Brand> brandsList() {
		
		ObservableList<Brand> brandsList = FXCollections.observableArrayList();
		brandsList = FXCollections.observableList(brandService.brandsList());
		
		return brandsList;
	}
}
