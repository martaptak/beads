package App.view;

import App.Model.Finish;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FinishNewDialogController {

	@FXML
	private TextField finishNameField;
	@FXML
	private TextField finishCodeField;

	private Stage dialogStage;
	private Finish finish;

	private boolean okClicked = false;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setFinish(Finish finish) {
		this.finish = finish;

		if (finish.getIdFinish() != null) {

			finishNameField.setText(finish.getNameFinish());
			finishCodeField.setText(finish.getCodeFinish());
		}
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {

			finish.setNameFinish(finishNameField.getText());
			finish.setCodeFinish(finishCodeField.getText());

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

		if (finishNameField.getText() == null || finishNameField.getText().length() == 0) {
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
