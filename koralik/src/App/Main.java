package App;

import java.io.IOException;

import App.Model.Beads;
import App.Model.HibernateUtil;
import App.test.BeadTest;
import App.view.BeadEditDialogController;
import App.view.BeadsOverviewController;
import App.view.CategoryOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	public static void main(String[] args) throws Exception {

		/*
		 * BeadTest test = new BeadTest(); test.test1();
		 */
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("BeadsApp");

		this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
		
		initRootLayout();

		showCategoryOverview();

	}

	public void initRootLayout() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showBeadOverview() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/BeadsOverview.fxml"));
			AnchorPane beadOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(beadOverview);

			BeadsOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showCategoryOverview() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CategoryOverview.fxml"));
			AnchorPane categoryOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(categoryOverview);

			CategoryOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showPersonEditDialog(Beads bead) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/BeadEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edytuj...");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			BeadEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBead(bead);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	@Override
	public void stop() throws Exception {
		HibernateUtil.shutdown();
	}

}
