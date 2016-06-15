package App;

import App.Model.Base;
import App.Service.BaseService;
import App.Service.BaseServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BaseController {
	private BaseService baseService = new BaseServiceImpl();
	
	public void addBase(Base base){
		baseService.addBase(base);
	}
	public void updateBase(Base base){
		baseService.updateBase(base);
	}
	public void removeBase(Base base){
		baseService.removeBase(base);
	}
	public ObservableList<Base> basesList(){
		
		ObservableList<Base> list = FXCollections.observableArrayList();
		
		list = FXCollections.observableList(baseService.basesList());
		
		return list;
	}
}
