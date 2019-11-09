package app.dao;

import java.util.List;

import app.model.Coatings;

public interface CoatingsDAO {

	void addCoating(Coatings coating);

	void updateCoatings(Coatings coating);

	void removeCoatings(Coatings coating);

	List<Coatings> coatingsList();
}
