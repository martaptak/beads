package app.service;

import java.util.List;

import org.w3c.dom.Element;

import app.model.Bead;
import app.model.Category;
import app.model.Color;

public interface BeadService {

	void addBead(Bead bead);

	List<Bead> listBeads();

	void removeBeads(Integer id);

	void updateBeads(Bead beads);

	List<Bead> listBeadsBySize(String size);

	List<Bead> superSearch(String searchString);

	List<Bead> findBead(Element element);

	List<Bead> listBeadsForTable();

	List<Bead> listBeadsForTable(Category category);

	List<Bead> listBeadsForTable(String categoryParent, String categoryChild);

	List<Bead> listBeadsByColor(Color color);
}
