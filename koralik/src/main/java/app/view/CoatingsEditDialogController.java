package app.view;

import app.model.Coatings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CoatingsEditDialogController {

	@FXML
	private TextField coatingNameField;
	@FXML
	private TextField coatingCodeField;

	private Stage dialogStage;
	private Coatings coating;

	private boolean okClicked = false;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setCoating(Coatings coating) {
		this.coating = coating;

		if (coating.getIdCoating() != null) {

			coatingNameField.setText(coating.getNameCoating());
			coatingCodeField.setText(coating.getCodeCoating());
		}
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {

			coating.setNameCoating(coatingNameField.getText());
			coating.setCodeCoating(coatingCodeField.getText());

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

		if (coatingNameField.getText() == null || coatingNameField.getText().length() == 0) {
			errorMessage += "Nieprawid³owa nazwa \n";
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
