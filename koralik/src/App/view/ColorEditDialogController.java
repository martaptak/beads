package App.view;

import App.BaseController;
import App.CoatingsController;
import App.ColorFamilyController;
import App.Main;
import App.Model.Base;
import App.Model.Coatings;
import App.Model.Color;
import App.Model.ColorFamily;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ColorEditDialogController {

	@FXML
	private ComboBox<ColorFamily> colorFamilyBox;
	@FXML
	private TextField colorNameField;
	@FXML
	private TextField colorCodeField;
	@FXML
	private ComboBox<Base> baseBox;
	@FXML
	private ComboBox<Coatings> coatingsBox;

	private Stage dialogStage;
	private Color color;

	private ColorFamilyController colorFamilyController = new ColorFamilyController();
	private BaseController baseController = new BaseController();
	private CoatingsController coatingsController = new CoatingsController();
	private ObservableList<ColorFamily> families = (ObservableList<ColorFamily>) colorFamilyController
			.listColorFamily();
	private ObservableList<Base> bases = baseController.basesList();
	private ObservableList<Coatings> coatings = coatingsController.coatingsList();
	private boolean okClicked = false;
	private Main mainApp;

	@FXML
	private void initialize() {

		colorFamilyBox.setItems(families);
		baseBox.setEditable(true);
		setBaseBox();

		coatingsBox.setEditable(true);
		setCoatingBox();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setColor(Color color) {
		this.color = color;

		if (color.getIdColor() != null) {

			colorFamilyBox.setValue(color.getColorFamily());
			colorNameField.setText(color.getColorName());
			colorCodeField.setText(color.getColorCode());
			baseBox.setValue(color.getBase());
			coatingsBox.setValue(color.getCoatings());
		}
	}
	
	public void setBaseBox(){
		
		FilteredList<Base> filteredItems = new FilteredList<Base>(bases, p -> true);
		baseBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			final TextField editor = baseBox.getEditor();
			final Base selected = baseBox.getSelectionModel().getSelectedItem();

			if (selected == null || !selected.toString().equals(editor.getText())) {
				Platform.runLater(() -> {
					filteredItems.setPredicate(item -> {

						if (item.toString().toUpperCase().contains(newValue.toUpperCase())) {
							baseBox.show();
							return true;
						} else {
							return false;
						}
					});

				});

			}
		});

		baseBox.setItems(filteredItems);
		baseBox.setConverter(new StringConverter<Base>() {

			@Override
			public Base fromString(String arg0) {
				Base base = null;

				for (Base c : filteredItems) {
					if (c.toString().equals(arg0.toString())) {
						base = c;
					}
				}

				return base;
			}

			@Override
			public String toString(Base arg0) {
				if (arg0 != null) {
					return arg0.toString();
				} else {
					return " ";
				}
			}

		});
	}
	
	public void setCoatingBox(){
		FilteredList<Coatings> filteredCoatings = new FilteredList<Coatings>(coatings, p -> true);
		coatingsBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			final TextField editor = coatingsBox.getEditor();
			final Coatings selected = coatingsBox.getSelectionModel().getSelectedItem();

			if (selected == null || !selected.toString().equals(editor.getText())) {
				Platform.runLater(() -> {
					filteredCoatings.setPredicate(item -> {

						if (item.toString().toUpperCase().contains(newValue.toUpperCase())) {
							coatingsBox.show();
							return true;
						} else {
							return false;
						}
					});

				});

			}
		});

		coatingsBox.setItems(filteredCoatings);
		coatingsBox.setConverter(new StringConverter<Coatings>() {

			@Override
			public Coatings fromString(String arg0) {
				Coatings coating = null;

				for (Coatings c : filteredCoatings) {
					if (c.toString().equals(arg0.toString())) {
						coating = c;
					}
				}

				return coating;
			}

			@Override
			public String toString(Coatings arg0) {
				if (arg0 != null) {
					return arg0.toString();
				} else {
					return " ";
				}
			}
		});
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {

			color.setColorFamily(colorFamilyBox.getSelectionModel().getSelectedItem());
			color.setColorName(colorNameField.getText());
			color.setColorCode(colorCodeField.getText());
			color.setBase(baseBox.getSelectionModel().getSelectedItem());
			color.setCoatings(coatingsBox.getSelectionModel().getSelectedItem());

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

		if (colorNameField.getText() == null || colorNameField.getText().length() == 0) {
			errorMessage += "Nieprawid³owa nazwa koloru\n";
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
	private void handleNewBase() {

		Base tempBase = new Base();
		boolean okClicked = mainApp.showBaseEditDialog(tempBase);
		if (okClicked) {
			baseController.addBase(tempBase);
			bases.add(tempBase);
			setBaseBox();
		}
	}

	@FXML
	private void handleNewCoating() {

		Coatings tempCoating = new Coatings();
		boolean okClicked = mainApp.showCoatingEditDialog(tempCoating);
		if (okClicked) {
			coatingsController.addCoating(tempCoating);
			coatings.add(tempCoating);
			setCoatingBox();
		}
	}

}
