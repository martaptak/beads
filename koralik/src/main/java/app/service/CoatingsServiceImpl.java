package app.service;

import java.util.List;

import app.model.Coatings;
import app.dao.CoatingsDAO;
import app.dao.CoatingsDAOImpl;

public class CoatingsServiceImpl implements CoatingsService {

	private final CoatingsDAO coatingsDAO = new CoatingsDAOImpl();

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
