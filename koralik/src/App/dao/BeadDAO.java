package App.dao;

import java.util.List;

import App.Model.Beads;

public interface BeadDAO {

	public void addBead(Beads bead);
	public List<Beads> listBeads();
	public void removeBeads(Integer id);
	public void updateBeads(Beads beads);
	public List<Beads> listBeadsBySize(String size);
	public List<Beads> superSearch(String searchString);
	public List<Beads> listBeadsFotTable();
	public List<Beads> listBeadsFotTable(String categoryParent, String categoryChild);
}