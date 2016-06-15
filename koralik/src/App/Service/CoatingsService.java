package App.Service;

import java.util.List;

import App.Model.Coatings;

public interface CoatingsService {

	public void addCoating(Coatings coating);
	public void updateCoatings(Coatings coating);
	public void removeCoatings(Coatings coating);
	public List<Coatings> coatingsList();
	
}
