package app;

import app.model.Color;
import app.model.Synonyms;
import app.service.SynonymService;
import app.service.SynonymServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SynonimsController {

	private final SynonymService synonymsService = new SynonymServiceImpl();

	public void addSynonym(Synonyms synonym) {
		synonymsService.addSynonym(synonym);
	}

	public void updateSynonym(Synonyms synonym) {
		synonymsService.updateSynonym(synonym);
	}

	public void removeSynonym(Synonyms synonym) {
		synonymsService.removeSynonym(synonym);
	}

	public ObservableList<Synonyms> synonymsList(Color color) {
		ObservableList<Synonyms> list;

		list = FXCollections.observableList(synonymsService.synonimsList(color));
		return list;
	}

}
