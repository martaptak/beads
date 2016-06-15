package App.view;

import java.util.List;
import java.util.Optional;
import App.BeadController;
import App.CategoryController;
import App.Main;
import App.ProductsInStoresController;
import App.Model.Bead;
import App.Model.Category;
import App.Model.ProductsInStores;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CategoryOverviewController {

	@FXML
	private TreeView<Category> categoryTreeView = new TreeView<Category>();
	@FXML
	private TableView<Bead> beadsTable;
	@FXML
	private TableColumn<Bead, String> sizeColumn;
	@FXML
	private TableColumn<Bead, String> colorNameColumn;
	@FXML
	private TableColumn<Bead, String> finishColumn;
	@FXML
	private ContextMenu contextMenu;
	@FXML
	private MenuItem editBead;
	@FXML
	private MenuItem deleteBead;
	@FXML
	private MenuItem mergeBead;
	@FXML
	private ContextMenu treeMenu;
	@FXML
	private MenuItem addCategory;
	@FXML
	private MenuItem removeCategory;
	@FXML
	private MenuItem editCategory;
	@FXML
	private MenuItem changeCategory;
	@FXML
	private MenuItem moveCategory;
	@FXML
	private MenuItem changeSize;

	private Main mainApp;
	private BeadController beadController = new BeadController();
	private CategoryController categoryController = new CategoryController();
	private ProductsInStoresController productsInStoresController = new ProductsInStoresController();
	private TabPaneOverviewController tabPaneOverviewController;

	@FXML
	private void initialize() {

		sizeColumn.setCellValueFactory(new PropertyValueFactory<Bead, String>("size"));
		colorNameColumn.setCellValueFactory(new PropertyValueFactory<Bead, String>("colorName"));
		finishColumn.setCellValueFactory(new PropertyValueFactory<Bead, String>("FinishesNames"));
		createTree();

		categoryTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> beadsTable.setItems(beadController.listBeadsForTable(newValue.getValue())));

		editBead.setOnAction(e -> {
			Bead selectedBead = beadsTable.getSelectionModel().getSelectedItem();
			tabPaneOverviewController.editBead(selectedBead);

		});

		deleteBead.setOnAction(e -> {
			Bead selectedBead = beadsTable.getSelectionModel().getSelectedItem();
			beadController.removeBead(selectedBead.getIdBeads());
			beadsTable.getItems().remove(selectedBead);
		});

		mergeBead.setOnAction(e -> {

			Bead selectedBead = beadsTable.getSelectionModel().getSelectedItem();

			List<Bead> pickedBeads = mainApp.showPickBeadDialog(selectedBead);

			if (!pickedBeads.isEmpty() || pickedBeads != null) {

				for (Bead bead : pickedBeads) {
					for (ProductsInStores product : bead.getProductsInStores()) {
						product.setBeads(selectedBead);
						productsInStoresController.updateProduct(product);
					}
					beadController.removeBead(bead.getIdBeads());
					beadsTable.getItems().remove(bead);
				}
			}

		});

		beadsTable.setRowFactory(e -> {
			TableRow<Bead> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					mainApp.showBeadDetailDialog(row.getItem());
				}
			});
			return row;
		});

		editCategory.setOnAction(e -> {
			categoryTreeView.setEditable(true);
			categoryTreeView.setCellFactory(value -> new TextFieldTreeCellImpl());
			categoryTreeView.setOnKeyReleased(t -> {
				if (t.getCode() == KeyCode.ENTER) {
					categoryTreeView.setEditable(false);
				}
			});

		});

		removeCategory.setOnAction(e -> {
			int selectedIndex = categoryTreeView.getSelectionModel().getSelectedIndex();
			Category currentCategory = categoryTreeView.getSelectionModel().getSelectedItem().getValue();

			if (selectedIndex >= 0) {
				categoryController.removeCategory(currentCategory);
				createTree();
			}
		});

		addCategory.setOnAction(e -> {
			Category selectedSubcategory = categoryTreeView.getSelectionModel().getSelectedItem().getValue();

			if (selectedSubcategory != null) {
				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Nowa Kategoria");
				dialog.setContentText("Podaj nazwê kategorii:");

				Optional<String> result = dialog.showAndWait();

				result.ifPresent(name -> {
					Category mainCategory = new Category(name, selectedSubcategory);
					categoryController.add(mainCategory);
					createTree();

				});
			}
		});

		changeCategory.setOnAction(e -> {

			Category selectedCategory = categoryTreeView.getSelectionModel().getSelectedItem().getValue();

			Category pickedCategory = mainApp.showCategoryPickDialog();

			if (pickedCategory.getIdCategory() != null) {

				List<Bead> beadsToMove = beadController.listBeadsForTable(selectedCategory);

				for (Bead bead : beadsToMove) {
					bead.setCategory(pickedCategory);
					beadController.updateBead(bead);
				}

				List<Category> childrenCategories = categoryController.listChildren(selectedCategory);

				for (Category category : childrenCategories) {
					category.setParentCategory(pickedCategory);
					categoryController.update(category);
				}

				categoryController.removeCategory(selectedCategory);
				createTree();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(mainApp.getPrimaryStage());
				alert.setTitle("B³¹d!");
				alert.setHeaderText("B³¹d przy wyborze kategorii");
				alert.setContentText("Spróbuj jeszcze raz");
				alert.showAndWait();
			}
		});

		moveCategory.setOnAction(e -> {

			Category selectedCategory = categoryTreeView.getSelectionModel().getSelectedItem().getValue();

			Category pickedCategory = mainApp.showCategoryPickDialog();

			if (pickedCategory.getIdCategory() != null) {

				selectedCategory.setParentCategory(pickedCategory);
				categoryController.update(selectedCategory);
				createTree();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(mainApp.getPrimaryStage());
				alert.setTitle("B³¹d!");
				alert.setHeaderText("B³¹d przy wyborze kategorii");
				alert.setContentText("Spróbuj jeszcze raz");
				alert.showAndWait();
			}

		});

		changeSize.setOnAction(e -> {

			Category selectedCategory = categoryTreeView.getSelectionModel().getSelectedItem().getValue();

			TextInputDialog dialog = new TextInputDialog("");
			dialog.setTitle("Rozmiar");
			dialog.setContentText("Podaj rozmiar dla ca³ej kategorii:");

			Optional<String> result = dialog.showAndWait();

			result.ifPresent(name -> {

				List<Bead> beads = beadController.listBeadsForTable(selectedCategory);

				for (Bead bead : beads) {
					bead.setSize(name);
					beadController.updateBead(bead);

				}

			});
			beadsTable.setItems(beadController.listBeadsForTable(selectedCategory));
		});

	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	public void setTabPaneOverviewController(TabPaneOverviewController tabPaneOverviewController) {
		this.tabPaneOverviewController = tabPaneOverviewController;

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

	public void selectCategory(Category category) {
		categoryTreeView.getSelectionModel().select(new TreeItem<Category>(category));
	}

}

final class TextFieldTreeCellImpl extends TreeCell<Category> {

	private TextField textField;
	private CategoryController categoryController = new CategoryController();

	public TextFieldTreeCellImpl() {

	}

	@Override
	public void startEdit() {
		super.startEdit();

		if (textField == null) {
			createTextField();
		}
		setText(null);
		setGraphic(textField);
		textField.selectAll();
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setText(getItem().toString());
		setGraphic(getTreeItem().getGraphic());
	}

	@Override
	public void updateItem(Category item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (textField != null) {
					textField.setText(getString());
				}
				setText(null);
				setGraphic(textField);
			} else {
				setText(getString());
				setGraphic(getTreeItem().getGraphic());
				if (!getTreeItem().isLeaf() && getTreeItem().getParent() != null) {

				}
			}
		}
	}

	private void createTextField() {
		textField = new TextField(getString());
		textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent t) {
				if (t.getCode() == KeyCode.ENTER) {
					getItem().setCategoryName(textField.getText());
					commitEdit(getItem());
					categoryController.update(getItem());
				} else if (t.getCode() == KeyCode.ESCAPE) {
					cancelEdit();
				}
			}
		});
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}

}