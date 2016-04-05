package App.view;

import App.BeadController;
import App.Main;
import App.Model.Beads;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BeadsOverviewController {

	@FXML
	private TableView<Beads> beadsTable;
	@FXML
	private TableColumn<Beads, String> categoryNameColumn;
	@FXML
	private TableColumn<Beads, String> sizeColumn;
	@FXML
	private TableColumn<Beads, String> colorNameColumn;
	@FXML
	private TableColumn<Beads, String> imageUrlColumn;
	@FXML
	private TableColumn<Beads, String> mainCategoryName;
	@FXML
	private TableColumn<Beads, String> subcategoryName;
	@FXML
	private ImageView beadsImage;
	@FXML
	private Label typeLabel;
	@FXML
	private Label colorLabel;
	@FXML
	private Label colorFamilyLabel;
	@FXML
	private Label sizeLabel;

	private BeadController beadController = new BeadController();

	private Main mainApp;

	public BeadsOverviewController() {

	}

	@FXML
	private void initialize() {

		mainCategoryName.setCellValueFactory(new PropertyValueFactory<Beads, String>("mainCategoryName"));
		subcategoryName.setCellValueFactory(new PropertyValueFactory<Beads, String>("subcategoryName"));
		categoryNameColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("categoryName"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("size"));
		colorNameColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("colorName"));
		imageUrlColumn.setCellValueFactory(new PropertyValueFactory<Beads, String>("imageUrl"));

		showBeadDetails(null);

		beadsTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showBeadDetails(newValue));
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		beadsTable.getItems().setAll(beadController.listBeadsFotTable());
	}

	private void showBeadDetails(Beads bead) {
		if (bead != null) {

			typeLabel.setText(bead.getCategoryName());
			colorLabel.setText(bead.getColorName());
			colorFamilyLabel.setText(bead.getColorFamily());
			sizeLabel.setText(bead.getSize());
			beadsImage.setImage(new Image(bead.getImageUrl()));

		} else {
		
			typeLabel.setText("");
			colorLabel.setText("");
			colorFamilyLabel.setText("");
			sizeLabel.setText("");

		}
	}

	@FXML
	private void handleDeleteBead() {
		int selectedIndex = beadsTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			beadsTable.getItems().remove(selectedIndex);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("Nie zosta³ zaznaczony koralik");
			alert.setContentText("Wybierz koralik z tabeli");

			alert.showAndWait();
		}

	}

	/*
	 * @FXML private void handleNewBead() { Beads tempBead = new Beads();
	 * boolean okClicked = mainApp.showPersonEditDialog(tempBead); if
	 * (okClicked) { beadController.addBead(tempBead);; } }
	 */

	@FXML
	private void handleEditBead() {
	    Beads selectedBead = beadsTable.getSelectionModel().getSelectedItem();
	    if (selectedBead != null) {
	        boolean okClicked = mainApp.showPersonEditDialog(selectedBead);
	        if (okClicked) {
	            showBeadDetails(selectedBead);
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
