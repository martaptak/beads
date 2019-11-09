package app;

import java.util.List;

import org.w3c.dom.Element;

import app.model.Bead;
import app.model.Category;
import app.model.Color;
import app.service.BeadService;
import app.service.BeadServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BeadController {

	private final BeadService beadService = new BeadServiceImpl();
	private ObservableList<Bead> beadList = FXCollections.observableArrayList();
	private ObservableList<Bead> beadListBySize = FXCollections.observableArrayList();

	private ObservableList<Bead> beadListForTableFiltered = FXCollections.observableArrayList();
	private ObservableList<Bead> beadListByColor = FXCollections.observableArrayList();

	public void addBead(Bead bead) {
		beadService.addBead(bead);
	}

	public ObservableList<Bead> getBeadList() {
		if (!beadList.isEmpty()) {
			beadList.clear();
		}
		beadList = FXCollections.observableList(beadService.listBeads());
		return beadList;
	}

	public void removeBead(Integer id) {
		beadService.removeBeads(id);
	}

	public void updateBead(Bead bead) {
		beadService.updateBeads(bead);
	}

	public ObservableList<Bead> getBeadsListBySize(String size) {

		if (!beadListBySize.isEmpty()) {
			beadListBySize.clear();
		}
		beadListBySize = FXCollections.observableList(beadService.listBeadsBySize(size));
		return beadListBySize;
	}

	public ObservableList<Bead> listBeadsForTable() {

		ObservableList<Bead> beadListForTable;

		beadListForTable = FXCollections.observableList(beadService.listBeadsForTable());
		return beadListForTable;

	}

	public ObservableList<Bead> listBeadsForTable(Category category) {

		ObservableList<Bead> beadListForTable;

		beadListForTable = FXCollections.observableList(beadService.listBeadsForTable(category));
		return beadListForTable;

	}

	public ObservableList<Bead> listBeadsForTable(String categoryParent, String categoryChild) {
		if (!beadListForTableFiltered.isEmpty()) {
			beadListForTableFiltered.clear();
		}
		beadListForTableFiltered = FXCollections
				.observableList(beadService.listBeadsForTable(categoryParent, categoryChild));

		return beadListForTableFiltered;
	}

	public ObservableList<Bead> listBeadsByColor(Color color) {
		if (!beadListByColor.isEmpty()) {
			beadListByColor.clear();
		}
		beadListByColor = FXCollections.observableList(beadService.listBeadsByColor(color));

		return beadListByColor;

	}

	public List<Bead> findBead(Element element) {

		ObservableList<Bead> beadsFound;

		beadsFound = FXCollections.observableList(beadService.findBead(element));
		return beadsFound;

	}

}
