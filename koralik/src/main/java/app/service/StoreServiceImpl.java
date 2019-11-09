package app.service;

import java.util.List;

import app.model.Store;
import app.dao.StoresDAO;
import app.dao.StoresDAOImpl;

public class StoreServiceImpl implements StoreService {

	private final StoresDAO storesDAO = new StoresDAOImpl();

	@Override
	public void addStore(Store store) {
		storesDAO.addStore(store);
	}

	@Override
	public List<Store> listStores() {
		return storesDAO.listStores();
	}

	@Override
	public void removeStore(Integer id) {
		storesDAO.removeStore(id);

	}

	@Override
	public void updateStore(Store store) {
		storesDAO.updateStore(store);

	}

}
