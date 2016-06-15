package App.Model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "stores", schema = "dbo", catalog = "koraliki")
public class Store implements java.io.Serializable {
	private Integer idStores;
	private String storeName;
	private String website;
	private Calendar updateDate;
	private Country countries;
	private Set<ProductsInStores> productsInStores = new HashSet<ProductsInStores>(0);

	public Store() {
		this.updateDate = Calendar.getInstance();

	}

	public Store(String storeName) {
		this.storeName = storeName;
	}

	public Store(Country countries, String nameStores, String website, Calendar updateDate) {		
		this.countries = countries;
		this.storeName = nameStores;
		this.website = website;
		this.updateDate = updateDate;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Country_idCountry")
	public Country getCountries() {
		return this.countries;
	}

	public void setCountries(Country countries) {
		this.countries = countries;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updateDate")
	public Calendar getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Calendar updateDate) {
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
		

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!Store.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final Store other = (Store) obj;
		if ((this.idStores == null) ? (other.idStores != null)
				: !this.idStores.equals(other.idStores)) {
			return false;
		}

		return true;
	}

}