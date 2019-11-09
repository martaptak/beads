package app.view;

import java.util.List;

import app.BeadController;
import app.model.Bead;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class PickBeadDialogController {
	@FXML
	private ListView<Bead> beadsList;

	private final BeadController beadController = new BeadController();
	private Stage dialogStage;
	private boolean okClicked = false;
	private List<Bead> pickedBeads;

	@FXML
	private void initialize() {

		beadsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public List<Bead> pickedBeads() {
		return pickedBeads;
	}

	@FXML
	private void handleOk() {

		pickedBeads = beadsList.getSelectionModel().getSelectedItems();
		okClicked = true;
		dialogStage.close();
	}

	public void createList(Bead selectedBead) {

		beadsList.setItems(beadController.listBeadsForTable(selectedBead.getCategory()));
		beadsList.getItems().remove(selectedBead);
	}
}
