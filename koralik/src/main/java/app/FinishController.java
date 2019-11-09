package app;

import app.model.Finish;
import app.service.FinishService;
import app.service.FinishServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FinishController {

	private final FinishService finishService = new FinishServiceImpl();

	public void addFinish(Finish finish) {
		finishService.addFinish(finish);
	}

	public ObservableList<Finish> listFinishes() {

		ObservableList<Finish> finishList;

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
