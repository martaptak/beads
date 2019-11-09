package app;

import app.model.Shape;
import app.service.ShapeService;
import app.service.ShapeServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShapeController {

	private final ShapeService shapeService = new ShapeServiceImpl();

	public void addShape(Shape shape) {

		shapeService.addShape(shape);
	}

	public void updateShape(Shape shape) {
		shapeService.updateShape(shape);
	}

	public void removeShape(Integer id) {
		shapeService.removeShape(id);
	}

	public ObservableList<Shape> shapesList() {

		ObservableList<Shape> shapesList;

		shapesList = FXCollections.observableList(shapeService.shapesList());

		return shapesList;
	}

}
