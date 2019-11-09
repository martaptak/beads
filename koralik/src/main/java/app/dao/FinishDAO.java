package app.dao;

import java.util.List;

import app.model.Finish;

public interface FinishDAO {

	void addFinish(Finish finish);

	List<Finish> listFinishes();

	void removeFinish(Integer id);

	void updateFinish(Finish finish);

}
