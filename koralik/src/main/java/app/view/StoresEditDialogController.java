package app.view;

import app.CountryController;
import app.model.Country;
import app.model.Store;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;

@SuppressWarnings("WeakerAccess")
public class StoresEditDialogController {

	@FXML
	private TextField nameField;
	@FXML
	private TextField websiteField;
	@FXML
	private ComboBox<Country> countryBox;

	private final CountryController countryController = new CountryController();
	private final ObservableList<Country> countryList = countryController.countriesList();
	private Stage dialogStage;
	private boolean okClicked = false;
	private Store store;

	@FXML
	private void initialize() {

		countryBox.setEditable(true);
		FilteredList<Country> filteredItems = new FilteredList<>(countryList, p -> true);

		countryBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			final TextField editor = countryBox.getEditor();
			final Country selected = countryBox.getSelectionModel().getSelectedItem();

			if (selected == null || !selected.toString().equals(editor.getText())) {
				Platform.runLater(() -> filteredItems.setPredicate(item -> {

					if (item.toString().toUpperCase().contains(newValue.toUpperCase())) {
						countryBox.show();
						return true;
					} else {
						return false;
					}
				}));

			}
		});

		countryBox.setItems(filteredItems);
		countryBox.setConverter(new StringConverter<>() {

			@Override
			public Country fromString(String arg0) {
				Country country = null;

				for (Country c : filteredItems) {
					if (c.toString().equals(arg0)) {
						country = c;
					}
				}

				return country;
			}

			@Override
			public String toString(Country arg0) {
				if (arg0 != null) {
					return arg0.toString();
				} else {
					return " ";
				}
			}

		});

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
		if (isInputValid()) {
			store.setCountries(countryBox.getSelectionModel().getSelectedItem());
			store.setStoreName(nameField.getText());
			store.setWebsite(websiteField.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	public void setStore(Store store) {
		this.store = store;

		if (store.getIdStores() != null) {
			nameField.setText(store.getStoreName());
			websiteField.setText(store.getWebsite());
			countryBox.getSelectionModel().select(store.getCountries());


		}
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (nameField.getText() == null || nameField.getText().length() == 0) {
			errorMessage += "Nieprawid³owa nazwa\n";
		}

		if (websiteField.getText() == null || websiteField.getText().length() == 0
				|| !websiteField.getText().contains("http")) {
			errorMessage += "Nieprawid³owy adres strony!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("B³¹d!");
			alert.setHeaderText("Popraw nieprawdi³owe pola");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

}
