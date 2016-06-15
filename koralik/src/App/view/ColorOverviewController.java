package App.view;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import App.BeadController;
import App.ColorController;
import App.ColorFamilyController;
import App.Main;
import App.Model.Bead;
import App.Model.Color;
import App.Model.ColorFamily;
import App.Model.Finish;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ColorOverviewController {

	@FXML
	private TextField filterField;
	@FXML
	private ChoiceBox<ColorFamily> colorBox;
	@FXML
	private TableView<Color> colorTable;
	@FXML
	private TableView<Bead> beadTable;
	@FXML
	private TableColumn<Bead, String> categoryNameColumn;
	@FXML
	private TableColumn<Bead, String> sizeColumn;
	@FXML
	private TableColumn<Color, String> colorNameColumn;
	@FXML
	private TableColumn<Color, String> colorCodeColumn;
	@FXML
	private TableColumn<Color, String> colorFamilyColumn;
	@FXML
	private TableColumn<Bead, String> mainCategoryName;
	@FXML
	private TableColumn<Bead, String> subcategoryName;
	@FXML
	private TableColumn<Bead, String> finishColumn;
	@FXML
	private ContextMenu contextMenu;
	@FXML
	private MenuItem editColor;
	@FXML
	private MenuItem newColor;
	@FXML
	private MenuItem deleteColor;
	@FXML
	private MenuItem editBead;
	@FXML
	private MenuItem changeColor;
	@FXML
	private MenuItem deleteBead;
	@FXML
	private MenuItem synonims;

	private BeadController beadController = new BeadController();
	private ColorController colorController = new ColorController();
	private ColorFamilyController colorFamilyController = new ColorFamilyController();

	private ObservableList<Color> masterData = (ObservableList<Color>) colorController.listColors();

	private Main mainApp;
	private TabPaneOverviewController tabPaneOverviewController;

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

		wrapListAndAddFiltering();

		colorTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showBeads(newValue));

		editColor.setOnAction(e -> {

			Color selectedColor = colorTable.getSelectionModel().getSelectedItem();
			boolean okClicked = mainApp.showColorEditDialog(selectedColor);
			if (okClicked) {
				colorController.updateColor(selectedColor);

			}

		});

		newColor.setOnAction(e -> {
			Color newColor = new Color();
			boolean okClicked = mainApp.showColorEditDialog(newColor);
			if (okClicked) {
				colorController.addColor(newColor);
				add(newColor);

			}
		});
		
		synonims.setOnAction(e -> {
			Color selectedColor = colorTable.getSelectionModel().getSelectedItem();
			mainApp.showSynonimsDialog(selectedColor);
			
		});

		deleteColor.setOnAction(e -> {
			Color selectedColor = colorTable.getSelectionModel().getSelectedItem();
			colorController.removeColor(selectedColor);
			remove(selectedColor);
		});
		
		changeColor.setOnAction(e -> {
			
			tabPaneOverviewController.showPickColorOverview();
			
			Color pickedColor = tabPaneOverviewController.pickedColor();
			Set<Finish> checkedFinishes = tabPaneOverviewController.checkedFnishes();
			
			Color selectedColor = colorTable.getSelectionModel().getSelectedItem();
			
			List<Bead> beads = beadController.listBeadsByColor(selectedColor);
			
			for(Bead bead: beads){
				bead.setColor(pickedColor);
				bead.setFinishes(checkedFinishes);
				beadController.updateBead(bead);
			}
			
			colorController.removeColor(selectedColor);
			remove(selectedColor);
					
			
		});

		beadTable.setRowFactory(e -> {
			TableRow<Bead> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					mainApp.showBeadDetailDialog(row.getItem());
				}
			});
			return row;
		});

		editBead.setOnAction(e -> {
			Bead selectedBead = beadTable.getSelectionModel().getSelectedItem();
			tabPaneOverviewController.editBead(selectedBead);

		});
		
		deleteBead.setOnAction(e -> {
			Bead selectedBead = beadTable.getSelectionModel().getSelectedItem();
			beadController.removeBead(selectedBead.getIdBeads());
			beadTable.getItems().remove(selectedBead);
		});
		

	}

	public TableView<Color> getColorTable() {
		return this.colorTable;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}
	public void setTabPaneOverviewController(TabPaneOverviewController tabPaneOverviewController) {
		this.tabPaneOverviewController = tabPaneOverviewController;

	}

	private void showBeads(Color color) {

		if (color != null) {
			beadTable.setItems(beadController.listBeadsByColor(color));

			mainCategoryName.setCellValueFactory(new PropertyValueFactory<Bead, String>("mainCategoryName"));
			subcategoryName.setCellValueFactory(new PropertyValueFactory<Bead, String>("subcategoryName"));
			categoryNameColumn.setCellValueFactory(new PropertyValueFactory<Bead, String>("categoryName"));
			sizeColumn.setCellValueFactory(new PropertyValueFactory<Bead, String>("size"));
			finishColumn.setCellValueFactory(new PropertyValueFactory<Bead, String>("FinishesNames"));

		}

	}

	private void add(Color item) {
		masterData.add(item);
		wrapListAndAddFiltering();

	}

	private void remove(Color item) {
		masterData.remove(item);
		wrapListAndAddFiltering();

	}

	private void wrapListAndAddFiltering() {
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
	}

	public void selectColor(Color color) {
		colorTable.requestFocus();
		colorTable.getSelectionModel().select(color);
		colorTable.getFocusModel().focus(colorTable.getSelectionModel().getSelectedIndex());
	}

}
