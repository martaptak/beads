package App;

import java.util.List;

import App.Model.Beads;
import App.Service.BeadService;
import App.Service.BeadServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BeadController {

	private BeadService beadService = new BeadServiceImpl();
	private ObservableList<Beads> beadList = FXCollections.observableArrayList();
	private ObservableList<Beads> beadListBySize = FXCollections.observableArrayList();
	private ObservableList<Beads> beadListForTable = FXCollections.observableArrayList();
	private ObservableList<Beads> beadListForTableFiltred = FXCollections.observableArrayList();

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

	public List<Beads> listBeadsFotTable() {

		if (!beadListForTable.isEmpty()) {
			beadListForTable.clear();
		}
		beadListForTable = FXCollections.observableList((List<Beads>) beadService.listBeadsFotTable());
		return beadListForTable;

	}

	public List<Beads> listBeadsFotTable(String categoryParent, String categoryChild) {
		if (!beadListForTableFiltred.isEmpty()) {
			beadListForTableFiltred.clear();
		}
		beadListForTableFiltred = FXCollections.observableList((List<Beads>) 
				beadService.listBeadsFotTable(categoryParent, categoryChild));

		return beadListForTableFiltred;
	}

}
