package app.view;

import java.util.Optional;

import app.SynonimsController;
import app.model.Color;
import app.model.Synonyms;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class SynonimsDialogController {

	@FXML
	private ListView<Synonyms> list;
	private Stage dialogStage;
	private final SynonimsController synonimsController = new SynonimsController();
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

			Synonyms newSynonim = new Synonyms(color, name);
			synonimsController.addSynonym(newSynonim);
			list.getItems().add(newSynonim);
		});

	}

	@FXML
	private void handleRemove() {

		Synonyms selectedSynonim = list.getSelectionModel().getSelectedItem();
		synonimsController.removeSynonym(selectedSynonim);
		list.getItems().remove(selectedSynonim);

	}

	@FXML
	private void handleEdit() {

		Synonyms selectedSynonim = list.getSelectionModel().getSelectedItem();
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Edytuj synonim");
		dialog.setContentText("Podaj nazwê koloru:");
		dialog.getEditor().setText(selectedSynonim.getNameSynonym());
		Optional<String> result = dialog.showAndWait();

		result.ifPresent(name -> {

			Synonyms newSynonim = new Synonyms(color, name);
			synonimsController.addSynonym(newSynonim);
			list.getItems().add(newSynonim);
		});

	}

	public void inicializeList(Color color) {
		list.setItems(synonimsController.synonymsList(color));
	}
}
