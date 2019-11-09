package app.test;

import java.util.List;

import app.model.Category;
import app.dao.CategoryDAO;
import app.dao.CategoryDAOImpl;

class CategoryTest {

	private final CategoryDAO categoryDao;

	public CategoryTest() {
		categoryDao = new CategoryDAOImpl();
	}

	public void test1() {
		List<Category> parents = categoryDao.listMainParents();

		for (Category p : parents) {
			System.out.println(p.getCategoryName());

			List<Category> children = categoryDao.listChildren(p);
			for (Category c : children) {
				System.out.println("\t" + c.getCategoryName());
			}
		}


	}
}
