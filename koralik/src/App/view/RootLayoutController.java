package App.view;

import java.io.File;
import java.util.List;
import java.util.Optional;

import App.InsertData;
import App.Main;
import App.StoreController;
import App.Model.Store;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;

public class RootLayoutController {

	private Main mainApp;
	private StoreController storesController = new StoreController();
	private List<Store> storesList = storesController.listStores();

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleEditColors() {
		mainApp.showColorFamilyDialog();
	}

	@FXML
	private void handleEditFinish() {
		mainApp.showFinishDialog();
	}

	@FXML
	private void handleEditStores() {

		mainApp.showStoresDialog();

	}
	
	@FXML
	private void handleEditCoatings(){
		mainApp.showCoatingDialog();
	}
	
	@FXML
	private void handleEditBase(){
		mainApp.showBaseDialog();
	}

	@FXML
	private void handleNewElements() {

		ChoiceDialog<Store> dialog = new ChoiceDialog<>(storesList.get(0), storesList);

		dialog.setTitle("Dodaj nowe elementy");
		dialog.setHeaderText("");
		dialog.setContentText("Wybierz sklep: ");

		Optional<Store> result = dialog.showAndWait();

		result.ifPresent(letter -> {
			File xmlFile = new File("scrapyScripts/marta/kadoro.xml");
			int[] values = InsertData.readXMLFile(result.get(), xmlFile);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Dodano elementy");
			alert.setHeaderText("Aktualizacja bazy zakoñczona powodzeniem");
			String s = "Zaktualizowano " + values[0] + " produktów. \n Do bazy dodano " + values[1] + " z " + values[2]
					+ " elementów";
			alert.setContentText(s);
			alert.show();

		});

	}

	@FXML
	private void handleUpdate() {

		mainApp.showUpdateDialog();

	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}
}
