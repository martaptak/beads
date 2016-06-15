package App.view;

import java.util.Optional;

import App.FinishController;
import App.Main;
import App.Model.Finish;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class FinishEditDialogController {

	@FXML
	private ListView<Finish> list;
	private Stage dialogStage;
	private boolean okClicked = false;
	private FinishController finishController = new FinishController();
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

		list.setItems(finishController.listFinishes());

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

		Finish tempFinish = new Finish();
		boolean okClicked = mainApp.showFinishEditDialog(tempFinish);
		if (okClicked) {
			finishController.addFinish(tempFinish);
			list.getItems().add(tempFinish);

		}

	}

	@FXML
	private void handleRemove() {

		Finish selectedFinish = list.getSelectionModel().getSelectedItem();

		finishController.removeFinish(selectedFinish.getIdFinish());
		list.getItems().remove(selectedFinish);

	}
	
	@FXML
	private void handleEdit(){
		
		Finish selectedFinish = list.getSelectionModel().getSelectedItem();
		boolean okClicked = mainApp.showFinishEditDialog(selectedFinish);
		if (okClicked) {
			finishController.updateFinish(selectedFinish);
			list.setItems(finishController.listFinishes());
		}
		
	}
}
