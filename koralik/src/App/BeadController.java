package App;

import java.util.List;

import org.w3c.dom.Element;

import App.Model.Bead;
import App.Model.Category;
import App.Model.Color;
import App.Service.BeadService;
import App.Service.BeadServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BeadController {

	private BeadService beadService = new BeadServiceImpl();
	private ObservableList<Bead> beadList = FXCollections.observableArrayList();
	private ObservableList<Bead> beadListBySize = FXCollections.observableArrayList();

	private ObservableList<Bead> beadListForTableFiltred = FXCollections.observableArrayList();
	private ObservableList<Bead> beadListByColor = FXCollections.observableArrayList();

	public void addBead(Bead bead) {
		beadService.addBead(bead);
	}

	public ObservableList<Bead> getBeadList() {
		if (!beadList.isEmpty()) {
			beadList.clear();
		}
		beadList = FXCollections.observableList((List<Bead>) beadService.listBeads());
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
		beadListBySize = FXCollections.observableList((List<Bead>) beadService.listBeadsBySize(size));
		return beadListBySize;
	}

	public ObservableList<Bead> listBeadsForTable() {

		ObservableList<Bead> beadListForTable = FXCollections.observableArrayList();

		beadListForTable = FXCollections.observableList((List<Bead>) beadService.listBeadsForTable());
		return beadListForTable;

	}

	public ObservableList<Bead> listBeadsForTable(Category category) {

		ObservableList<Bead> beadListForTable = FXCollections.observableArrayList();
		
		beadListForTable = FXCollections.observableList((List<Bead>) beadService.listBeadsForTable(category));
		return beadListForTable;

	}

	public ObservableList<Bead> listBeadsForTable(String categoryParent, String categoryChild) {
		if (!beadListForTableFiltred.isEmpty()) {
			beadListForTableFiltred.clear();
		}
		beadListForTableFiltred = FXCollections
				.observableList((List<Bead>) beadService.listBeadsForTable(categoryParent, categoryChild));

		return beadListForTableFiltred;
	}

	public ObservableList<Bead> listBeadsByColor(Color color) {
		if (!beadListByColor.isEmpty()) {
			beadListByColor.clear();
		}
		beadListByColor = FXCollections.observableList((List<Bead>) beadService.listBeadsByColor(color));

		return beadListByColor;

	}
	
	public List<Bead> findBead(Element element){
		
		ObservableList<Bead> beadsFound = FXCollections.observableArrayList();

		beadsFound = FXCollections.observableList((List<Bead>) beadService.findBead(element));
		return beadsFound;
		
	}

}
