package App.view;

import java.util.Optional;

import App.CategoryController;
import App.Model.Category;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class CategoryPickDialogController {

	@FXML
	private TreeView<Category> treeView;

	private CategoryController categoryController = new CategoryController();
	private Stage dialogStage;
	private boolean okClicked = false;
	private Category category;

	@FXML
	private void initialize() {

		createTree();

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public Category pickedCategory() {
		return category;
	}

	@FXML
	private void handleOk() {

		category = treeView.getSelectionModel().getSelectedItem().getValue();
		okClicked = true;
		dialogStage.close();
	}

	
	private void createTree() {
		TreeItem<Category> root = new TreeItem<Category>(new Category("Kategorie"));

		ObservableList<Category> mainCategoriesNamesList = categoryController.listMainParents();

		for (Category name : mainCategoriesNamesList) {
			root.getChildren().add(createTreeMainItem(name));
		}

		treeView.setRoot(root);
		root.setExpanded(true);
		treeView.setShowRoot(false);

	}

	private TreeItem<Category> createTreeMainItem(Category name) {
		TreeItem<Category> mainCategory = new TreeItem<Category>(name);

		ObservableList<Category> subcategoriesNamesList = categoryController.listChildren(name);

		for (Category subcategory : subcategoriesNamesList) {
			mainCategory.getChildren().add(createTreeSubcategoryItem(subcategory));
		}

		return mainCategory;
	}

	private TreeItem<Category> createTreeSubcategoryItem(Category subcategory) {
		TreeItem<Category> subcategoryTree = new TreeItem<Category>(subcategory);

		ObservableList<Category> typesNamesList = categoryController.listChildren(subcategory);

		for (Category type : typesNamesList) {
			subcategoryTree.getChildren().add(new TreeItem<Category>(type));
		}

		return subcategoryTree;
	}
}
