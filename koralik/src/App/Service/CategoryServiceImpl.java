package App.Service;

import java.util.List;

import App.Model.Category;
import App.dao.CategoryDAO;
import App.dao.CategoryDAOImpl;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDAO kategorieDao = new CategoryDAOImpl();
	
	@Override
	public void create(String name) {
		kategorieDao.create(name);
		
	}

	@Override
	public void create(String name, Category parent) {
		kategorieDao.create(name, parent);
		
	}

	@Override
	public void update(Category category) {
		kategorieDao.update(category);
		
	}

	@Override
	public Category getByName(String name) {
		
		return kategorieDao.getByName(name);
	}

	@Override
	public Category getById(int id) {
		return kategorieDao.getById(id);
	}

	@Override
	public List<Category> listMainParents() {
	
		return kategorieDao.listMainParents();
	}

	@Override
	public List<Category> listChildren(Category parent) {
		return kategorieDao.listChildren(parent);
	}

}
