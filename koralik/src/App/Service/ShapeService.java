package App.Service;

import java.util.List;

import App.Model.Shape;

public interface ShapeService {
	
	public void addShape(Shape shape);
	public void updateShape(Shape shape);
	public void removeShape(Integer id);
	public List<Shape> shapesList();

}
