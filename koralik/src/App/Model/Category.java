package App.Model;

import java.util.ArrayList;

// Generated 2016-03-18 14:42:09 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Category", schema = "dbo", catalog = "koraliki")
public class Category implements java.io.Serializable {

	private int idCategory;
	private Category parentCategory;
	private String categoryName;
	private int categoryDepth;
	private Set<Category> childrenCategories = new HashSet<Category>(0);
	private Set<Beads> beads = new HashSet<Beads>(0);

	public Category() {
	}

	public Category (String categoryName){
		this.categoryName = categoryName;
	}
	
	public Category(String categoryName, int depth) {
		this.categoryName = categoryName;
		this.categoryDepth = depth;
	}
	
	public Category(String categoryName, Category parentCategory ) {
		this.categoryName = categoryName;
		this.parentCategory = parentCategory;
		this.categoryDepth = parentCategory.getDepth() + 1;
	}

	public Category(Category parentCategory, String categoryName, int depth, Set<Beads> beads) {
		this.parentCategory = parentCategory;
		this.categoryName = categoryName;
		this.categoryDepth = depth;
		this.beads = beads;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_category", unique = true, nullable = false)
	public int getidCategory() {
		return this.idCategory;
	}

	public void setidCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	@Column(name = "category_name", nullable = false, length = 255)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "category_parent", nullable = true)
	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	@Column(name = "category_depth", nullable = true)
	public int getDepth() {
		return this.categoryDepth;
	}

	public void setDepth(int depth) {
		this.categoryDepth = depth;
	}

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory")
	public Set<Category> getChildrenCategories() {
		return this.childrenCategories;
	}

	public void setChildrenCategories(Set<Category> childrenCategories) {
		this.childrenCategories = childrenCategories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Beads> getBeads() {
		return this.beads;
	}

	public void setBeads(Set<Beads> beads) {
		this.beads = beads;
	}

}
