package app.service;

import java.util.List;

import app.model.Coatings;

public interface CoatingsService {

	void addCoating(Coatings coating);

	void updateCoatings(Coatings coating);

	void removeCoatings(Coatings coating);

	List<Coatings> coatingsList();

}
