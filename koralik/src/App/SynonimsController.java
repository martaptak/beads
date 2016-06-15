package App;

import App.Model.Color;
import App.Model.Synonims;
import App.Service.SynonimService;
import App.Service.SynonimServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SynonimsController {
	
	private SynonimService synonimsService = new SynonimServiceImpl();
	
	public void addSynonim(Synonims synonim){
		synonimsService.addSynonim(synonim);
	}
	public void updateSynonim(Synonims synonim){
		synonimsService.updateSynonim(synonim);
	}
	public void removeSynonim(Synonims synonim){
		synonimsService.removeSynonim(synonim);
	}
	public ObservableList<Synonims> synonimsList(Color color){
		ObservableList<Synonims> list = FXCollections.observableArrayList();
		
		list = FXCollections.observableList(synonimsService.synonimsList(color));
		return list;
	}
		
}
