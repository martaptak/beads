package App.dao;

import java.util.List;

import App.Model.Store;

public interface StoresDAO {
	
	public void addStore(Store store);
	public List<Store> listStores();
	public void removeStore(Integer id);
	public void updateStore(Store store);

}
