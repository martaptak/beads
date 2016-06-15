package App.Service;

import java.util.List;

import App.Model.Brand;
import App.dao.BrandDAO;
import App.dao.BrandDAOImpl;

public class BrandServiceImpl implements BrandService{
	
	private BrandDAO brandDAO = new BrandDAOImpl();

	@Override
	public void addBrand(Brand brand) {
		brandDAO.addBrand(brand);
		
	}

	@Override
	public void updateBrand(Brand brand) {
		brandDAO.updateBrand(brand);
		
	}

	@Override
	public void removeBrand(Integer id) {
		brandDAO.removeBrand(id);
		
	}

	@Override
	public List<Brand> brandsList() {
		
		return brandDAO.brandsList();
	}

}
