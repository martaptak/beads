package App.Service;

import java.util.List;

import App.Model.Beads;
import App.dao.BeadDAO;
import App.dao.BeadDAOImpl;

public class BeadServiceImpl implements BeadService {

	private BeadDAO beadDAO = new BeadDAOImpl();

	@Override
	public void addBead(Beads bead) {
		beadDAO.addBead(bead);

	}

	@Override
	public List<Beads> listBeads() {
		return beadDAO.listBeads();
	}

	@Override
	public void removeBeads(Integer id) {
		beadDAO.removeBeads(id);

	}

	@Override
	public void updateBeads(Beads beads) {
		beadDAO.updateBeads(beads);

	}

	@Override
	public List<Beads> listBeadsBySize(String size) {
		return beadDAO.listBeadsBySize(size);
	}

	@Override
	public List<Beads> superSearch(String searchString) {
		return beadDAO.superSearch(searchString);
	}

	@Override
	public List<Beads> listBeadsFotTable() {
		return beadDAO.listBeadsFotTable();
	}

	@Override
	public List<Beads> listBeadsFotTable(String categoryParent, String categoryChild) {
		return beadDAO.listBeadsFotTable(categoryParent, categoryChild);
	}

}
