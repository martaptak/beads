package App.dao;

import java.util.List;

import App.Model.Category;

public interface CategoryDAO {

	public void create(String name);
	public void create(String name, Category parent);
	public void add(Category category);
	public void update(Category category);
	public void removeCategory(Category c);
	
	public Category getByName(String name);

	public Category getById(int id);

	public List<Category> listMainParents();

	public List<Category> listChildren(Category parent);
	

}