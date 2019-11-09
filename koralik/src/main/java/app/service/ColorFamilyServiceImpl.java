package app.service;

import java.util.List;

import app.model.ColorFamily;
import app.dao.ColorFamilyDAO;
import app.dao.ColorFamilyDAOImpl;

public class ColorFamilyServiceImpl implements ColorFamilyService {

	private final ColorFamilyDAO colorFamilyDAO = new ColorFamilyDAOImpl();

	@Override
	public void addColorFamily(ColorFamily colorFamily) {
		colorFamilyDAO.addColorFamily(colorFamily);
	}

	@Override
	public void removeColorFamily(ColorFamily colorFamily) {
		colorFamilyDAO.removeColorFamily(colorFamily);
	}


	@Override
	public void updateColorFamily(ColorFamily colorFamily) {
		colorFamilyDAO.updateColorFamily(colorFamily);

	}

	@Override
	public List<ColorFamily> listColorFamily() {
		return colorFamilyDAO.listColorFamily();
	}

	@Override
	public ColorFamily getDefaultColorFamily() {
		return colorFamilyDAO.getDefaultColorFamily();
	}

}
