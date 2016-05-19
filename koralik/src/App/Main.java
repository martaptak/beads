package App;

import java.io.IOException;
import App.Model.Beads;
import App.Model.Color;
import App.Model.HibernateUtil;
import App.view.BeadDetailDialogController;
import App.view.BeadEditDialogController;
import App.view.ColorEditDialogController;
import App.view.TabPaneOverviewController;
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

		launch(args);
		
		//CategoryTest test = new CategoryTest();
		
		//test.test1();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("BeadsApp");

		this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));

		initRootLayout();

		initTabPane();

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

	public void initTabPane() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/TabPaneOverview.fxml"));
			AnchorPane tabOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(tabOverview);
			TabPaneOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public boolean showBeadEditDialog(Beads bead) {
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
			controller.setMainApp(this);
			controller.setBead(bead);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	public void showBeadDetailDialog(Beads bead) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/BeadDetailDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Szczegó³y");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			BeadDetailDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			controller.setBead(bead);
			

			dialogStage.showAndWait();

			
		} catch (IOException e) {
			e.printStackTrace();
			
		}

	}
		
	public boolean showColorEditDialog(Color color) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ColorEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edytuj...");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			ColorEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			controller.setColor(color);

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
