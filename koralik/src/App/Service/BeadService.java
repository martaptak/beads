package App.Service;

import java.util.List;

import App.Model.Beads;
import App.Model.Category;
import App.Model.Color;

public interface BeadService {

	public void addBead(Beads bead);
	public List<Beads> listBeads();
	public void removeBeads(Integer id);
	public void updateBeads(Beads beads);
	public List<Beads> listBeadsBySize(String size);
	public List<Beads> superSearch(String searchString);
	public List<Beads> listBeadsForTable();

	public List<Beads> listBeadsForTable(Category category);
	public List<Beads> listBeadsForTable(String categoryParent, String categoryChild);
	public List<Beads> listBeadsByColor(Color color);
}
