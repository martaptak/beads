package App;

import App.Model.Stores;
import App.Service.StoreService;
import App.Service.StoreServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StoresController {
	
	private StoreService storeService = new StoreServiceImpl();
	private ObservableList<Stores> storesList = FXCollections.observableArrayList();

	public void addStore(Stores store) {
		storeService.addStore(store);
	}
	public ObservableList<Stores> listStores() {
		if (!storesList.isEmpty()) {
			storesList.clear();
		}
		storesList = FXCollections.observableList(storeService.listStores());
		return storesList;
	}
	public void removeStore(Integer id) {
		storeService.removeStore(id);
	}
	public void updateStore(Stores store) {
		storeService.updateStore(store);
	}
}
