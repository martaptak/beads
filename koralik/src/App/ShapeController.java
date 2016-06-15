package App;

import App.Model.Shape;
import App.Service.ShapeService;
import App.Service.ShapeServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShapeController {
	
	private ShapeService shapeService = new ShapeServiceImpl();
	
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
		
		ObservableList<Shape> shapesList = FXCollections.observableArrayList();
		
		shapesList = FXCollections.observableList(shapeService.shapesList());
		
		return shapesList;
	}

}
