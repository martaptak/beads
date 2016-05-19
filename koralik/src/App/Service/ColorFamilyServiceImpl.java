package App.Service;

import java.util.List;

import App.Model.ColorFamily;
import App.dao.ColorFamilyDAO;
import App.dao.ColorFamilyDAOImpl;

public class ColorFamilyServiceImpl implements ColorFamilyService {
	
	private ColorFamilyDAO colorFamilyDAO = new ColorFamilyDAOImpl();

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
