package App.Service;

import java.util.List;

import App.Model.Store;

public interface StoreService {
	
	public void addStore(Store store);
	public List<Store> listStores();
	public void removeStore(Integer id);
	public void updateStore(Store store);

}
