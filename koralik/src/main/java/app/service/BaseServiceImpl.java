package app.service;

import java.util.List;

import app.model.Base;
import app.dao.BaseDAO;
import app.dao.BaseDAOImpl;

public class BaseServiceImpl implements BaseService {

	private final BaseDAO baseDAO = new BaseDAOImpl();

	@Override
	public void addBase(Base base) {
		baseDAO.addBase(base);

	}

	@Override
	public void updateBase(Base base) {
		baseDAO.updateBase(base);
	}

	@Override
	public void removeBase(Base base) {
		baseDAO.removeBase(base);

	}

	@Override
	public List<Base> basesList() {

		return baseDAO.basesList();
	}

}
