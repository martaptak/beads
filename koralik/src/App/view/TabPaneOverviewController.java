package App.view;

import java.io.IOException;

import App.Main;
import App.Model.Category;
import App.Model.Color;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

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

			HomeOverviewController controller = loader.getController();
			controller.setMainApp(mainApp);
			controller.setTabPane(tabPane);
			controller.setTabPaneOverviewController(this);
			
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void selectColor(Color color){
		colorController.selectColor(color);
	}
	
	public void selectCategory(Category category){
		categoryConroller.selectCategory(category);
	}

}
