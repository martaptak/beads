package App.view;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import App.Main;
import App.Model.Bead;
import App.Model.Category;
import App.Model.Color;
import App.Model.Finish;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TabPaneOverviewController {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab homeTab;

	@FXML
	private Tab categoryTab;

	@FXML
	private Tab colorTab;

	@FXML
	private Tab ownedTab;

	private Main mainApp;

	private ColorOverviewController colorController;
	private CategoryOverviewController categoryConroller;
	private HomeOverviewController homeController;
	private ColorPickDialogController colorPickController;

	@FXML
	private void initialize() {

	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		showColorOverview();

		showCategoryOverview();
		showHomeOverview();

	}

	public void showHomeOverview() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HomeOverview.fxml"));
			AnchorPane beadOverview = (AnchorPane) loader.load();

			homeTab.setContent(beadOverview);

			homeController = loader.getController();
			homeController.setMainApp(mainApp);
			homeController.setTabPane(tabPane);
			homeController.setTabPaneOverviewController(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showCategoryOverview() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CategoryOverview.fxml"));
			AnchorPane categoryOverview = (AnchorPane) loader.load();

			categoryTab.setContent(categoryOverview);

			categoryConroller = loader.getController();
			categoryConroller.setMainApp(mainApp);
			categoryConroller.setTabPaneOverviewController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showColorOverview() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ColorOverview.fxml"));
			AnchorPane colorOverview = (AnchorPane) loader.load();

			colorTab.setContent(colorOverview);

			colorController = loader.getController();
			colorController.setMainApp(mainApp);
			colorController.setTabPaneOverviewController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showPickColorOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ColorPickDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wybierz kategorie");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			colorPickController = loader.getController();
			colorPickController.setDialogStage(dialogStage);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void selectColor(Color color) {
		colorController.selectColor(color);
	}

	public void selectCategory(Category category) {
		categoryConroller.selectCategory(category);
	}

	public void editBead(Bead selectedBead) {
		homeController.editBead(selectedBead);
	}

	public Color pickedColor() {

		if (colorPickController.isOkClicked()) {
			return colorPickController.pickedColor();
		} else {
			return new Color();
		}
	}

	public Set<Finish> checkedFnishes(){
		
		if(colorPickController.isOkClicked()){
			return colorPickController.checkedFinishes();
		}
		else {
			return new HashSet<Finish>(0);
		}
		
		
	}

}
