package app;

import app.model.Brand;
import app.service.BrandService;
import app.service.BrandServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BrandController {

	private final BrandService brandService = new BrandServiceImpl();

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

		ObservableList<Brand> brandsList;
		brandsList = FXCollections.observableList(brandService.brandsList());

		return brandsList;
	}
}
