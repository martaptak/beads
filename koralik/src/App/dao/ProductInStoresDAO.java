package App.dao;

import java.util.List;

import App.Model.Bead;
import App.Model.ProductsInStores;

public interface ProductInStoresDAO {
	public void addProduct(ProductsInStores product);
	public List<ProductsInStores> listProducts();
	public void removeProduct(Integer id);
	public void updateProduct(ProductsInStores product);
	public List<ProductsInStores> listProducts(Bead bead);
	public List<String> listOfUnits();

}
