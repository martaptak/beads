package app.view;

import java.util.HashSet;
import java.util.Optional;

import app.BeadController;
import app.BrandController;
import app.ColorFamilyController;
import app.FinishController;
import app.Main;
import app.ShapeController;
import app.model.Bead;
import app.model.Brand;
import app.model.ColorFamily;
import app.model.Finish;
import app.model.Shape;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("WeakerAccess")
public class HomeOverviewController {

	@FXML
	private TableView<Bead> beadsTable;
	@FXML
	private TableColumn<Bead, String> categoryNameColumn;
	@FXML
	private TableColumn<Bead, String> sizeColumn;
	@FXML
	private TableColumn<Bead, String> colorNameColumn;
	@FXML
	private TableColumn<Bead, String> mainCategoryName;
	@FXML
	private TableColumn<Bead, String> subcategoryName;
	@FXML
	private TableColumn<Bead, String> finishColumn;
	@FXML
	private TextField colorFiltr;
	@FXML
	private TextField sizeFiltr;
	@FXML
	private ComboBox<ColorFamily> colorFamilyFiltr;
	@FXML
	private ComboBox<String> ownedFiltr;
	@FXML
	private TextField categoryFiltr;
	@FXML
	private ComboBox<Shape> shapeBox;
	@FXML
	private ComboBox<Brand> brandBox;
	@FXML
	private ComboBox<Finish> finishFiltr;
	@FXML
	private ContextMenu contextMenu;
	@FXML
	private MenuItem category;
	@FXML
	private MenuItem colors;
	@FXML
	private MenuItem duplicate;

	private final BeadController beadController = new BeadController();
	private final ColorFamilyController colorFamilyController = new ColorFamilyController();
	private final FinishController finishController = new FinishController();
	private final ShapeController shapeController = new ShapeController();
	private final BrandController brandController = new BrandController();
	private final ObservableList<Bead> masterData = beadController.listBeadsForTable();
	private final ObservableList<String> options = FXCollections.observableArrayList("wszystkie", "tak", "nie");
	private TabPane tabPane;
	private Main mainApp;

	private TabPaneOverviewController tabPaneOverviewController;

	public HomeOverviewController() {

	}

