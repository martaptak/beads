package App.Service;

import java.util.List;

import App.Model.Coatings;
import App.dao.CoatingsDAO;
import App.dao.CoatingsDAOImpl;

public class CoatingsServiceImpl implements CoatingsService {

	private CoatingsDAO coatingsDAO = new CoatingsDAOImpl();
	
	@Override
	public void addCoating(Coatings coating) {
		coatingsDAO.addCoating(coating);
		
	}

	@Override
	public void updateCoatings(Coatings coating) {
		coatingsDAO.updateCoatings(coating);
		
	}

	@Override
	public void removeCoatings(Coatings coating) {
		coatingsDAO.removeCoatings(coating);
	}

	@Override
	public List<Coatings> coatingsList() {
		return coatingsDAO.coatingsList();
	}

}
