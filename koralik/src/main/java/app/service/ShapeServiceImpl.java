package app.service;

import java.util.List;

import app.model.Shape;
import app.dao.ShapeDAO;
import app.dao.ShapeDAOImpl;

public class ShapeServiceImpl implements ShapeService {

	private final ShapeDAO shapeDAO = new ShapeDAOImpl();

	@Override
	public void addShape(Shape shape) {
		shapeDAO.addShape(shape);

	}

	@Override
	public void updateShape(Shape shape) {
		shapeDAO.updateShape(shape);

	}

	@Override
	public void removeShape(Integer id) {
		shapeDAO.removeShape(id);

	}

	@Override
	public List<Shape> shapesList() {

		return shapeDAO.shapesList();
	}


}
