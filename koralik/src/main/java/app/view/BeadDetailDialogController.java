package app.view;

import java.io.IOException;

import app.Main;
import app.ProductsInStoresController;
import app.model.Bead;
import app.model.ProductsInStores;
import app.model.Store;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
	public ContextMenu contextMenu;
	@FXML
	public MenuItem colors;
	@FXML
	public MenuItem stores;
	@FXML
	private TableView<ProductsInStores> storesTable;
	@FXML
	private TableColumn<ProductsInStores, Store> storeNameColumn;
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

	private final WebView browser = new WebView();
	final WebEngine webEngine = browser.getEngine();
	private Main mainApp;
	private Stage dialogStage;
	private Bead bead;
	private final ProductsInStoresController productsInStoresController = new ProductsInStoresController();

	@FXML
	private void initialize() {

		storeNameColumn.setCellValueFactory(new PropertyValueFactory<>("stores"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amountWithUnit"));
		urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));

		storesTable.setRowFactory(product -> new TableRow<>() {
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
	
	
	/*urlColumn.setCellFactory(new Callback<TableColumn<ProductsInStores, Hyperlink>,TableCell<ProductsInStores, Hyperlink>>() {

		@Override
		public TableCell<ProductsInStores, Hyperlink> call(TableColumn<ProductsInStores, Hyperlink> param) {
			TableCell<ProductsInStores, Hyperlink> cell =  new TableCell<ProductsInStores, Hyperlink>();
			cell.setOnMouseClicked(event -> {
				if ((!cell.isEmpty())) {
					cell.getItem().setOnAction(e -> {
						String s = cell.getItem().getTooltip().getText();
						mainApp.getHostServices().showDocument(s);
					});
				}
			});
			return cell;
		}
	});*/

	/*	urlColumn.setCellFactory(column -> {
			return new TableCell<ProductsInStores, Hyperlink>() {

			@Override
				public void updateItem(Hyperlink item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						
					} else {
						
						item.setOnAction(event -> {
							
							String s = item.getTooltip().getText();
							mainApp.getHostServices().showDocument(s);
							event.consume();
						});
					}
				}
			};
		});*/
	
	
	
/*	urlColumn.setCellFactory(column -> {
		TableCell<ProductsInStores, Hyperlink> cell = new TableCell<ProductsInStores, Hyperlink>();

		cell.getItem().setOnAction(event -> {
			String s = cell.getItem().getTooltip().getText();
			mainApp.getHostServices().showDocument(s);
		});					
		return cell;
	});*/

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	public void setBead(Bead bead) {
		this.bead = bead;
		showBeadDetails(bead);
		showStores(bead);

	}

	private void showBeadDetails(Bead bead) {
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

	private void showStores(Bead bead) {
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
			alert.setHeaderText("Nie zosta� zaznaczony koralik");
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

	private boolean showProductEditDialog(ProductsInStores product) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ProductEditDialog.fxml"));
			AnchorPane page = loader.load();

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