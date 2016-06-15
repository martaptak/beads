package App.dao;

import java.util.List;

import App.Model.Color;
import App.Model.Synonims;

public interface SynonimsDAO {
	
	public void addSynonim(Synonims synonim);
	public void updateSynonim(Synonims synonim);
	public void removeSynonim(Synonims synonim);
	public List<Synonims> synonimsList(Color color);
		
	

}
