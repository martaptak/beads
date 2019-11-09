package app.dao;

import java.util.List;

import app.model.Base;

public interface BaseDAO {

	void addBase(Base base);

	void updateBase(Base base);

	void removeBase(Base base);

	List<Base> basesList();
}
