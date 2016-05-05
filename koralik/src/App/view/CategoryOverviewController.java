package App.view;

import App.BeadController;
import App.CategoryController;
import App.Main;
import App.Model.Beads;
import App.Model.Category;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CategoryOverviewController {

	@FXML
	private TreeView<Category> categoryTreeView = new TreeView<Category>();
	@FXML
	private TableView<Beads> beadsTable;
	@FXML
	private TableColumn<Beads, String> sizeColumn;
	@FXML
	private TableColumn<Beads, String> colorNameColumn;
	@FXML
	private TableColumn<Beads, String> finishColumn;
	@FXML
	private ContextMenu contextMenu;
	@FXML
	private MenuItem detail;

	private Main mainApp;
	private BeadController beadController = new BeadController();
	private CategoryController categoryController = new CategoryController();

	public CategoryOverviewController() {

	}

	@FXML
	private void initialize() {

		sizeColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("size"));
		colorNameColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("colorName"));
		finishColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("FinishesNames"));
		createTree();
				
		categoryTreeView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> beadsTable.getItems().setAll(beadController.listBeadsForTable(
						newValue.getValue())));
		
		detail.setOnAction(e -> {

			Beads selectedBead = beadsTable.getSelectionModel().getSelectedItem();
			mainApp.showBeadDetailDialog(selectedBead);

		});
		
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	private void createTree() {
		TreeItem<Category> root = new TreeItem<Category>(new Category("Kategorie"));

		ObservableList<Category> mainCategoriesNamesList = categoryController.listMainParents();

		for (Category name : mainCategoriesNamesList) {
			root.getChildren().add(createTreeMainItem(name));
		}

		categoryTreeView.setRoot(root);
		root.setExpanded(true);
		categoryTreeView.setShowRoot(false);

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
	
	public void selectCategory(Category category){
		categoryTreeView.getSelectionModel().select(new TreeItem<Category>(category));
	}

}