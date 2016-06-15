package App.dao;

import java.util.List;

import App.Model.Base;

public interface BaseDAO {

	public void addBase(Base base);
	public void updateBase(Base base);
	public void removeBase(Base base);
	public List<Base> basesList();
}