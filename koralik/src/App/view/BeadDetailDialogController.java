package App.view;

import java.io.IOException;

import App.Main;
import App.ProductsInStoresController;
import App.Model.Beads;
import App.Model.ProductsInStores;
import App.Model.Stores;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BeadDetailDialogController {

	@FXML
	private TableView<ProductsInStores> storesTable;
	@FXML
	private TableColumn<ProductsInStores, Stores> storeNameColumn;
	@FXML
	private TableColumn<ProductsInStores, String> amountColumn;
	@FXML
	private TableColumn<ProductsInStores, Hyperlink> urlColumn;
	@FXML
	private ImageView beadsImage;
	@FXML
	private Label typeLabel;
	@FXML
	private Label colorLabel;
	@FXML
	private Label colorFamilyLabel;
	@FXML
	private Label sizeLabel;

	final WebView browser = new WebView();
	final WebEngine webEngine = browser.getEngine();
	private Main mainApp;
	private Stage dialogStage;
	private Beads bead;
	private ProductsInStoresController productsInStoresController = new ProductsInStoresController();

	@FXML
	private void initialize() {

		storeNameColumn.setCellValueFactory(new PropertyValueFactory<ProductsInStores, Stores>("stores"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<ProductsInStores, String>("amountWithUnit"));
		urlColumn.setCellValueFactory(new PropertyValueFactory<ProductsInStores, Hyperlink>("url"));

		storesTable.setRowFactory(product -> new TableRow<ProductsInStores>() {
			@Override
			public void updateItem(ProductsInStores item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setStyle("");
				} else if (item.getAvibility().equals(true)) {
					setStyle("-fx-background-color: #D4D7B7;");
				} else {
					setStyle("");
				}
			}
		});	
		
	
		
	/*	urlColumn.setCellFactory(cv -> {
			TableCell<ProductsInStores, Hyperlink> cell = new TableCell<>();
			cell.getItem().setOnAction(arg0);
			
		}); 
	*/
		
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	public void setBead(Beads bead) {
		this.bead = bead;
		showBeadDetails(bead);
		showStores(bead);

	}

	public void showBeadDetails(Beads bead) {
		if (bead != null) {

			typeLabel.setText(bead.getCategoryName());
			colorLabel.setText(bead.getColorName());
			sizeLabel.setText(bead.getSize());
			beadsImage.setImage(new Image(bead.getImageUrl()));
			colorFamilyLabel.setText(bead.getColorFamily());

		} else {

			typeLabel.setText("");
			colorLabel.setText("");
			colorFamilyLabel.setText("");
			sizeLabel.setText("");

		}
	}

	public void showStores(Beads bead) {
		storesTable.setItems((ObservableList<ProductsInStores>) productsInStoresController.listProducts(bead));
	}

	@FXML
	private void handleDeleteProduct() {
		int selectedIndex = storesTable.getSelectionModel().getSelectedIndex();
		int id = storesTable.getSelectionModel().selectedItemProperty().getValue().getIdProductInStores();
		if (selectedIndex >= 0) {
			storesTable.getItems().remove(selectedIndex);
			productsInStoresController.removeProduct(id);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("Nie zosta³ zaznaczony koralik");
			alert.setContentText("Wybierz koralik z tabeli");

			alert.showAndWait();
		}

	}

	@FXML
	private void handleNewProduct() {
		ProductsInStores tempProduct = new ProductsInStores(bead);
		boolean okClicked = showProductEditDialog(tempProduct);
		if (okClicked) {
			productsInStoresController.addProduct(tempProduct);
			showStores(bead);

		}
	}

	public boolean showProductEditDialog(ProductsInStores product) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ProductEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edytuj...");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.dialogStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			ProductEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setProduct(product);
			

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
