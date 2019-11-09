package app.service;

import java.util.List;

import app.model.Store;

public interface StoreService {

	void addStore(Store store);

	List<Store> listStores();

	void removeStore(Integer id);

	void updateStore(Store store);

}
