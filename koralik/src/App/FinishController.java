package App;

import App.Model.Finish;
import App.Service.FinishService;
import App.Service.FinishServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FinishController {
	
	FinishService finishService = new FinishServiceImpl();

	public void addFinish(Finish finish) {
		finishService.addFinish(finish);
	}

	public ObservableList<Finish> listFinishes() {

		ObservableList<Finish> finishList = FXCollections.observableArrayList();

		finishList = FXCollections.observableList(finishService.listFinishes());
		return finishList;

	}

	public void removeFinish(Integer id) {
		finishService.removeFinish(id);
	}

	public void updateFinish(Finish finish) {
		finishService.updateFinish(finish);
	}

}
