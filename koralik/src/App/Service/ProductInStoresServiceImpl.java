package App.Service;

import java.util.List;

import App.Model.Bead;
import App.Model.ProductsInStores;
import App.dao.ProductInStoresDAO;
import App.dao.ProductInStoresDAOImpl;

public class ProductInStoresServiceImpl implements ProductInStoresService{

	ProductInStoresDAO productInStoresDAO = new ProductInStoresDAOImpl();
	
	@Override
	public void addProduct(ProductsInStores product) {
		productInStoresDAO.addProduct(product);
		
	}

	@Override
	public List<ProductsInStores> listProducts() {
		return productInStoresDAO.listProducts();
	}

	@Override
	public void removeProduct(Integer id) {
		productInStoresDAO.removeProduct(id);
		
	}

	@Override
	public void updateProduct(ProductsInStores product) {
		productInStoresDAO.updateProduct(product);
		
	}

	@Override
	public List<ProductsInStores> listProducts(Bead bead) {
		return productInStoresDAO.listProducts(bead);
	}

	@Override
	public List<String> listOfUnits() {
		return productInStoresDAO.listOfUnits();
	}

}
