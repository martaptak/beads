package App.Model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Countries", schema = "dbo", catalog = "koraliki")
public class Country implements java.io.Serializable {

	private Integer idCountry;
	private String nameCountry;
	private Set<Store> stores = new HashSet<Store>(0);

	public Country() {
	}

	public Country(String nameCountry) {
		
		this.nameCountry = nameCountry;
	}

	public Country(String nameCountry, Set<Store> stores) {
		this.nameCountry = nameCountry;
		this.stores = stores;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_Country", unique = true, nullable = false)
	public Integer getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}

	@Column(name = "nameCountry", nullable = false)
	public String getNameCountry() {
		return this.nameCountry;
	}

	public void setNameCountry(String nameCountry) {
		this.nameCountry = nameCountry;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "countries")
	public Set<Store> getStores() {
		return this.stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}
	
	@Override
	public String toString() {
		return this.nameCountry;
	}	

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!Country.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final Country other = (Country) obj;
		if ((this.idCountry == null) ? (other.idCountry != null)
				: !this.idCountry.equals(other.idCountry)) {
			return false;
		}

		return true;
	}

}
