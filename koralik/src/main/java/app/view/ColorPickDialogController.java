package app.view;

import java.util.HashSet;
import java.util.Set;

import org.controlsfx.control.CheckComboBox;

import app.ColorController;
import app.FinishController;
import app.model.Color;
import app.model.Finish;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

@SuppressWarnings("WeakerAccess")
public class ColorPickDialogController {

	@FXML
	private ComboBox<Color> colorComboBox;
	@FXML
	private CheckComboBox<Finish> finishBox;

	private final ColorController colorController = new ColorController();
	private final FinishController finishController = new FinishController();

	private Stage dialogStage;
	private boolean okClicked = false;
	private Color pickedColor;
	private final Set<Finish> pickedFinishes = new HashSet<>(0);
	private final ObservableList<Color> colors = (ObservableList<Color>) colorController.listColors();

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

		pickedFinishes.addAll(finishBox.getCheckModel().getCheckedItems());

		pickedColor = colorComboBox.getSelectionModel().getSelectedItem();
		okClicked = true;
		dialogStage.close();
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public Color pickedColor() {
		return pickedColor;
	}

	public Set<Finish> checkedFinishes() {
		return pickedFinishes;
	}

}
