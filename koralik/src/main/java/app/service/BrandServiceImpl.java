package app.service;

import java.util.List;

import app.model.Brand;
import app.dao.BrandDAO;
import app.dao.BrandDAOImpl;

public class BrandServiceImpl implements BrandService {

	private final BrandDAO brandDAO = new BrandDAOImpl();

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
