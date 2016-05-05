package App.view;

import java.util.HashSet;

import App.BeadController;
import App.CategoryController;
import App.ColorController;
import App.ColorFamilyController;
import App.Main;
import App.Model.Beads;
import App.Model.Category;
import App.Model.Color;
import App.Model.ColorFamily;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

public class HomeOverviewController {

	@FXML
	private TableView<Beads> beadsTable;
	@FXML
	private TableColumn<Beads, String> categoryNameColumn;
	@FXML
	private TableColumn<Beads, String> sizeColumn;
	@FXML
	private TableColumn<Beads, String> colorNameColumn;
	@FXML
	private TableColumn<Beads, String> mainCategoryName;
	@FXML
	private TableColumn<Beads, String> subcategoryName;
	@FXML
	private TextField colorFiltr;
	@FXML
	private TextField sizeFiltr;
	@FXML
	private ComboBox<ColorFamily> colorFamilyFiltr;
	@FXML
	private ComboBox<String> ownedFiltr;
	@FXML
	private ComboBox<Category> mainCategoryFiltr;
	@FXML
	private ComboBox<Category> subCategoryFiltr;
	@FXML
	private ComboBox<Category> typeFiltr;
	@FXML
	private ContextMenu contextMenu;
	@FXML
	private MenuItem category;
	@FXML
	private MenuItem colors;

	private BeadController beadController = new BeadController();
	private ColorController colorController = new ColorController();
	private ColorFamilyController colorFamilyController = new ColorFamilyController();
	private CategoryController categoryController = new CategoryController();

	private ObservableList<Beads> masterData = beadController.listBeadsForTable();
	private ObservableList<String> options = FXCollections.observableArrayList("wszystkie", "tak", "nie");
	private TabPane tabPane;
	private Main mainApp;
	private TabPaneOverviewController tabPaneOverviewController;

	@FXML
	private void initialize() {

		mainCategoryName.setCellValueFactory(new PropertyValueFactory<Beads, String>("mainCategoryName"));
		subcategoryName.setCellValueFactory(new PropertyValueFactory<Beads, String>("subcategoryName"));
		categoryNameColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("categoryName"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("size"));
		colorNameColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("colorName"));

		colorFamilyFiltr.getItems().setAll(colorFamilyController.listColorFamily());
		colorFamilyFiltr.getItems().add(0, new ColorFamily("Wszystkie", new HashSet<Color>()));
		colorFamilyFiltr.getSelectionModel().select(0);

		ownedFiltr.setItems(options);
		ownedFiltr.getSelectionModel().select(0);

		mainCategoryFiltr.setItems(categoryController.listMainParents());
		mainCategoryFiltr.getItems().add(0, new Category("Wszystkie", new HashSet<Beads>()));
		mainCategoryFiltr.getSelectionModel().select(0);
		subCategoryFiltr.getItems().add(0, new Category("", new HashSet<Beads>()));
		subCategoryFiltr.getSelectionModel().select(0);
		typeFiltr.getItems().add(0, new Category("", new HashSet<Beads>()));
		typeFiltr.getSelectionModel().select(0);

		mainCategoryFiltr.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

