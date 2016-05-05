package App;

import java.util.List;

import App.Model.Beads;
import App.Model.Category;
import App.Model.Color;
import App.Service.BeadService;
import App.Service.BeadServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BeadController {

	private BeadService beadService = new BeadServiceImpl();
	private ObservableList<Beads> beadList = FXCollections.observableArrayList();
	private ObservableList<Beads> beadListBySize = FXCollections.observableArrayList();

	private ObservableList<Beads> beadListForTableFiltred = FXCollections.observableArrayList();
	private ObservableList<Beads> beadListByColor = FXCollections.observableArrayList();

	public void addBead(Beads bead) {
		beadService.addBead(bead);
	}

	public ObservableList<Beads> getBeadList() {
		if (!beadList.isEmpty()) {
			beadList.clear();
		}
		beadList = FXCollections.observableList((List<Beads>) beadService.listBeads());
		return beadList;
	}

	public void removeBead(Integer id) {
		beadService.removeBeads(id);
	}

	public void updateBead(Beads bead) {
		beadService.updateBeads(bead);
	}

	public ObservableList<Beads> getBeadsListBySize(String size) {

		if (!beadListBySize.isEmpty()) {
			beadListBySize.clear();
		}
		beadListBySize = FXCollections.observableList((List<Beads>) beadService.listBeadsBySize(size));
		return beadListBySize;
	}

	public ObservableList<Beads> listBeadsForTable() {

		ObservableList<Beads> beadListForTable = FXCollections.observableArrayList();

		beadListForTable = FXCollections.observableList((List<Beads>) beadService.listBeadsForTable());
		return beadListForTable;

	}

	public ObservableList<Beads> listBeadsForTable(Category category) {

		ObservableList<Beads> beadListForTable = FXCollections.observableArrayList();
		
		beadListForTable = FXCollections.observableList((List<Beads>) beadService.listBeadsForTable(category));
		return beadListForTable;

	}

	public ObservableList<Beads> listBeadsForTable(String categoryParent, String categoryChild) {
		if (!beadListForTableFiltred.isEmpty()) {
			beadListForTableFiltred.clear();
		}
		beadListForTableFiltred = FXCollections
				.observableList((List<Beads>) beadService.listBeadsForTable(categoryParent, categoryChild));

		return beadListForTableFiltred;
	}

	public ObservableList<Beads> listBeadsByColor(Color color) {
		if (!beadListByColor.isEmpty()) {
			beadListByColor.clear();
		}
		beadListByColor = FXCollections.observableList((List<Beads>) beadService.listBeadsByColor(color));

		return beadListByColor;

	}

}
