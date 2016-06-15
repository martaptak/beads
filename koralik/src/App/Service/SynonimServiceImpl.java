package App.Service;

import java.util.List;

import App.Model.Color;
import App.Model.Synonims;
import App.dao.SynonimsDAO;
import App.dao.SynonimsDAOImpl;

public class SynonimServiceImpl implements SynonimService{

	private SynonimsDAO synonimsDAO = new SynonimsDAOImpl();
	
	@Override
	public void addSynonim(Synonims synonim) {
		synonimsDAO.addSynonim(synonim);
		
	}

	@Override
	public void updateSynonim(Synonims synonim) {
		synonimsDAO.updateSynonim(synonim);
		
	}

	@Override
	public void removeSynonim(Synonims synonim) {
		synonimsDAO.removeSynonim(synonim);
		
	}

	@Override
	public List<Synonims> synonimsList(Color color) {
		return synonimsDAO.synonimsList(color);
	}

}
