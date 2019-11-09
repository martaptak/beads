package app.dao;

import java.util.List;

import app.model.Store;

public interface StoresDAO {

	void addStore(Store store);

	List<Store> listStores();

	void removeStore(Integer id);

	void updateStore(Store store);

}
