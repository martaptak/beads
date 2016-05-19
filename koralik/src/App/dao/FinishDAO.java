package App.dao;

import java.util.List;

import App.Model.Finish;

public interface FinishDAO {

	public void addFinish(Finish finish);
	public List<Finish> listFinishes();
	public void removeFinish(Integer id);
	public void updateFinish(Finish finish);

}
