package App.Service;

import java.util.List;

import App.Model.Category;

public interface CategoryService {

	public void create(String name);

	public void create(String name, Category parent);

	public void update(Category category);

	public Category getByName(String name);

	public Category getById(int id);

	public List<Category> listMainParents();

	public List<Category> listChildren(Category parent);
}
