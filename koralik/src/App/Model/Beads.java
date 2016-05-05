package App.Model;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "Beads", schema = "dbo", catalog = "koraliki")
public class Beads implements java.io.Serializable {

	private Integer idBeads;
	private Color color;
	private Category category;
	private String size;
	private Boolean owned;
	private String imageUrl;
	private BigDecimal amountOwned;
	private Set<ProductsInStores> productsInStores = new HashSet<ProductsInStores> (0);
	private Set<Finish> finishes = new HashSet<Finish>(0);
	
	public Beads(){
		
	}

	
	public Beads(Color color, Category category, String size) {
		this.color = color;
		this.category = category;
		this.size = size;
	}

	public Beads(Color color, Category category, String size, Boolean owned, String imageUrl, BigDecimal amountOwned, Set<ProductsInStores> productsInStores,
			Set<Finish> finishes) {
		this.color = color;
		this.category = category;
		this.size = size;
		this.owned = owned;
		this.imageUrl = imageUrl;
		this.amountOwned = amountOwned;
		this.productsInStores = productsInStores;
		this.finishes = finishes;
	}

	@Id

	@Column(name = "IdBeads", unique = true, nullable = false)
	public Integer getIdBeads() {
		return this.idBeads;
	}

	public void setIdBeads(Integer idBeads) {
		this.idBeads = idBeads;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Color_idColor", nullable = false)
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Category_idCategory", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "size", length = 50)
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "Owned")
	public Boolean getOwned() {
		return this.owned;
	}

	public void setOwned(Boolean owned) {
		this.owned = owned;
	}

	@Column(name = "imageURL", length = 200)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Column(name = "AmountOwned", precision = 6)
	public BigDecimal getAmountOwned() {
		return this.amountOwned;
	}

	public void setAmountOwned(BigDecimal amountOwned) {
		this.amountOwned = amountOwned;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "beads")
	public Set<ProductsInStores> getProductsInStores() {
		return this.productsInStores;
	}

	public void setproductsInStores(Set<ProductsInStores> productsInStores) {
		this.productsInStores = productsInStores;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "beads_has_finish", schema = "dbo", catalog = "koraliki", joinColumns = {
			@JoinColumn(name = "Beads_IdBeads", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Finish_idFinish", nullable = false, updatable = false) })
	public Set<Finish> getFinishes() {
		return this.finishes;
	}

	public void setFinishes(Set<Finish> finishes) {
		this.finishes = finishes;
	}


	@Transient
	public String getColorName() {
		return color.getColorName();
	}

	public void setColorName(String colorName) {
		this.color.setColorName(colorName);
	}
	
	

	@Transient
	public String getCategoryName() {
		return category.getCategoryName();
	}

	public void setCategoryName(String categoryName) {
		this.category.setCategoryName(categoryName);
	}

	@Transient
	public String getColorFamily() {
		return color.getColorFamily().getColorFamilyName();
	}

	public void setColorFamily(String colorFamily) {
		this.color.getColorFamily().setColorFamilyName(colorFamily);
	}

	@Transient
	public String getSubcategoryName() {
		return category.getParentCategory().getCategoryName();
	}

	public void setSubcategoryName(String subcategoryName) {
		this.category.setParentCategory(new Category(subcategoryName));
	}

	@Transient
	public String getMainCategoryName() {
		return category.getParentCategory().getParentCategory().getCategoryName();
	}

	public void setMainCategoryName(String mainCategoryName) {
		this.category.getParentCategory().setCategoryName(mainCategoryName);
	}
	
	@Transient
	public Category getParentCategory(){
		return category.getParentCategory().getParentCategory();
	}
	
	public void setParentCategory(Category parentCategory){
		this.category.getParentCategory().setParentCategory(parentCategory);
	}
	
	@Transient
	public Category getSubcategory(){
		return category.getParentCategory();
	}
	
	public void setSubcategory(Category subcategory){
		this.category.setParentCategory(subcategory);
	}
	
	@Transient
	public String getFinishesNames(){
		
		StringBuilder result = new StringBuilder();
		
		for(Finish f: finishes){
			
			result.append(f.getNameFinish());
	        result.append(", ");
			
		}
		
		return result.length() > 0 ? result.substring(0, result.length() - 2): "";
	}
	
	public void setFinishesNames(String finish){
		
	}

	
}