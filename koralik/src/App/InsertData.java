package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import App.Model.Beads;
import App.Model.Category;
import App.Model.Color;
import App.Model.ProductsInStores;
import App.Model.Stores;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class InsertData {

	private static List<Beads> beads;
	private static BeadController beadController = new BeadController();
	private static ProductsInStoresController productController = new ProductsInStoresController();
	private static CategoryController categoryController = new CategoryController();
	private static ColorController colorController = new ColorController();
	private static ColorFamilyController colorFamilyController = new ColorFamilyController();
	private static ProcessBuilder builder;
	private static Process process;

	public static int[] readXMLFile(Stores store, File xmlFile) {

		int count = 0;
		int news = 0;
		int total = 0;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			NodeList list = doc.getElementsByTagName("record");
			total = list.getLength();

			for (int temp = 0; temp < total; temp++) {

				Node node = list.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;
					String name = element.getElementsByTagName("Name").item(0).getTextContent();
					String image = element.getElementsByTagName("Image").item(0).getTextContent();
					String amount = element.getElementsByTagName("Amount").item(0).getTextContent();
					String unit = element.getElementsByTagName("Unit").item(0).getTextContent();
					String website = element.getElementsByTagName("Website").item(0).getTextContent();
					String mainCategoryName = element.getElementsByTagName("Category").item(0).getTextContent();
					String subcategoryName = element.getElementsByTagName("Subcategory").item(0).getTextContent();
					NodeList type = element.getElementsByTagName("Type");
					String typeName = "";
					if (type.item(0) != null) {
						typeName = element.getElementsByTagName("Type").item(0).getTextContent();
					}
					String colorName = element.getElementsByTagName("Color").item(0).getTextContent();
					String size = element.getElementsByTagName("Size").item(0).getTextContent();
					NodeList code = element.getElementsByTagName("Code");
					String codeName = "";
					if (code.item(0) != null) {
						codeName = element.getElementsByTagName("Code").item(0).getTextContent();
					}

					beads = beadController.findBead(element);

					if (beads.size() == 1 && !checkIfExistes(website)) {

						Beads bead = beads.get(0);

						ProductsInStores product = new ProductsInStores(bead, store, amount, unit, true, website, name,
								image);

						productController.addProduct(product);
						count++;
					} else if (beads.size() == 0 && !checkIfExistes(website)) {

						Category mainCategory = null;
						Category subCategory = null;
						Category typeCategory = null;

						if (findCategoryBrench(typeName, subcategoryName, mainCategoryName)[0] == null) {
							mainCategory = new Category(mainCategoryName, 0);
							categoryController.add(mainCategory);
							subCategory = new Category(subcategoryName, mainCategory);
							categoryController.add(subCategory);
							typeCategory = new Category(typeName, subCategory);
							categoryController.add(typeCategory);
						} else if (findMainCategory(mainCategoryName) != null) {
							mainCategory = findMainCategory(mainCategoryName);

							subCategory = findSubCategory(mainCategory, subcategoryName);
							if (subCategory == null) {
								subCategory = new Category(subcategoryName, mainCategory);
								categoryController.add(subCategory);
							}
							typeCategory = findType(subCategory, typeName);
							if (typeCategory == null) {
								typeCategory = new Category(typeName, subCategory);
								categoryController.add(typeCategory);
							}
						}

						Color newColor = findColor(colorName, codeName);
						if (newColor == null) {
							newColor = new Color(colorName, codeName, colorFamilyController.getDefaultColorFamily());
							colorController.addColor(newColor);
						}

						Beads newBead = new Beads(newColor, typeCategory, size, image, false);
						beadController.addBead(newBead);

						ProductsInStores newProduct = new ProductsInStores(newBead, store, amount, unit, true, website,
								name, image);
						productController.addProduct(newProduct);
						news++;
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		int[] values = { count, news, total };

		return values;
	}

	public static void startScrapy(Stores store) throws IOException {

		builder = new ProcessBuilder("cmd.exe", "/c",
				"cd \"C:\\Users\\marta\\git\\koraliki\\koralik\\scrapy scripts\\marta\" && scrapy crawl "
						+ store.getStoreName());
		builder.redirectErrorStream(true);
		process = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}

	}

	static public void stopScrapy() {
		process.destroy();
	}

	private static boolean checkIfExistes(String website) {

		List<ProductsInStores> productList = productController.listProducts();

		for (ProductsInStores product : productList) {
			if (product.getWebsite().equals(website))
				return true;
		}

		return false;
	}

	private static Category[] findCategoryBrench(String typeName, String subCategoryName, String mainCategoryName) {

		Category main = null;
		Category subCategory = null;
		Category typeCategory = null;

		List<Category> mainCategoryList = categoryController.listMainParents();

		for (Category c : mainCategoryList) {
			if (c.getCategoryName().equals(mainCategoryName)) {
				main = c;
				List<Category> subCategoryList = categoryController.listChildren(c);
				for (Category sub : subCategoryList) {
					if (sub.getCategoryName().equals(subCategoryName)) {
						subCategory = sub;
						List<Category> typeList = categoryController.listChildren(sub);
						for (Category type : typeList) {
							if (type.getCategoryName().equals(typeName)) {
								typeCategory = type;
							}
						}
					}
				}
			}
		}

		Category[] result = { main, subCategory, typeCategory };

		return result;
	}

	private static Category findMainCategory(String mainCategoryName) {
		List<Category> mainCategoryList = categoryController.listMainParents();
		for (Category c : mainCategoryList) {
			if (c.getCategoryName().equals(mainCategoryName)) {
				return c;
			}
		}
		return null;
	}

	private static Category findSubCategory(Category main, String subCategoryName) {
		List<Category> subCategoryList = categoryController.listChildren(main);
		for (Category sub : subCategoryList) {
			if (sub.getCategoryName().equals(subCategoryName)) {
				return sub;
			}
		}
		return null;
	}

	private static Category findType(Category subcategory, String typeName) {
		List<Category> typeList = categoryController.listChildren(subcategory);
		for (Category type : typeList) {
			if (type.getCategoryName().equals(typeName)) {
				return type;
			}
		}
		return null;
	}

	private static Color findColor(String colorName, String code) {

		List<Color> colorList = colorController.listColors();

		for (Color color : colorList) {
			if (color.getColorCode().equals(code) && color.getColorName().equals(colorName)) {
				return color;
			}
		}

		return null;
	}
}