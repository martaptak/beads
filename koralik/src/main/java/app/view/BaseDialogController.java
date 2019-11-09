package app.view;

import app.BaseController;
import app.Main;
import app.model.Base;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class BaseDialogController {

	@FXML
	private ListView<Base> list;
	private Stage dialogStage;
	private boolean okClicked = false;
	private final BaseController baseController = new BaseController();
	private Main mainApp;

	public boolean isOkClicked() {
		return okClicked;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	@FXML
	private void initialize() {

		list.setItems(baseController.basesList());

	}

	@FXML
	private void handleOK() {
		okClicked = true;
		dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	@FXML
	private void handleNew() {

		Base tempBase = new Base();
		boolean okClicked = mainApp.showBaseEditDialog(tempBase);
		if (okClicked) {
			baseController.addBase(tempBase);
			list.getItems().add(tempBase);

		}

	}

	@FXML
	private void handleRemove() {

		Base selectedBase = list.getSelectionModel().getSelectedItem();

		baseController.removeBase(selectedBase);
		list.getItems().remove(selectedBase);

	}

	@FXML
	private void handleEdit() {

		Base selectedBase = list.getSelectionModel().getSelectedItem();
		boolean okClicked = mainApp.showBaseEditDialog(selectedBase);
		if (okClicked) {
			baseController.updateBase(selectedBase);
			list.setItems(baseController.basesList());
		}

	}
}
