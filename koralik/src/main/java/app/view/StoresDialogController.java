package app.view;

import java.io.IOException;

import app.Main;
import app.StoreController;
import app.model.Store;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StoresDialogController {

	@FXML
	private ListView<Store> listView;

	private final StoreController storeController = new StoreController();
	private final ObservableList<Store> storesList = storeController.listStores();
	private Stage dialogStage;
	private Store store;

	@FXML
	private void initialize() {

		listView.setItems(storesList);

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	@FXML
	private void handleOk() {
		dialogStage.close();
	}

	@FXML
	private void handleNewStore() {
		Store tempStore = new Store();
		boolean okClicked = showProductEditDialog(tempStore);

		if (okClicked) {
			storeController.addStore(tempStore);
			listView.getItems().add(tempStore);
		}

	}

	@FXML
	private void handleEditStore() {

		Store selectedStore = listView.getSelectionModel().getSelectedItem();
		boolean okClicked = showProductEditDialog(selectedStore);
		if (selectedStore != null) {
			if (okClicked) {
				storeController.updateStore(selectedStore);
				listView.getItems().set(listView.getSelectionModel().getSelectedIndex(), selectedStore);

			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(dialogStage);
			alert.setTitle("Nie wybrano");
			alert.setHeaderText("Sklep nie wybrany");
			alert.setContentText("Zaznacz sklep na liœcie");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleDeleteStore() {

		int selectedIndex = listView.getSelectionModel().getSelectedIndex();
		Store currentStore = listView.getSelectionModel().selectedItemProperty().getValue();
		int id = currentStore.getIdStores();
		if (selectedIndex >= 0) {
			storeController.removeStore(id);
			listView.getItems().remove(selectedIndex);

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(dialogStage);
			alert.setTitle("Nie wybrano");
			alert.setHeaderText("Sklep nie wybrany");
			alert.setContentText("Zaznacz sklep na liœcie");

			alert.showAndWait();
		}

	}

	private boolean showProductEditDialog(Store store) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/StoresEditDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edytuj...");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.dialogStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			StoresEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setStore(store);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
