package App.view;

import java.util.Optional;

import App.SynonimsController;
import App.Model.Color;
import App.Model.Synonims;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class SynonimsDialogController {

	@FXML
	private ListView<Synonims> list;
	private Stage dialogStage;
	private SynonimsController synonimsController = new SynonimsController();
	private Color color;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}	

	@FXML
	private void initialize() {

		

	}

	public void setColor(Color color) {
		this.color = color;
	}

	@FXML
	private void handleOK() {

		dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	@FXML
	private void handleNew() {

		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Nowy synonim");
		dialog.setContentText("Podaj nazwê koloru:");

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(name -> {
			
			Synonims newSynonim = new Synonims(color, name);
			synonimsController.addSynonim(newSynonim);
			list.getItems().add(newSynonim);			
		});

	}

	@FXML
	private void handleRemove() {

		Synonims selectedSynonim = list.getSelectionModel().getSelectedItem();
		synonimsController.removeSynonim(selectedSynonim);
		list.getItems().remove(selectedSynonim);

	}

	@FXML
	private void handleEdit() {

		Synonims selectedSynonim = list.getSelectionModel().getSelectedItem();
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Edytuj synonim");
		dialog.setContentText("Podaj nazwê koloru:");
		dialog.getEditor().setText(selectedSynonim.getNameSynonim());
		Optional<String> result = dialog.showAndWait();

		result.ifPresent(name -> {
			
			Synonims newSynonim = new Synonims(color, name);
			synonimsController.addSynonim(newSynonim);
			list.getItems().add(newSynonim);			
		});

	}
	
	public void inicializeList(Color color){
		list.setItems(synonimsController.synonimsList(color));
	}
}
