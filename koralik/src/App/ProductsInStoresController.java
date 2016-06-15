package App;

import java.util.List;

import App.Model.Bead;
import App.Model.ProductsInStores;
import App.Service.ProductInStoresService;
import App.Service.ProductInStoresServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductsInStoresController {

	private ProductInStoresService productInStoresService = new ProductInStoresServiceImpl();
	private ObservableList<ProductsInStores> productsList = FXCollections.observableArrayList();

	public void addProduct(ProductsInStores product) {
		productInStoresService.addProduct(product);
	}

	public ObservableList<ProductsInStores> listProducts(){
		if (!productsList.isEmpty()) {
			productsList.clear();
		}
		productsList = FXCollections.observableList(productInStoresService.listProducts());
		return productsList;
	}

	public void removeProduct(Integer id){
		productInStoresService.removeProduct(id);
	}

	public void updateProduct(ProductsInStores product){
		productInStoresService.updateProduct(product);
	}
	
	public List<ProductsInStores> listProducts(Bead bead){
		if (!productsList.isEmpty()) {
			productsList.clear();
		}
		productsList = FXCollections.observableList(productInStoresService.listProducts(bead));
		return productsList;
	}
	
	public ObservableList<String> listOfUnits() {
		ObservableList<String> unitList = FXCollections.observableList(productInStoresService.listOfUnits());
		return unitList;
		
	}

}
