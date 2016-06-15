package App.test;


import java.util.List;

import App.Model.Bead;
import App.dao.BeadDAO;
import App.dao.BeadDAOImpl;

public class BeadTest {
	private BeadDAO rozmiaryDao;
	
	public BeadTest()
	{
		rozmiaryDao = new BeadDAOImpl();
	}
	
	public void test1()
	{
		List<Bead> rozmiary = rozmiaryDao.listBeadsForTable();
		
		assert(rozmiary.size() > 0);
		System.out.println("Rozmiary size: " + rozmiary.size());
		System.out.println("Parent: " + rozmiary.get(0).getMainCategoryName());
	}
	
	public void test2()
	{
		List<Bead> rozmiary = rozmiaryDao.listBeadsBySize("10/5mm");
		
		assert(rozmiary.size() > 0);
		System.out.println("Rozmiary by rozmiar size: " + rozmiary.size());
		
		for(Bead r: rozmiary)
		{
			System.out.println("Rozmiar: " + r.getSize());
		}
	}
	
	public void test3(){
		List<Bead> rozmiary = rozmiaryDao.listBeads();
		
		assert(rozmiary.size() > 0);
		System.out.println("Rozmiary by rozmiar size: " + rozmiary.size());
		
		for(Bead r: rozmiary)
		{
			System.out.println("Rozmiar: " + r.getSize());
			System.out.println("Rozmiar: " + r.getImageUrl());
		}
		
	}
	
	public void testSuperSearch()
	{
		
	}
}
