package app;

import java.io.IOException;
import java.util.List;

import app.model.Base;
import app.model.Bead;
import app.model.Category;
import app.model.Coatings;
import app.model.Color;
import app.model.Finish;
import app.model.HibernateUtil;
import app.view.BaseDialogController;
import app.view.BaseEditDialogController;
import app.view.BeadDetailDialogController;
import app.view.BeadEditDialogController;
import app.view.CategoryPickDialogController;
import app.view.CoatingsDialogController;
import app.view.CoatingsEditDialogController;
import app.view.ColorEditDialogController;
import app.view.ColorFamilyDialogController;
import app.view.FinishEditDialogController;
import app.view.FinishNewDialogController;
import app.view.PickBeadDialogController;
import app.view.RootLayoutController;
import app.view.StoresDialogController;
import app.view.SynonimsDialogController;
import app.view.TabPaneOverviewController;
import app.view.UpdateOverviewController;
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

	public static void main(String[] args) {

		launch(args);

		// InsertData.preciosa();

		// CategoryTest test = new CategoryTest();

		// test.test1();
	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("BeadsApp");

		this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));

		initRootLayout();

		initTabPane();

	}

	private void initRootLayout() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initTabPane() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/TabPaneOverview.fxml"));
			AnchorPane tabOverview = loader.load();

			rootLayout.setCenter(tabOverview);
			TabPaneOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showBeadEditDialog(Bead bead) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/BeadEditDialog.fxml"));
			AnchorPane page = loader.load();

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

	public void showBeadDetailDialog(Bead bead) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/BeadDetailDialog.fxml"));
			AnchorPane page = loader.load();

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
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edytuj...");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			ColorEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			controller.setColor(color);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void showUpdateDialog() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/UpdateOverview.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Aktualizacja stanów sklepów");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			UpdateOverviewController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public void showStoresDialog() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/StoresDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Sklepy");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			StoresDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showFinishDialog() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/FinishEditDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Finish");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			FinishEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showFinishEditDialog(Finish finish) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/FinishNewDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edytuj...");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			FinishNewDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			controller.setFinish(finish);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void showBaseDialog() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/BaseDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Base");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			BaseDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showBaseEditDialog(Base base) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/BaseEditDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edytuj...");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			BaseEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			controller.setBase(base);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void showCoatingDialog() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CoatingsDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Finish");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			CoatingsDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showCoatingEditDialog(Coatings coating) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CoatingsEditDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edytuj...");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			CoatingsEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			controller.setCoating(coating);
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void showColorFamilyDialog() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ColorFamilyEditDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Rodziny kolorystyczne");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			ColorFamilyDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showSynonimsDialog(Color color) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/SynonimsDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Base");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			SynonimsDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.inicializeList(color);
			controller.setColor(color);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Category showCategoryPickDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CategoryPickDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wybierz kategorie");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			CategoryPickDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();

			if (controller.isOkClicked()) {
				return controller.pickedCategory();
			} else {
				return new Category();
			}

		} catch (IOException e) {
			e.printStackTrace();
			return new Category();
		}
	}

	public List<Bead> showPickBeadDialog(Bead selectedBead) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/PickBeadDialog.fxml"));
			AnchorPane page = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wybierz koraliki");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			PickBeadDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.createList(selectedBead);
			dialogStage.showAndWait();

			return controller.pickedBeads();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	@Override
	public void stop() {
		HibernateUtil.shutdown();
	}

}
