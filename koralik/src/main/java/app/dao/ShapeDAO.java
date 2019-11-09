package app.dao;

import java.util.List;

import app.model.Shape;

public interface ShapeDAO {

	void addShape(Shape shape);

	void updateShape(Shape shape);

	void removeShape(Integer id);

	List<Shape> shapesList();


}
