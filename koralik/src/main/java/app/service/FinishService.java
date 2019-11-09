package app.service;

import java.util.List;

import app.model.Finish;

public interface FinishService {

	void addFinish(Finish finish);

	List<Finish> listFinishes();

	void removeFinish(Integer id);

	void updateFinish(Finish finish);
}
