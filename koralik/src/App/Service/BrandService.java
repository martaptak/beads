package App.Service;

import java.util.List;

import App.Model.Brand;

public interface BrandService {

	public void addBrand(Brand brand);
	public void updateBrand(Brand brand);
	public void removeBrand(Integer id);
	public List<Brand> brandsList();
}
