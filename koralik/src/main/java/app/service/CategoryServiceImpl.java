package app.service;

import java.util.List;

import app.model.Category;
import app.dao.CategoryDAO;
import app.dao.CategoryDAOImpl;

public class CategoryServiceImpl implements CategoryService {

	private final CategoryDAO categoryDAO = new CategoryDAOImpl();

	@Override
	public void create(String name) {
		categoryDAO.create(name);

	}

	@Override
	public void create(String name, Category parent) {
		categoryDAO.create(name, parent);

	}

	@Override
	public void update(Category category) {
		categoryDAO.update(category);

	}

	@Override
	public void removeCategory(Category c) {
		categoryDAO.removeCategory(c);
	}

	@Override
	public Category getByName(String name) {

		return categoryDAO.getByName(name);
	}

	@Override
	public Category getById(int id) {
		return categoryDAO.getById(id);
	}

	@Override
	public List<Category> listMainParents() {

		return categoryDAO.listMainParents();
	}

	@Override
	public List<Category> listChildren(Category parent) {
		return categoryDAO.listChildren(parent);
	}

	@Override
	public void add(Category category) {
		categoryDAO.add(category);

	}

}
