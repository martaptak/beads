package App;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import App.Model.Category;
import App.Service.CategoryService;
import App.Service.CategoryServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryController {

	private CategoryService categoryService = new CategoryServiceImpl();
	private ObservableList<Category> mainParentList = FXCollections.observableArrayList();


	public void create(String name) {
		categoryService.create(name);
	}

	public void create(String name, Category parent) {
		categoryService.create(name, parent);
	}

	public void update(Category category) {
		categoryService.update(category);
	}

	public Category getByName(String name) {
		return categoryService.getByName(name);
	}

	public Category getById(int id) {
		return categoryService.getById(id);
	}

	public ObservableList<Category> listMainParents() {
		if (!mainParentList.isEmpty()) {
			mainParentList.clear();
		}
		mainParentList = FXCollections.observableList((List<Category>) categoryService.listMainParents());
		return mainParentList;

	}

	public ObservableList<Category> listChildren(Category parent) {

		ObservableList<Category> chList = FXCollections.observableArrayList();
		chList = FXCollections.observableList((List<Category>) categoryService.listChildren(parent));
		return chList;
	}

	public ObservableList<String> getSubcategoriesNames(String categoryName) {

		Category parent = this.getByName(categoryName);
		ObservableList<Category> subcategoriesList = this.listChildren(parent);
		ObservableList<String> subcategoriesNamesList = FXCollections.observableArrayList();

		for (Category c : subcategoriesList) {
			subcategoriesNamesList.add(c.getCategoryName());
		}

		return subcategoriesNamesList;

	}

	public ObservableList<String> getTypesNames(String subCategoryName, String mainCategory) {

		ObservableList<Category> subcategoriesList = listChildren(this.getByName(mainCategory));

		Comparator<Category> com = new Comparator<Category>() {
			public int compare(Category c1, Category c2) {
				return c1.getCategoryName().compareTo(c2.getCategoryName());
			}
		};
		int index = Collections.binarySearch(subcategoriesList, new Category(subCategoryName), com);

		Category parent = subcategoriesList.get(index);

		ObservableList<Category> typeList = this.listChildren(parent);
		ObservableList<String> typeNameList = FXCollections.observableArrayList();

		for (Category c : typeList) {
			typeNameList.add(c.getCategoryName());
		}

		return typeNameList;
	}

	public ObservableList<String> getMainCategoriesName() {

		ObservableList<String> mainCategoriesNamesList = FXCollections.observableArrayList();		
		
		
		for (Category c : listMainParents()) {
			mainCategoriesNamesList.add(c.getCategoryName());
		}

		return mainCategoriesNamesList;
	}

}
