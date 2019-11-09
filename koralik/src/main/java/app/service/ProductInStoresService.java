package app.service;

import java.util.List;

import app.model.Bead;
import app.model.ProductsInStores;

public interface ProductInStoresService {

	void addProduct(ProductsInStores product);

	List<ProductsInStores> listProducts();

	void removeProduct(Integer id);

	void updateProduct(ProductsInStores product);

	List<ProductsInStores> listProducts(Bead bead);

	List<String> listOfUnits();
}
