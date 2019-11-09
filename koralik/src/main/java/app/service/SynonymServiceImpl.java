package app.service;

import java.util.List;

import app.model.Color;
import app.model.Synonyms;
import app.dao.SynonymsDAO;
import app.dao.SynonymsDAOImpl;

public class SynonymServiceImpl implements SynonymService {

	private final SynonymsDAO synonymsDAO = new SynonymsDAOImpl();

	@Override
	public void addSynonym(Synonyms synonym) {
		synonymsDAO.addSynonym(synonym);

	}

	@Override
	public void updateSynonym(Synonyms synonym) {
		synonymsDAO.updateSynonym(synonym);

	}

	@Override
	public void removeSynonym(Synonyms synonym) {
		synonymsDAO.removeSynonym(synonym);

	}

	@Override
	public List<Synonyms> synonimsList(Color color) {
		return synonymsDAO.synonymsList(color);
	}

}
