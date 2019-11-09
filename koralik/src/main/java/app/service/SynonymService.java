package app.service;

import java.util.List;

import app.model.Color;
import app.model.Synonyms;

public interface SynonymService {
	void addSynonym(Synonyms synonym);

	void updateSynonym(Synonyms synonym);

	void removeSynonym(Synonyms synonym);

	List<Synonyms> synonimsList(Color color);

}
