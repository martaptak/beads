package app.service;

import java.util.List;

import org.w3c.dom.Element;

import app.model.Bead;
import app.model.Category;
import app.model.Color;
import app.dao.BeadDAO;
import app.dao.BeadDAOImpl;

public class BeadServiceImpl implements BeadService {

	private final BeadDAO beadDAO = new BeadDAOImpl();

	@Override
	public void addBead(Bead bead) {
		beadDAO.addBead(bead);

	}

	@Override
	public List<Bead> listBeads() {
		return beadDAO.listBeads();
	}

	@Override
	public void removeBeads(Integer id) {
		beadDAO.removeBeads(id);

	}

	@Override
	public void updateBeads(Bead beads) {
		beadDAO.updateBeads(beads);

	}

	@Override
	public List<Bead> listBeadsBySize(String size) {
		return beadDAO.listBeadsBySize(size);
	}

	@Override
	public List<Bead> superSearch(String searchString) {
		return beadDAO.superSearch(searchString);
	}

	@Override
	public List<Bead> listBeadsForTable() {
		return beadDAO.listBeadsForTable();
	}


	@Override
	public List<Bead> listBeadsForTable(Category category) {
		return beadDAO.listBeadsForTable(category);
	}

	@Override
	public List<Bead> listBeadsForTable(String categoryParent, String categoryChild) {
		return beadDAO.listBeadsForTable(categoryParent, categoryChild);
	}

	@Override
	public List<Bead> listBeadsByColor(Color color) {
		return beadDAO.listBeadsByColor(color);
	}

	@Override
	public List<Bead> findBead(Element element) {
		return beadDAO.findBead(element);
	}

}
