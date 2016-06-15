package App.view;

import java.util.HashSet;
import java.util.Set;

import org.controlsfx.control.CheckComboBox;

import App.ColorController;
import App.FinishController;
import App.Model.Color;
import App.Model.Finish;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ColorPickDialogController {
	
	@FXML
	private ComboBox<Color> colorComboBox;
	@FXML
	private CheckComboBox<Finish> finishBox;
	
	private ColorController colorController = new ColorController();
	private FinishController finishController = new FinishController();
	
	private Stage dialogStage;
	private boolean okClicked = false;
	private Color pickedColor;
	private Set<Finish> pickedFinishes = new HashSet<Finish>(0);
	private ObservableList<Color> colors = (ObservableList<Color>) colorController.listColors();
	
	@FXML
	private void initialize() {
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
		
		finishBox.getItems().setAll(finishController.listFinishes());
		
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
				
		for (Finish f : finishBox.getCheckModel().getCheckedItems()) {
			pickedFinishes.add(f);
		}

		pickedColor = colorComboBox.getSelectionModel().getSelectedItem();
		okClicked = true;
		dialogStage.close();
	}

	public boolean isOkClicked() {
		return okClicked;
	}
	
	public Color pickedColor(){
		return pickedColor;
	}
	
	public Set<Finish> checkedFinishes(){				
		return pickedFinishes;
	}

}
