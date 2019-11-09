package app.view;

import app.CoatingsController;
import app.Main;
import app.model.Coatings;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CoatingsDialogController {

	@FXML
	private ListView<Coatings> list;
	private Stage dialogStage;
	private boolean okClicked = false;
	private final CoatingsController coatingsController = new CoatingsController();
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

		list.setItems(coatingsController.coatingsList());

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

		Coatings tempCoating = new Coatings();
		boolean okClicked = mainApp.showCoatingEditDialog(tempCoating);
		if (okClicked) {
			coatingsController.addCoating(tempCoating);
			list.getItems().add(tempCoating);

		}

	}

	@FXML
	private void handleRemove() {

		Coatings selectedCoating = list.getSelectionModel().getSelectedItem();

		coatingsController.removeCoatings(selectedCoating);
		list.getItems().remove(selectedCoating);

	}

	@FXML
	private void handleEdit() {

		Coatings selectedCoating = list.getSelectionModel().getSelectedItem();
		boolean okClicked = mainApp.showCoatingEditDialog(selectedCoating);
		if (okClicked) {
			coatingsController.updateCoatings(selectedCoating);
			list.setItems(coatingsController.coatingsList());
		}

	}
}
