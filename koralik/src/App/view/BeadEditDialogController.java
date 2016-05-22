package App.view;

import App.CategoryController;
import App.ColorController;
import App.Main;
import App.ProductsInStoresController;
import App.Model.Beads;
import App.Model.Category;
import App.Model.Color;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class BeadEditDialogController {

	@FXML
	private ComboBox<Category> mainCategoryChoice;
	@FXML
	private ComboBox<Category> subcategoryChoice;
	@FXML
	private ComboBox<Category> typeChoice;
	@FXML
	private ComboBox<Color> colorComboBox;
	@FXML
	private TextField sizeTextField;
	@FXML
	private TextField imageUrlTextField;
	@FXML
	private TextField amountOwned;
	@FXML
	private ComboBox<String> unitsBox;
	@FXML
	private ComboBox<Boolean> ownedBox;

	private Stage dialogStage;
	private Main mainApp;
	private Beads bead;
	private boolean okClicked = false;

	private CategoryController categoryController = new CategoryController();
	private ColorController colorController = new ColorController();
	private ProductsInStoresController productController = new ProductsInStoresController();

	private ObservableList<Color> colors = (ObservableList<Color>) colorController.listColors();
	private ObservableList<Boolean> options = FXCollections.observableArrayList(true, false);
	private ObservableList<String> units = productController.listOfUnits();

	@FXML
	private void initialize() {

		ownedBox.setConverter(new StringConverter<Boolean>() {

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

		colorComboBox.setEditable(true);
		FilteredList<Color> filteredItems = new FilteredList<Color>(colors, p -> true);
		colorComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			final TextField editor = colorComboBox.getEditor();
			final Color selected = colorComboBox.getSelectionModel().getSelectedItem();

			if (selected == null || !selected.toString().equals(editor.getText())) {
				Platform.runLater(() -> {
					filteredItems.setPredicate(item -> {

						if (item.toString().toUpperCase().contains(newValue.toUpperCase())) {
							colorComboBox.show();
							return true;
						} else {
							return false;
						}
					});

				});

			}
		});

		colorComboBox.setItems(filteredItems);
		colorComboBox.setConverter(new StringConverter<Color>() {

			@Override
			public Color fromString(String arg0) {
				Color color = null;

				for (Color c : filteredItems) {
					if (c.toString().equals(arg0.toString())) {
						color = c;
					}
				}

				return color;
			}

			@Override
			public String toString(Color arg0) {
				if (arg0 != null) {
					return arg0.toString();
				} else {
					return " ";
				}
			}

		});
		unitsBox.setItems(units);
		ownedBox.setItems(options);

		mainCategoryChoice.setItems(categoryController.listMainParents());

		mainCategoryChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
			@Override
			public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {

				subcategoryChoice.setItems(categoryController.listChildren(newValue));
			}
		});

		subcategoryChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
			@Override
			public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {

				ObservableList<Category> list = categoryController.listChildren(newValue);
				typeChoice.setItems(list);
			}
		});

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	public void setBead(Beads bead) {
		this.bead = bead;

		if (bead.getIdBeads() != null) {

			// colorComboBox.setValue(bead.getColor());
			colorComboBox.getSelectionModel().select(bead.getColor());
			// colorComboBox.getSelectionModel().se
			sizeTextField.setText(bead.getSize());
			imageUrlTextField.setText(bead.getImageUrl());
			ownedBox.setValue(bead.getOwned());
			typeChoice.setValue(bead.getCategory());
			subcategoryChoice.setValue(bead.getSubcategory());
			mainCategoryChoice.setValue(bead.getParentCategory());
			amountOwned.setText(String.valueOf(bead.getAmountOwned()));
			unitsBox.setValue(bead.getUnit());
		}

	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {

			bead.setCategory(typeChoice.getSelectionModel().getSelectedItem());
			bead.setSubcategory(subcategoryChoice.getSelectionModel().getSelectedItem());
			bead.setParentCategory(mainCategoryChoice.getSelectionModel().getSelectedItem());
			bead.setColor(colorComboBox.getSelectionModel().getSelectedItem());
			bead.setSize(sizeTextField.getText());
			bead.setImageUrl(imageUrlTextField.getText());
			bead.setOwned(ownedBox.getValue());
			bead.setAmountOwned(Double.valueOf(amountOwned.getText()));
			bead.setUnit(unitsBox.getSelectionModel().getSelectedItem());
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

		if (sizeTextField.getText() == null || sizeTextField.getText().length() == 0) {
			errorMessage += "Nieprawid³owy rozmiar\n";
		}

		if (imageUrlTextField.getText() == null || imageUrlTextField.getText().length() == 0) {
			errorMessage += "Nieprawid³owy adres zdjêcia!\n";
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

	@FXML
	private void handleNewColor() {		
		
		Color newColor = new Color();
		boolean okClicked = mainApp.showColorEditDialog(newColor);
		if (okClicked) {
			colorController.addColor(newColor);
			colorComboBox.getItems().add(newColor);			
			colorComboBox.getSelectionModel().select(newColor);
		}

	}
}
