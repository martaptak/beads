package App.Model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "stores", schema = "dbo", catalog = "koraliki")
public class Stores implements java.io.Serializable {
	private Integer idStores;
	private String storeName;
	private String website;
	private Date updateDate;
	private String country;
	private Set<ProductsInStores> productsInStores = new HashSet<ProductsInStores>(0);

	public Stores() {
		this.storeName = null;
		this.website = null;

	}

	public Stores(String storeName) {
		this.storeName = storeName;
	}

	public Stores(String storeName, String website, Date updateDate, String country, Set<ProductsInStores> productsInStores) {
		this.storeName = storeName;
		this.website = website;
		this.updateDate = updateDate;
		this.country = country;
		this.productsInStores = productsInStores;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idStores", unique = true, nullable = false)
	public Integer getIdStores() {
		return this.idStores;
	}

	public void setIdStores(Integer idStores) {
		this.idStores = idStores;
	}

	@Column(name = "NameStores", unique = true, nullable = false, length = 80)
	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(name = "website", length = 200)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	@Column(name = "Country")
	public String getCountry() {
		return this.country;
	}
	public void setCountry(String country){
		this.country = country;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updateDate")
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stores")
	public Set<ProductsInStores> getProductsinstores() {
		return this.productsInStores;
	}

	public void setProductsinstores(Set<ProductsInStores> productsInStores) {
		this.productsInStores = productsInStores;
	}

	@Override
	public String toString() {
		return this.storeName;
	}

}