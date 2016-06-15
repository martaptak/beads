package App.dao;

import java.util.List;

import App.Model.Brand;

public interface BrandDAO {
	
	public void addBrand(Brand brand);
	public void updateBrand(Brand brand);
	public void removeBrand(Integer id);
	public List<Brand> brandsList();

}
