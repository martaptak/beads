package app.service;

import java.util.List;

import app.model.Base;


public interface BaseService {

	void addBase(Base base);

	void updateBase(Base base);

	void removeBase(Base base);

	List<Base> basesList();

}
