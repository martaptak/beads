package app.view;

import app.StoreController;
import app.model.Store;

import java.io.IOException;
import java.util.Optional;

import app.InsertData;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class UpdateOverviewController {

	@FXML
	private ComboBox<Store> storesBox;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label progressLabel;
	@FXML
	private Button updateButton;

	private final StoreController storesController = new StoreController();
	private final ObservableList<Store> storesList = storesController.listStores();
	private Stage dialogStage;

	private enum State {
		START, IN_PROGRESS, DONE
	}

	private State progress = State.START;

	@FXML
	private void initialize() {

		storesBox.getItems().setAll(storesList);
		storesBox.getItems().add(0, new Store("Wszystkie"));
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
		progressBar.setVisible(true);
		progressLabel.setVisible(true);
		updateButton.setText("Przerwij");

		Task<Void> task1 = new Task<>() {
			@Override
			public Void call() {
				try {
					InsertData.startScrapy(storesBox.getSelectionModel().getSelectedItem());
				} catch (IOException e) {

					e.printStackTrace();
				}
				return null;
			}
		};
		Thread thread = new Thread(task1);

		try {

			if (storesBox.getSelectionModel().getSelectedIndex() == 0) {

				for (Store s : storesList) {
					progressLabel.setText("Pobieram dla: " + s.getStoreName());
					InsertData.startScrapy(s);
					if (progress == State.START) {
						break;
					}
				}

			} else {
				progressLabel
						.setText("Pobieram dla: " + storesBox.getSelectionModel().getSelectedItem().getStoreName());

				progressBar.progressProperty().bind(task1.progressProperty());
				thread.start();

			}

			if (progress == State.IN_PROGRESS && !thread.isAlive()) {
				progressLabel.setText("Pobieranie zako�czone");
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
		progressBar.setVisible(false);
		progressLabel.setVisible(false);
		progressLabel.setText("Uruchamiam....");
		updateButton.setText("Aktualizuj");

	}

	private void done() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(dialogStage);
		alert.setTitle("Aktualizacja bazy");
		alert.setHeaderText("Proces zako�czony");
		alert.setContentText("Czy chcesz przej�� do przegl�dania nowych produkt�w?");

		Optional<ButtonType> result = alert.showAndWait();


	}
}