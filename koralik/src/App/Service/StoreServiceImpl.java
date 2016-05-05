package App.Service;

import java.util.List;

import App.Model.Stores;
import App.dao.StoresDAO;
import App.dao.StoresDAOImpl;

public class StoreServiceImpl implements StoreService {
	
	private StoresDAO storesDAO = new StoresDAOImpl();

	@Override
	public void addStore(Stores store) {
		storesDAO.addStore(store);
	}

	@Override
	public List<Stores> listStores() {
		return storesDAO.listStores();
	}

	@Override
	public void removeStore(Integer id) {
		storesDAO.removeStore(id);
		
	}

	@Override
	public void updateStore(Stores store) {
		storesDAO.updateStore(store);
		
	}

}
