package app.dao;

import java.util.List;

import app.model.Category;

public interface CategoryDAO {

	void create(String name);

	void create(String name, Category parent);

	void add(Category category);

	void update(Category category);

	void removeCategory(Category c);

	Category getByName(String name);

	Category getById(int id);

	List<Category> listMainParents();

	List<Category> listChildren(Category parent);


}