package App.Service;

import java.util.List;

import App.Model.Beads;
import App.Model.ProductsInStores;

public interface ProductInStoresService {

	public void addProduct(ProductsInStores product);
	public List<ProductsInStores> listProducts();
	public void removeProduct(Integer id);
	public void updateProduct(ProductsInStores product);
	public List<ProductsInStores> listProducts(Beads bead);
}