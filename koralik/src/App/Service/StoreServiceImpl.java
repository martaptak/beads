package App.Service;

import java.util.List;

import App.Model.Store;
import App.dao.StoresDAO;
import App.dao.StoresDAOImpl;

public class StoreServiceImpl implements StoreService {
	
	private StoresDAO storesDAO = new StoresDAOImpl();

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
