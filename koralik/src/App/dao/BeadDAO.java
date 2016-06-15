package App.dao;

import java.util.List;

import org.w3c.dom.Element;

import App.Model.Bead;
import App.Model.Category;
import App.Model.Color;

public interface BeadDAO {

	public void addBead(Bead bead);
	public List<Bead> listBeads();
	public void removeBeads(Integer id);
	public void updateBeads(Bead beads);
	public List<Bead> listBeadsBySize(String size);
	public List<Bead> superSearch(String searchString);
	public List<Bead> findBead(Element element);
	public List<Bead> listBeadsForTable();
	public List<Bead> listBeadsForTable(Category category);
	public List<Bead> listBeadsForTable(String categoryParent, String categoryChild);
	public List<Bead> listBeadsByColor(Color color);
	
}