	@FXML
	private void initialize() {

		mainCategoryName.setCellValueFactory(new PropertyValueFactory<>("mainCategoryName"));
		subcategoryName.setCellValueFactory(new PropertyValueFactory<>("subcategoryName"));
		categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
		colorNameColumn.setCellValueFactory(new PropertyValueFactory<>("colorName"));
		finishColumn.setCellValueFactory(new PropertyValueFactory<>("FinishesNames"));

		colorFamilyFiltr.getItems().setAll(colorFamilyController.listColorFamily());
		colorFamilyFiltr.getItems().add(0, new ColorFamily("Wszystkie", new HashSet<>()));
		colorFamilyFiltr.getSelectionModel().select(0);

		ownedFiltr.setItems(options);
		ownedFiltr.getSelectionModel().select(0);

		finishFiltr.getItems().setAll(finishController.listFinishes());
		finishFiltr.getItems().add(0, new Finish("Wszystkie", new HashSet<>()));
		finishFiltr.getSelectionModel().select(0);

		shapeBox.getItems().setAll(shapeController.shapesList());
		shapeBox.getItems().add(0, new Shape("Wszystkie", new HashSet<>()));
		shapeBox.getItems().remove(1);
		shapeBox.getSelectionModel().select(0);

		brandBox.getItems().setAll(brandController.brandsList());
		brandBox.getItems().add(0, new Brand("Wszystkie", new HashSet<>()));
		brandBox.getItems().remove(1);
		brandBox.getSelectionModel().select(0);

		wrapListAndAddFiltering();

		colors.setOnAction(e -> {
			tabPane.getSelectionModel().select(2);
			tabPaneOverviewController.selectColor((beadsTable.getSelectionModel().getSelectedItem().getColor()));

		});

		category.setOnAction(e -> {
			tabPane.getSelectionModel().select(1);
			tabPaneOverviewController.selectCategory((beadsTable.getSelectionModel().getSelectedItem().getCategory()));

		});

		duplicate.setOnAction(e -> {
			Bead selectedBead = beadsTable.getSelectionModel().getSelectedItem();
			Bead clone = Bead.cloneBead(selectedBead);
			createNewBead(clone);

		});

		beadsTable.setRowFactory(param -> {
			TableRow<Bead> row = new MyTableRowFormat();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() > 1 && (!row.isEmpty())) {
					mainApp.showBeadDetailDialog(row.getItem());
				}
			});
			return row;
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
		Bead currentBead = beadsTable.getSelectionModel().selectedItemProperty().getValue();
		int id = currentBead.getIdBeads();
		if (selectedIndex >= 0) {
			beadController.removeBead(id);
			remove(currentBead);

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("Nie zosta³ zaznaczony koralik");
			alert.setContentText("Wybierz koralik z tabeli");

			alert.showAndWait();
		}

	}

	@FXML
	private void handleNewBead() {
		Bead tempBead = new Bead();
		createNewBead(tempBead);
	}

	private void createNewBead(Bead tempBead) {

		boolean okClicked = mainApp.showBeadEditDialog(tempBead);
		if (okClicked) {
			beadController.addBead(tempBead);
			add(tempBead);
			beadsTable.requestFocus();
			beadsTable.getSelectionModel().select(tempBead);
			beadsTable.getFocusModel().focus(beadsTable.getSelectionModel().getSelectedIndex());

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Dodawanie sklepów");
			alert.setHeaderText("Dodano nowy produkt");
			alert.setContentText("Czy chcesz dodaæ informacje o produkcie w sklepach?");

			Optional<ButtonType> result = alert.showAndWait();

			if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
				handleShowBead();
			}
		}
	}

	@FXML
	private void handleEditBead() {
		Bead selectedBead = beadsTable.getSelectionModel().getSelectedItem();
		editBead(selectedBead);

	}

	private void handleShowBead() {
		Bead selectedBead = beadsTable.getSelectionModel().getSelectedItem();
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

	private void wrapListAndAddFiltering() {

		FilteredList<Bead> filteredData = new FilteredList<>(masterData, p -> true);

		filteredData.predicateProperty()
				.bind(Bindings.createObjectBinding(() -> bead -> (bead.getColor().getColorName().toLowerCase()
								.contains(colorFiltr.getText().toLowerCase())
								|| bead.getColor().getColorCode().toLowerCase().contains(colorFiltr.getText().toLowerCase()))
								&& (bead.getColor().getColorFamily()
								.equals(colorFamilyFiltr.getSelectionModel().getSelectedItem())
								|| colorFamilyFiltr
								.getSelectionModel().getSelectedIndex() == 0)
								&& ((bead.getOwned())
								&& (ownedFiltr.getSelectionModel().getSelectedItem()
								.equals("tak"))
								|| (!bead.getOwned())
								&& (ownedFiltr.getSelectionModel().getSelectedItem().equals("nie"))
								|| ownedFiltr.getSelectionModel().getSelectedIndex() == 0)
								/*
								 * && (bead.getCategory().getParentCategory()
								 * .getParentCategory().getCategoryName().contains(categoryFiltr.getText
								 * ().toLowerCase()) ||
								 * bead.getCategory().getParentCategory().getCategoryName()
								 * .contains(categoryFiltr.getText().toLowerCase()) ||
								 * bead.getCategory().getCategoryName().toLowerCase()
								 * .contains(categoryFiltr.getText().toLowerCase()) ||
								 * bead.getCategory().getCategoryName().contains(""))
								 */
								&& bead.getSize().toLowerCase().contains(sizeFiltr.getText().toLowerCase())
								&& (bead.getFinishesNames()
								.contains(finishFiltr.getSelectionModel().getSelectedItem().getNameFinish())
								|| finishFiltr.getSelectionModel().getSelectedIndex() == 0)
								&& (bead.getShapes().equals(shapeBox.getSelectionModel().getSelectedItem())
								|| shapeBox.getSelectionModel().getSelectedIndex() == 0)
								&& (bead.getBrands().equals(brandBox.getSelectionModel().getSelectedItem())
								|| brandBox.getSelectionModel().getSelectedIndex() == 0),

						colorFiltr.textProperty(), colorFamilyFiltr.getSelectionModel().selectedItemProperty(),
						ownedFiltr.getSelectionModel().selectedItemProperty(), sizeFiltr.textProperty(),
						finishFiltr.getSelectionModel().selectedItemProperty(), categoryFiltr.textProperty(),
						shapeBox.getSelectionModel().selectedItemProperty(),
						brandBox.getSelectionModel().selectedItemProperty()

				));

		SortedList<Bead> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(beadsTable.comparatorProperty());
		beadsTable.setItems(sortedData);

	}

	private void add(Bead item) {
		masterData.add(item);
		wrapListAndAddFiltering();

	}

	private void remove(Bead item) {
		masterData.remove(item);
		wrapListAndAddFiltering();

	}

	public void editBead(Bead selectedBead) {
		if (selectedBead != null) {
			boolean okClicked = mainApp.showBeadEditDialog(selectedBead);
			if (okClicked) {
				beadController.updateBead(selectedBead);
				wrapListAndAddFiltering();
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

}

class MyTableRowFormat extends TableRow<Bead> {
	@Override
	protected void updateItem(Bead item, boolean empty) {
		super.updateItem(item, empty);
		if (item == null) {
			setStyle("");
		} else if (item.getOwned()) {
			setStyle("-fx-background-color: #D4D7B7");
		} else {
			setStyle("");
		}
	}
}