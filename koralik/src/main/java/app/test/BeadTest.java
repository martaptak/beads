package app.test;


import java.util.List;

import app.model.Bead;
import app.dao.BeadDAO;
import app.dao.BeadDAOImpl;

class BeadTest {
	private final BeadDAO rozmiaryDao;

	public BeadTest() {
		rozmiaryDao = new BeadDAOImpl();
	}

	public void test1() {
		List<Bead> sizes = rozmiaryDao.listBeadsForTable();

		assert (sizes.size() > 0);
		System.out.println("Rozmiary size: " + sizes.size());
		System.out.println("Parent: " + sizes.get(0).getMainCategoryName());
	}

	public void test2() {
		List<Bead> sizes = rozmiaryDao.listBeadsBySize("10/5mm");

		assert (sizes.size() > 0);
		System.out.println("Rozmiary by rozmiar size: " + sizes.size());

		for (Bead r : sizes) {
			System.out.println("Rozmiar: " + r.getSize());
		}
	}

	public void test3() {
		List<Bead> rozmiary = rozmiaryDao.listBeads();

		assert (rozmiary.size() > 0);
		System.out.println("Rozmiary by rozmiar size: " + rozmiary.size());

		for (Bead r : rozmiary) {
			System.out.println("Rozmiar: " + r.getSize());
			System.out.println("Rozmiar: " + r.getImageUrl());
		}

	}

}
