package App.test;


import java.util.List;

import App.Model.Beads;
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
		List<Beads> rozmiary = rozmiaryDao.listBeadsFotTable();
		
		assert(rozmiary.size() > 0);
		System.out.println("Rozmiary size: " + rozmiary.size());
		System.out.println("Parent: " + rozmiary.get(0).getMainCategoryName());
	}
	
	public void test2()
	{
		List<Beads> rozmiary = rozmiaryDao.listBeadsBySize("10/5mm");
		
		assert(rozmiary.size() > 0);
		System.out.println("Rozmiary by rozmiar size: " + rozmiary.size());
		
		for(Beads r: rozmiary)
		{
			System.out.println("Rozmiar: " + r.getSize());
		}
	}
	
	public void testSuperSearch()
	{
		
	}
}
