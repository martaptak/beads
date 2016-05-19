package App.Service;

import java.util.List;

import App.Model.Finish;
import App.dao.FinishDAO;
import App.dao.FinishDAOImpl;

public class FinishServiceImpl implements FinishService {
	
	FinishDAO finishDAO = new FinishDAOImpl();

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
