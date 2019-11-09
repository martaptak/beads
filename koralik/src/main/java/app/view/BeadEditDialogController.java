package app.view;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.controlsfx.control.CheckComboBox;

import app.BrandController;
import app.CategoryController;
import app.ColorController;
import app.FinishController;
import app.Main;
import app.ProductsInStoresController;
import app.ShapeController;
import app.model.Bead;
import app.model.Brand;
import app.model.Category;
import app.model.Color;
import app.model.Finish;
import app.model.Shape;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
	private ComboBox<Shape> shapeBox;
	@FXML
	private ComboBox<Brand> brandBox;

	private Stage dialogStage;
	private Main mainApp;
	private Bead bead;
	private boolean okClicked = false;

	private final CategoryController categoryController = new CategoryController();
	private final ColorController colorController = new ColorController();
	private final FinishController finishController = new FinishController();
	private final ShapeController shapeController = new ShapeController();
	private final BrandController brandController = new BrandController();
	private final ProductsInStoresController productController = new ProductsInStoresController();

	private final ObservableList<Color> colors = (ObservableList<Color>) colorController.listColors();
	private final ObservableList<String> units = productController.listOfUnits();

	@FXML
	private CheckComboBox<Finish> finishBox;

	@FXML
	private void initialize() {

		colorComboBox.setEditable(true);
		FilteredList<Color> filteredItems = new FilteredList<>(colors, p -> true);
		colorComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			final TextField editor = colorComboBox.getEditor();
			final Color selected = colorComboBox.getSelectionModel().getSelectedItem();

			if (selected == null || !selected.toString().equals(editor.getText())) {
				Platform.runLater(() -> filteredItems.setPredicate(item -> {

					if (item.toString().toUpperCase().contains(newValue.toUpperCase())) {
						colorComboBox.show();
						return true;
					} else {
						return false;
					}
				}));

			}
		});

		colorComboBox.setItems(filteredItems);
		colorComboBox.setConverter(new StringConverter<>() {

			@Override
			public Color fromString(String arg0) {
				Color color = null;

				for (Color c : filteredItems) {
					if (c.toString().equals(arg0)) {
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
		shapeBox.setItems(shapeController.shapesList());
		brandBox.setItems(brandController.brandsList());

		finishBox.getItems().setAll(finishController.listFinishes());

		mainCategoryChoice.setItems(categoryController.listMainParents());

		mainCategoryChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> subcategoryChoice.setItems(categoryController.listChildren(newValue)));

		subcategoryChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

			ObservableList<Category> list = categoryController.listChildren(newValue);
			typeChoice.setItems(list);
		});

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	public void setBead(Bead bead) {
		this.bead = bead;

		colorComboBox.getSelectionModel().select(bead.getColor());

		for (Finish f : bead.getFinishes()) {
			finishBox.getCheckModel().check(f);
		}
		shapeBox.getSelectionModel().select(bead.getShapes());
		brandBox.getSelectionModel().select(bead.getBrands());
		sizeTextField.setText(bead.getSize());
		imageUrlTextField.setText(bead.getImageUrl());

		if (bead.getCategory() != null) {
			typeChoice.setValue(bead.getTypeCategory());
			subcategoryChoice.setValue(bead.getSubcategory());
			mainCategoryChoice.setValue(bead.getParentCategory());
		}

		if (bead.getAmountOwned() != null) {
			double value = bead.getAmountOwned();
			amountOwned.setText(value == 0.0 ? "" : String.valueOf(value));
		}

		unitsBox.setValue(bead.getUnit());

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

			Set<Finish> newFinishes = new HashSet<>(0);
			newFinishes.addAll(finishBox.getCheckModel().getCheckedItems());

			bead.setFinishes(newFinishes);
			bead.setBrands(brandBox.getSelectionModel().getSelectedItem());
			bead.setShapes(shapeBox.getSelectionModel().getSelectedItem());
			bead.setSize(sizeTextField.getText());
			bead.setImageUrl(imageUrlTextField.getText());
			String value = amountOwned.getText();
			if (value.equals("") || value.isEmpty()) {
				bead.setAmountOwned(0.0);
				bead.setOwned(false);
			} else {
				bead.setAmountOwned(Double.valueOf(value));
				bead.setUnit(unitsBox.getSelectionModel().getSelectedItem());
				bead.setOwned(true);
			}

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
			errorMessage += "Nieprawid�owy rozmiar\n";
		}

		if (imageUrlTextField.getText() == null || imageUrlTextField.getText().length() == 0) {
			errorMessage += "Nieprawid�owy adres zdj�cia!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("B��d!");
			alert.setHeaderText("Popraw nieprawdi�owe pola");
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

	@FXML
	private void handleNewShape() {

		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Nowy kszta�t");
		dialog.setContentText("Podaj nazw� kszta�tu:");

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(name -> {
			Shape newShape = new Shape(name);
			shapeController.addShape(newShape);
			shapeBox.getItems().add(newShape);
			shapeBox.getSelectionModel().select(newShape);
		});
	}

	@FXML
	private void handleNewBrand() {

		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Nowy producent");
		dialog.setContentText("Podaj nazw� producenta:");

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(name -> {
			Brand newBrand = new Brand(name);
			brandController.addBrand(newBrand);
			brandBox.getItems().add(newBrand);
			brandBox.getSelectionModel().select(newBrand);
		});
	}

	@FXML
	private void handleNewFinish() {

		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Nowy finish");
		dialog.setContentText("Podaj nazw�:");

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(name -> {
			Finish newFinish = new Finish(name);
			finishController.addFinish(newFinish);
			finishBox.getItems().add(newFinish);
			finishBox.getCheckModel().check(newFinish);
		});
	}

}
