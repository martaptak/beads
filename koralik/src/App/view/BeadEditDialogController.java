package App.view;

import App.BeadController;
import App.CategoryController;
import App.Model.Beads;
import App.Model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BeadEditDialogController {

	@FXML
	private ChoiceBox<String> mainCategoryChoice;
	@FXML
	private ChoiceBox<String> subcategoryChoice;
	@FXML
	private ChoiceBox<String> typeChoice;
	@FXML
	private TextField colorTextField;
	@FXML
	private TextField colorFamilyTextField;
	@FXML
	private TextField sizeTextField;
	@FXML
	private TextField imageUrlTextField;

	private Stage dialogStage;
	private Beads bead;
	private CategoryController categoryController = new CategoryController();
	private boolean okClicked = false;

	@FXML
	private void initialize() {

		mainCategoryChoice.setItems(categoryController.getMainCategoriesName());
		mainCategoryChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> subcategoryChoice.setItems(categoryController.getSubcategoriesNames(newValue)));

		subcategoryChoice.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> typeChoice.setItems(categoryController.getTypesNames(newValue, mainCategoryChoice.getValue())));

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setBead(Beads bead) {
		this.bead = bead;

		mainCategoryChoice.setValue(bead.getMainCategoryName());
		subcategoryChoice.setValue(bead.getSubcategoryName());
		typeChoice.setValue(bead.getCategoryName());
		colorTextField.setText(bead.getColorName());
		colorFamilyTextField.setText(bead.getColorFamily());
		sizeTextField.setText(bead.getSize());
		imageUrlTextField.setText(bead.getImageUrl());

	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {

			bead.setMainCategoryName(mainCategoryChoice.getValue());
			bead.setSubcategoryName(subcategoryChoice.getValue());
			bead.setCategoryName(typeChoice.getValue());
			bead.setColorName(colorTextField.getText());
			bead.setColorFamily(colorFamilyTextField.getText());
			bead.setSize(sizeTextField.getText());
			bead.setImageUrl(imageUrlTextField.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (colorTextField.getText() == null || colorTextField.getText().length() == 0) {
			errorMessage += "Nieprawid這wy kolor!\n";
		}
		if (colorFamilyTextField.getText() == null || colorFamilyTextField.getText().length() == 0) {
			errorMessage += "Nieprawid這wa rodzinna kolorystyczna\n";
		}
		if (sizeTextField.getText() == null || sizeTextField.getText().length() == 0) {
			errorMessage += "Nieprawid這wy rozmiar\n";
		}

		if (imageUrlTextField.getText() == null || imageUrlTextField.getText().length() == 0) {
			errorMessage += "Nieprawid這wy adres zdj璚ia!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

}
