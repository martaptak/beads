package App.view;

import App.Model.Base;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BaseEditDialogController {

	@FXML
	private TextField baseNameField;
	@FXML
	private TextField baseCodeField;

	private Stage dialogStage;
	private Base base;

	private boolean okClicked = false;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setBase(Base base) {
		this.base = base;

		if (base.getIdBase() != null) {

			baseNameField.setText(base.getNameBase());
			baseCodeField.setText(base.getCodeBase());
		}
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {

			base.setNameBase(baseNameField.getText());
			base.setCodeBase(baseCodeField.getText());

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

		if (baseNameField.getText() == null || baseNameField.getText().length() == 0) {
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
