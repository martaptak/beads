package app;

import app.model.Store;
import app.service.StoreService;
import app.service.StoreServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StoreController {

	private final StoreService storeService = new StoreServiceImpl();
	private ObservableList<Store> storesList = FXCollections.observableArrayList();

	public void addStore(Store store) {
		storeService.addStore(store);
	}

	public ObservableList<Store> listStores() {
		if (!storesList.isEmpty()) {
			storesList.clear();
		}
		storesList = FXCollections.observableList(storeService.listStores());
		return storesList;
	}

	public void removeStore(Integer id) {
		storeService.removeStore(id);
	}

	public void updateStore(Store store) {
		storeService.updateStore(store);
	}
}
