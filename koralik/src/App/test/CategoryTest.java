package App.test;

import java.util.List;

import App.Model.Category;
import App.dao.CategoryDAO;
import App.dao.CategoryDAOImpl;

public class CategoryTest {

	private CategoryDAO categoryDao;
	
	public CategoryTest(){
		categoryDao = new CategoryDAOImpl();
	}
	
	public void test1(){
		List<Category> parents = categoryDao.listMainParents();
		
		for (Category p: parents){
			System.out.println(p.getCategoryName());
			
			List<Category> childrens = categoryDao.listChildren(p);
			for(Category c: childrens){
				System.out.println("\t" + c.getCategoryName());
			}
		} 
		
		
	}
}
