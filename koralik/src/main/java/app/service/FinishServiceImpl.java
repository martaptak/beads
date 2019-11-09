package app.service;

import java.util.List;

import app.model.Finish;
import app.dao.FinishDAO;
import app.dao.FinishDAOImpl;

public class FinishServiceImpl implements FinishService {

	private final FinishDAO finishDAO = new FinishDAOImpl();

	@Override
	public void addFinish(Finish finish) {
		finishDAO.addFinish(finish);

	}

	@Override
	public List<Finish> listFinishes() {

		return finishDAO.listFinishes();
	}

	@Override
	public void removeFinish(Integer id) {
		finishDAO.removeFinish(id);

	}

	@Override
	public void updateFinish(Finish finish) {
		finishDAO.updateFinish(finish);

	}

}
