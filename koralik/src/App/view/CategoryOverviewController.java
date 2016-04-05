package App.view;

import App.BeadController;
import App.CategoryController;
import App.Main;
import App.Model.Beads;
import App.Model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CategoryOverviewController {

	@FXML
	private TreeView<String> categoryTreeView = new TreeView<String>();
	@FXML
	private TableView<Beads> beadsTable;
	@FXML
	private TableColumn<Beads, String> sizeColumn;
	@FXML
	private TableColumn<Beads, String> colorNameColumn;
	@FXML
	private TableColumn<Beads, String> finishColumn;

	private Main mainApp;
	private BeadController beadController = new BeadController();
	private CategoryController categoryController = new CategoryController();

	public CategoryOverviewController() {

	}

	@FXML
	private void initialize() {

		sizeColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("size"));
		colorNameColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("colorName"));
		finishColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("finishesNames"));
		createTree();
		//categoryTreeView.getSelectionModel().selectedItemProperty().addListener(
				//(observable, oldValue, newValue) -> {System.out.println("blabla " + oldValue + " " + newValue.getParent().getValue());});
		
		categoryTreeView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> beadsTable.getItems().setAll(beadController.listBeadsFotTable(
						newValue.getParent().getValue(), newValue.getValue())));
		
		


	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	private void createTree() {
		TreeItem<String> root = new TreeItem<String>("Kategorie");

		ObservableList<String> mainCategoriesNamesList = categoryController.getMainCategoriesName();

		for (String name : mainCategoriesNamesList) {
			root.getChildren().add(createTreeMainItem(name));
		}

		categoryTreeView.setRoot(root);
		root.setExpanded(true);
		categoryTreeView.setShowRoot(false);

	}

	private TreeItem<String> createTreeMainItem(String name) {
		TreeItem<String> mainCategory = new TreeItem<String>(name);

		ObservableList<String> subcategoriesNamesList = categoryController.getSubcategoriesNames(name);

		for (String subcategory : subcategoriesNamesList) {
			mainCategory.getChildren().add(createTreeSubcategoryItem(subcategory, name));
		}

		return mainCategory;
	}

	private TreeItem<String> createTreeSubcategoryItem(String subcategory, String main) {
		TreeItem<String> subcategoryTree = new TreeItem<String>(subcategory);

		ObservableList<String> typesNamesList = categoryController.getTypesNames(subcategory, main);

		for (String type : typesNamesList) {
			subcategoryTree.getChildren().add(new TreeItem<String>(type));
		}

		return subcategoryTree;
	}

}
