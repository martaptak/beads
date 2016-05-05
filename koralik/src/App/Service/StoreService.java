package App.Service;

import java.util.List;

import App.Model.Stores;

public interface StoreService {
	
	public void addStore(Stores store);
	public List<Stores> listStores();
	public void removeStore(Integer id);
	public void updateStore(Stores store);

}
