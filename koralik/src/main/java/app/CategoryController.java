package app;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.model.Bead;
import app.model.Category;
import app.service.CategoryService;
import app.service.CategoryServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryController {

	private final CategoryService categoryService = new CategoryServiceImpl();
	private ObservableList<Category> mainParentList = FXCollections.observableArrayList();
	private final BeadController beadController = new BeadController();

	public void create(String name) {
		categoryService.create(name);
	}

	public void create(String name, Category parent) {
		categoryService.create(name, parent);
	}

	public void update(Category category) {
		categoryService.update(category);
	}

	/*
	 * public void removeCategory(Integer id) {
	 * categoryService.removeCategory(id); }
	 */

	public void removeCategory(Category c) {

		Category parent = c.getParentCategory();
		List<Bead> temp1;
		List<Category> temp2;
		if (c.getDepth() != 0) {

			temp1 = beadController.listBeadsForTable(c);

			if (!temp1.isEmpty()) {
				for (Bead bead : temp1) {
					bead.setCategory(parent);
					beadController.updateBead(bead);
				}
			}

			temp2 = categoryService.listChildren(c);

			if (!temp2.isEmpty()) {
				for (Category category : temp2) {
					category.setParentCategory(parent);
					categoryService.update(category);
				}
			}
			categoryService.removeCategory(c);
		} else {
			List<Category> children = categoryService.listChildren(c);

			if (children.isEmpty()) {
				categoryService.removeCategory(c);
			}
		}
	}

	private Category getByName(String name) {
		return categoryService.getByName(name);
	}

	public Category getById(int id) {
		return categoryService.getById(id);
	}

	public ObservableList<Category> listMainParents() {
		if (!mainParentList.isEmpty()) {
			mainParentList.clear();
		}
		mainParentList = FXCollections.observableList(categoryService.listMainParents());
		return mainParentList;

	}

	public ObservableList<Category> listChildren(Category parent) {

		ObservableList<Category> chList;
		chList = FXCollections.observableList(categoryService.listChildren(parent));
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

		Comparator<Category> com = Comparator.comparing(Category::getCategoryName);
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

	public void add(Category category) {
		categoryService.add(category);
	}

}
