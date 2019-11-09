package app.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Category", schema = "dbo", catalog = "koraliki")
public class Category implements java.io.Serializable {

	private Integer idCategory;
	private Category parentCategory;
	private String categoryName;
	private int categoryDepth;
	private Set<Category> childrenCategories = new HashSet<>(0);
	private Set<Bead> beads = new HashSet<>(0);

	public Category() {

	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
		this.categoryDepth = 0;
	}

	public Category(String categoryName, int depth) {
		this.categoryName = categoryName;
		this.categoryDepth = depth;
	}

	public Category(String categoryName, Category parentCategory) {
		this.categoryName = categoryName;
		this.parentCategory = parentCategory;
		this.categoryDepth = parentCategory.getDepth() + 1;
	}

	public Category(Category parentCategory, String categoryName, int depth, Set<Bead> beads) {
		this.parentCategory = parentCategory;
		this.categoryName = categoryName;
		this.beads = beads;
		this.categoryDepth = depth;
	}

	public Category(String categoryName, Set<Bead> beads) {
		this.categoryName = categoryName;
		this.beads = beads;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idCategory", unique = true, nullable = false)
	public Integer getIdCategory() {
		return this.idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	@Column(name = "nameCategory", nullable = false, length = 80)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Category_idCategory")
	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	@Column(name = "category_depth")
	public int getDepth() {
		return this.categoryDepth;
	}

	public void setDepth(int depth) {
		this.categoryDepth = depth;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "parentCategory")
	public Set<Category> getChildrenCategories() {
		return this.childrenCategories;
	}

	public void setChildrenCategories(Set<Category> childrenCategories) {
		this.childrenCategories = childrenCategories;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "category")
	public Set<Bead> getBeads() {
		return this.beads;
	}

	public void setBeads(Set<Bead> beads) {
		this.beads = beads;
	}

	@Override
	public String toString() {
		return this.categoryName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!Category.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final Category other = (Category) obj;
		return Objects.equals(this.idCategory, other.idCategory);
	}

}