			if (mainCategoryFiltr.getSelectionModel().getSelectedIndex() == 0) {
				subCategoryFiltr.getItems().clear();
				subCategoryFiltr.getItems().add(0, new Category("", new HashSet<Beads>()));
				subCategoryFiltr.getSelectionModel().select(0);

			}
			subCategoryFiltr.setItems(categoryController.listChildren(newValue));
			subCategoryFiltr.getItems().add(0, new Category("Wszystkie", new HashSet<Beads>()));
			subCategoryFiltr.getSelectionModel().select(0);

		});

		subCategoryFiltr.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

			if (mainCategoryFiltr.getSelectionModel().getSelectedIndex() == 0) {
				typeFiltr.getItems().clear();
				typeFiltr.getItems().add(0, new Category("", new HashSet<Beads>()));
				typeFiltr.getSelectionModel().select(0);

			}

			typeFiltr.setItems(categoryController.listChildren(newValue));
			typeFiltr.getItems().add(0, new Category("Wszystkie", new HashSet<Beads>()));
			typeFiltr.getSelectionModel().select(0);

		});

		FilteredList<Beads> filteredData = new FilteredList<>(masterData, p -> true);

		filteredData.predicateProperty()
				.bind(Bindings.createObjectBinding(() -> bead -> (bead.getColor().getColorName().toLowerCase()
						.contains(colorFiltr.getText().toLowerCase())
						|| bead.getColor().getColorCode().toLowerCase().contains(colorFiltr.getText().toLowerCase()))
						&& (bead.getColor().getColorFamily()
								.equals(colorFamilyFiltr.getSelectionModel().getSelectedItem()) || colorFamilyFiltr
										.getSelectionModel().getSelectedIndex() == 0)
						&& ((bead.getOwned().booleanValue() == true) && (ownedFiltr.getSelectionModel()
								.getSelectedItem().equals("tak"))
								|| (bead.getOwned().booleanValue() == false)
										&& (ownedFiltr.getSelectionModel().getSelectedItem().equals("nie"))
								|| ownedFiltr.getSelectionModel().getSelectedIndex() == 0)
						&& (bead.getParentCategory().equals(mainCategoryFiltr.getSelectionModel().getSelectedItem())
								|| mainCategoryFiltr.getSelectionModel().getSelectedIndex() == 0)
						&& (bead.getSubcategory().equals(subCategoryFiltr.getSelectionModel().getSelectedItem())
								|| subCategoryFiltr.getSelectionModel().getSelectedIndex() == 0)
						&& (bead.getCategory().equals(typeFiltr.getSelectionModel().getSelectedItem())
								|| typeFiltr.getSelectionModel().getSelectedIndex() == 0)
						&& bead.getSize().toLowerCase().contains(sizeFiltr.getText().toLowerCase()),

						colorFiltr.textProperty(), colorFamilyFiltr.getSelectionModel().selectedItemProperty(),
						ownedFiltr.getSelectionModel().selectedItemProperty(),
						mainCategoryFiltr.getSelectionModel().selectedItemProperty(),
						subCategoryFiltr.getSelectionModel().selectedItemProperty(),
						typeFiltr.getSelectionModel().selectedItemProperty(), sizeFiltr.textProperty()

		));

		SortedList<Beads> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(beadsTable.comparatorProperty());
		beadsTable.setItems(sortedData);

		beadsTable.setRowFactory(bead -> new TableRow<Beads>() {
			@Override
			public void updateItem(Beads item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setStyle("");
				} else if (item.getOwned().equals(true)) {
					setStyle("-fx-background-color: LightGreen;");
				} else {
					setStyle("");
				}
			}
		});

		colors.setOnAction(e -> {
			tabPane.getSelectionModel().select(2);
			tabPaneOverviewController.selectColor((beadsTable.getSelectionModel().getSelectedItem().getColor()));

		});

		category.setOnAction(e -> {
			tabPane.getSelectionModel().select(1);
			tabPaneOverviewController.selectCategory((beadsTable.getSelectionModel().getSelectedItem().getCategory()));

		});

	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	@FXML
	private void handleDeleteBead() {
		int selectedIndex = beadsTable.getSelectionModel().getSelectedIndex();
		int id = beadsTable.getSelectionModel().selectedItemProperty().getValue().getIdBeads();
		if (selectedIndex >= 0) {
			beadController.removeBead(id);
			beadsTable.getItems().setAll(beadController.listBeadsForTable());
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("Nie zosta� zaznaczony koralik");
			alert.setContentText("Wybierz koralik z tabeli");

			alert.showAndWait();
		}

	}

	@FXML
	private void handleNewBead() {
		Beads tempBead = new Beads();
		boolean okClicked = mainApp.showBeadEditDialog(tempBead);
		if (okClicked) {
			beadController.addBead(tempBead);
			beadsTable.getItems().setAll(beadController.listBeadsForTable());

		}
	}

	@FXML
	private void handleEditBead() {
		Beads selectedBead = beadsTable.getSelectionModel().getSelectedItem();
		if (selectedBead != null) {
			boolean okClicked = mainApp.showBeadEditDialog(selectedBead);
			if (okClicked) {
				beadController.updateBead(selectedBead);
				beadsTable.getItems().setAll(beadController.listBeadsForTable());
			}

		} else {

			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Nie wybrano");
			alert.setHeaderText("Koralik nie wybrany");
			alert.setContentText("Zaznacz koralik w tabeli");

			alert.showAndWait();
		}

	}

	@FXML
	private void handleShowBead() {
		Beads selectedBead = beadsTable.getSelectionModel().getSelectedItem();
		if (selectedBead != null) {
			mainApp.showBeadDetailDialog(selectedBead);

		} else {

			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Nie wybrano");
			alert.setHeaderText("Koralik nie wybrany");
			alert.setContentText("Zaznacz koralik w tabeli");

			alert.showAndWait();
		}
	}

	public void setTabPaneOverviewController(TabPaneOverviewController tabPaneOverviewController) {
		this.tabPaneOverviewController = tabPaneOverviewController;

	}
}