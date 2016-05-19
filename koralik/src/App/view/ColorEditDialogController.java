package App.view;

import App.ColorFamilyController;
import App.Model.Color;
import App.Model.ColorFamily;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ColorEditDialogController {

	@FXML
	private ComboBox<ColorFamily> colorFamilyBox;
	@FXML
	private TextField colorNameField;
	@FXML
	private TextField colorCodeField;

	private Stage dialogStage;
	private Color color;

	private ColorFamilyController colorFamilyController = new ColorFamilyController();
	private ObservableList<ColorFamily> families = (ObservableList<ColorFamily>) colorFamilyController
			.listColorFamily();
	private boolean okClicked = false;

	@FXML
	private void initialize() {

		colorFamilyBox.setItems(families);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setColor(Color color) {
		this.color = color;

		if (color.getIdColor() != null) {

			colorFamilyBox.setValue(color.getColorFamily());
			colorNameField.setText(color.getColorName());
			colorCodeField.setText(color.getColorCode());
		}
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleOk() {
		if (isInputValid()) {

			color.setColorFamily(colorFamilyBox.getSelectionModel().getSelectedItem());
			color.setColorName(colorNameField.getText());
			color.setColorCode(colorCodeField.getText());			
			
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

		if (colorNameField.getText() == null || colorNameField.getText().length() == 0) {
			errorMessage += "Nieprawid³owa nazwa koloru\n";
		}

		

		if (errorMessage.length() == 0) {
			return true;
		} else {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("B³¹d!");
			alert.setHeaderText("Popraw nieprawdi³owe pola");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

}
