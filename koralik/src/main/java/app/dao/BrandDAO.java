package app.dao;

import java.util.List;

import app.model.Brand;

public interface BrandDAO {

	void addBrand(Brand brand);

	void updateBrand(Brand brand);

	void removeBrand(Integer id);

	List<Brand> brandsList();

}
