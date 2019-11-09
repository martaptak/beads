package app.dao;

import java.util.List;

import app.model.Color;
import app.model.Synonyms;

public interface SynonymsDAO {

	void addSynonym(Synonyms synonym);

	void updateSynonym(Synonyms synonym);

	void removeSynonym(Synonyms synonym);

	List<Synonyms> synonymsList(Color color);


}
