package App.Service;

import java.util.List;

import App.Model.Color;
import App.dao.ColorDAO;
import App.dao.ColorDAOImpl;

public class ColorServiceImpl implements ColorService{
	
	private ColorDAO colorDAO = new ColorDAOImpl();

	@Override
	public void addColor(Color color) {
		colorDAO.addColor(color);
		
	}

	@Override
	public void mergeColor(Color color1, Color color2) {
		colorDAO.mergeColor(color1, color2);
		
	}

	@Override
	public void updateColor(Color color) {
		colorDAO.updateColor(color);
		
	}

	@Override
	public List<Color> listColors() {
		return colorDAO.listColors();
	}

	@Override
	public List<Color> listColorsByName(String name) {
		return colorDAO.listColorsByName(name);
	}

	@Override
	public List<Color> listColorByCode(String code) {
		return colorDAO.listColorByCode(code);
	}

	@Override
	public List<Color> listColorByFamily(int id) {
		return colorDAO.listColorByFamily(id);
	}



}
