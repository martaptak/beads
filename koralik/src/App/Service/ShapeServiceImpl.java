package App.Service;

import java.util.List;

import App.Model.Shape;
import App.dao.ShapeDAO;
import App.dao.ShapeDAOImpl;

public class ShapeServiceImpl implements ShapeService{
	
	private ShapeDAO shapeDAO = new ShapeDAOImpl();

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
