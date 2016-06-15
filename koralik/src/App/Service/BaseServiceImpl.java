package App.Service;

import java.util.List;

import App.Model.Base;
import App.dao.BaseDAO;
import App.dao.BaseDAOImpl;

public class BaseServiceImpl implements BaseService {

	private BaseDAO baseDAO = new BaseDAOImpl();

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
