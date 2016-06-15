package App.view;

import java.util.Optional;

import App.ColorFamilyController;
import App.Model.ColorFamily;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class ColorFamilyDialogController {

	@FXML
	private ListView<ColorFamily> list;
	private Stage dialogStage;
	private boolean okClicked = false;

	private ColorFamilyController colorFamilyController = new ColorFamilyController();

	public boolean isOkClicked() {
		return okClicked;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void initialize() {

		list.setItems((ObservableList<ColorFamily>) colorFamilyController.listColorFamily());
		

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

		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Nowy kolor");
		dialog.setContentText("Podaj nazwê koloru:");

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(name -> {
			ColorFamily newColor = new ColorFamily(name);
			colorFamilyController.addColorFamily(newColor);
			list.getItems().add(newColor);			
		});
	}

	@FXML
	private void handleRemove() {

		ColorFamily selectedColor = list.getSelectionModel().getSelectedItem();
		
		colorFamilyController.removeColorFamily(selectedColor);
		list.getItems().remove(selectedColor);
		
	}
}
