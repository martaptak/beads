package App.view;

import java.util.HashSet;

import App.BeadController;
import App.ColorController;
import App.ColorFamilyController;
import App.Main;
import App.Model.Beads;
import App.Model.Color;
import App.Model.ColorFamily;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

public class ColorOverviewController {

	@FXML
	private TextField filterField;
	@FXML
	private ChoiceBox<ColorFamily> colorBox;
	@FXML
	private TableView<Color> colorTable;
	@FXML
	private TableView<Beads> beadTable;
	@FXML
	private TableColumn<Beads, String> categoryNameColumn;
	@FXML
	private TableColumn<Beads, String> sizeColumn;
	@FXML
	private TableColumn<Color, String> colorNameColumn;
	@FXML
	private TableColumn<Color, String> colorCodeColumn;
	@FXML
	private TableColumn<Color, String> colorFamilyColumn;
	@FXML
	private TableColumn<Beads, String> mainCategoryName;
	@FXML
	private TableColumn<Beads, String> subcategoryName;
	@FXML
	private TableColumn<Beads, String> finishColumn;
	@FXML
	private ContextMenu contextMenu;
	@FXML
	private MenuItem detail;

	private BeadController beadController = new BeadController();
	private ColorController colorController = new ColorController();
	private ColorFamilyController colorFamilyController = new ColorFamilyController();

	private ObservableList<Color> masterData = (ObservableList<Color>) colorController.listColors();

	private Main mainApp;

	public ColorOverviewController() {
	}

	@FXML
	private void initialize() {

		colorNameColumn.setCellValueFactory(new PropertyValueFactory<Color, String>("colorName"));
		colorCodeColumn.setCellValueFactory(new PropertyValueFactory<Color, String>("colorCode"));
		colorFamilyColumn.setCellValueFactory(new PropertyValueFactory<Color, String>("colorFamilyName"));
		colorBox.getItems().setAll(colorFamilyController.listColorFamily());
		colorBox.getItems().add(0, new ColorFamily("Wszystkie", new HashSet<Color>()));
		colorBox.getSelectionModel().select(0);

		FilteredList<Color> filteredData = new FilteredList<>(masterData, p -> true);

		filteredData.predicateProperty()
				.bind(Bindings.createObjectBinding(
						() -> color -> (color.getColorName().toLowerCase().contains(filterField.getText().toLowerCase())
								|| color.getColorCode().toLowerCase().contains(filterField.getText().toLowerCase()))
								&& (color.getColorFamily().equals(colorBox.getSelectionModel().getSelectedItem())
										|| colorBox.getSelectionModel().getSelectedIndex() == 0),

						filterField.textProperty(), colorBox.getSelectionModel().selectedItemProperty()));

		SortedList<Color> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(colorTable.comparatorProperty());
		colorTable.setItems(sortedData);

		colorTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showBeads(newValue));

		detail.setOnAction(e -> {

			Beads selectedBead = beadTable.getSelectionModel().getSelectedItem();
			mainApp.showBeadDetailDialog(selectedBead);

		});

	}

	public TableView<Color> getColorTable() {
		return this.colorTable;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	private void showBeads(Color color) {

		if (color != null) {
			beadTable.setItems(beadController.listBeadsByColor(color));

			mainCategoryName.setCellValueFactory(new PropertyValueFactory<Beads, String>("mainCategoryName"));
			subcategoryName.setCellValueFactory(new PropertyValueFactory<Beads, String>("subcategoryName"));
			categoryNameColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("categoryName"));
			sizeColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("size"));
			finishColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("FinishesNames"));

		}

	}

	public void selectColor(Color color) {
		colorTable.requestFocus();
		colorTable.getSelectionModel().select(color);
		colorTable.getFocusModel().focus(null);
	}

}
