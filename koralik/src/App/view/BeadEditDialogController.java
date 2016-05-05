package App.view;

import App.CategoryController;
import App.ColorController;
import App.Model.Beads;
import App.Model.Category;
import App.Model.Color;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ComboBox<Boolean> ownedBox;

	private Stage dialogStage;
	private Beads bead;
	private CategoryController categoryController = new CategoryController();
	private ColorController colorController = new ColorController();
	private boolean okClicked = false;

	private ObservableList<Color> colors = (ObservableList<Color>) colorController.listColors();
	private ObservableList<Boolean> options = FXCollections.observableArrayList(true, false);

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

		colorComboBox.setItems(colors);

		ownedBox.setItems(options);

		mainCategoryChoice.setItems(categoryController.listMainParents());
		//mainCategoryChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				//newValue) -> subcategoryChoice.setItems(categoryController.listChildren(newValue)));
		
		
		mainCategoryChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
		    @Override
		    public void changed(ObservableValue<? extends Category> observable,
		            Category oldValue, Category newValue) {

		    	subcategoryChoice.setItems(categoryController.listChildren(newValue));
		    }
		});

		//subcategoryChoice.getSelectionModel().selectedItemProperty().addListener(
				//(observable, oldValue, newValue) -> typeChoice.setItems(categoryController.listChildren(newValue)));
		
		subcategoryChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
		    @Override
		    public void changed(ObservableValue<? extends Category> observable,
		            Category oldValue, Category newValue) {

		    	ObservableList<Category> list = categoryController.listChildren(newValue);
		    	typeChoice.setItems(list);
		    }
		});

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setBead(Beads bead) {
		this.bead = bead;
		
		if(bead.getIdBeads() != null){
		colorComboBox.setValue(bead.getColor());
		sizeTextField.setText(bead.getSize());
		imageUrlTextField.setText(bead.getImageUrl());
		ownedBox.setValue(bead.getOwned());		
		typeChoice.setValue(bead.getCategory());
		subcategoryChoice.setValue(bead.getSubcategory());
		mainCategoryChoice.setValue(bead.getParentCategory());
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
			errorMessage += "Nieprawid這wy rozmiar\n";
		}

		if (imageUrlTextField.getText() == null || imageUrlTextField.getText().length() == 0) {
			errorMessage += "Nieprawid這wy adres zdj璚ia!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Niaprawid這we pola");
			alert.setHeaderText("Popraw nieprawdi這we pola");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
	
	

}
