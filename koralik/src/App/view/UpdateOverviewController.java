package App.view;

import App.StoresController;
import App.Model.Stores;

import java.io.IOException;
import java.util.Optional;

import App.InsertData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

public class UpdateOverviewController {

	@FXML
	private ComboBox<Stores> storesBox;
	@FXML
	private ProgressIndicator progressIndicator;
	@FXML
	private Label progressLabel;
	@FXML
	private Button updateButton;

	private StoresController storesController = new StoresController();
	private ObservableList<Stores> storesList = storesController.listStores();
	private Stage dialogStage;

	private enum State {
		START, IN_PROGRESS, DONE
	};

	private State progress = State.START;

	@FXML
	private void initialize() {

		storesBox.getItems().setAll(storesList);
		storesBox.getItems().add(0, new Stores("Wszystkie"));
		storesBox.getSelectionModel().select(0);

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void handleOk() {

		switch (progress) {
		case START:
			start();
			break;
		case IN_PROGRESS:
			progress();
			break;
		default:
			break;

		}

	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private void start() {
		progress = State.IN_PROGRESS;
		progressIndicator.setVisible(true);
		progressLabel.setVisible(true);
		updateButton.setText("Przerwij");

		try {

			if (storesBox.getSelectionModel().getSelectedIndex() == 0) {

				for (Stores s : storesList) {
					progressLabel.setText("Pobieram dla: " + s.getStoreName());
					InsertData.startScrapy(s);
					if (progress == State.START) {
						break;
					}
				}

			} else {
				progressLabel
						.setText("Pobieram dla: " + storesBox.getSelectionModel().getSelectedItem().getStoreName());
				InsertData.startScrapy(storesBox.getSelectionModel().getSelectedItem());

			}

			if (progress == State.IN_PROGRESS) {
				progressLabel.setText("Pobieranie zakoñczone");
				progress = State.DONE;
				done();
				progress = State.START;
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void progress() {
		progress = State.START;
		progressLabel.setText("Przerywanie procesu...");
		InsertData.stopScrapy();
		progressIndicator.setVisible(false);
		progressLabel.setVisible(false);
		progressLabel.setText("Uruchamiam....");
		updateButton.setText("Aktualizuj");

	}

	private void done() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(dialogStage);
		alert.setTitle("Aktualizacja bazy");
		alert.setHeaderText("Proces zakoñczony");
		alert.setContentText("Czy chcesz przejœæ do przegl¹dania nowych produktów?");

		Optional<ButtonType> result = alert.showAndWait();

		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {

		    
		}

		
	}
}
