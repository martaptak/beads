package App.view;

import App.StoresController;
import App.Model.ProductsInStores;
import App.Model.Stores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ProductEditDialogController {

	@FXML
	private ComboBox<Stores> stores;
	@FXML
	private TextField amount;
	@FXML
	private ComboBox<String> unit;
	@FXML
	private TextField website;
	@FXML
	private ComboBox<Boolean> avibility;

	private Stage dialogStage;
	private ProductsInStores product;
	private boolean okClicked = false;
	private StoresController storesController = new StoresController();
	private ObservableList<Boolean> options = FXCollections.observableArrayList(true, false);
	private ObservableList<String> units = FXCollections.observableArrayList("g", "szt");

	@FXML
	private void initialize() {

		avibility.setConverter(new StringConverter<Boolean>() {

			@Override
			public String toString(Boolean bool) {
				if (bool == null) {
					return "";
				} else if (bool == true) {
					return "tak";
				} else {
					return "nie";
				}

			}

			@Override
			public Boolean fromString(String s) {
				try {
					if (s == "tak") {
						return true;
					} else {
						return false;
					}
				} catch (NumberFormatException e) {
					return null;
				}
			}

		});

		avibility.setItems(options);
		stores.setItems(storesController.listStores());
		unit.setItems(units);

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setProduct(ProductsInStores product) {
		this.product = product;

		stores.setValue(product.getStores());
		amount.setText(String.valueOf(product.getAmount()));
		unit.setValue(product.getUnit());
		website.setText(product.getWebsite());
		avibility.setValue(product.getAvibility());

	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {

			product.setStores(stores.getSelectionModel().getSelectedItem());
			product.setAmount(Integer.valueOf(amount.getText()));
			product.setUnit(unit.getSelectionModel().getSelectedItem());
			product.setWebsite(website.getText());
			product.setAvibility(avibility.getSelectionModel().getSelectedItem());
			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (amount.getText() == null || amount.getText().length() == 0 || !isNumeric(amount.getText())) {
			errorMessage += "Nieprawid³owa iloœæ\n";
		}
		if (stores.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "Wybierz sklep\n";
		}
		if (unit.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "Wybierz jednostkê\n";
		}
		if (website.getText() == null || website.getText().length() == 0 || !website.getText().contains("www")) {
			errorMessage += "Nieprawid³owy adres storny\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {

			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Niaprawid³owe pola");
			alert.setHeaderText("Popraw nieprawid³owe pola");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	@SuppressWarnings("unused")
	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
