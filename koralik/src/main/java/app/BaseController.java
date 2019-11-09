package app;

import app.model.Base;
import app.service.BaseService;
import app.service.BaseServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BaseController {
	private final BaseService baseService = new BaseServiceImpl();

	public void addBase(Base base) {
		baseService.addBase(base);
	}

	public void updateBase(Base base) {
		baseService.updateBase(base);
	}

	public void removeBase(Base base) {
		baseService.removeBase(base);
	}

	public ObservableList<Base> basesList() {

		ObservableList<Base> list;

		list = FXCollections.observableList(baseService.basesList());

		return list;
	}
}
