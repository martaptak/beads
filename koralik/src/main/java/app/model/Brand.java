package app.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Objects;
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
@Table(name = "Brands", schema = "dbo", catalog = "koraliki")
public class Brand implements java.io.Serializable {

	private Integer idBrand;
	private String brandName;
	private Set<Bead> beads = new HashSet<>(0);

	public Brand() {
	}

	public Brand(String brandName) {
		this.brandName = brandName;
	}

	public Brand(String brandName, Set<Bead> beads) {

		this.brandName = brandName;
		this.beads = beads;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_brand", unique = true, nullable = false)
	public Integer getIdBrand() {
		return this.idBrand;
	}

	public void setIdBrand(Integer idBrand) {
		this.idBrand = idBrand;
	}

	@Column(name = "brandName", length = 100)
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brands")
	public Set<Bead> getBeads() {
		return this.beads;
	}

	public void setBeads(Set<Bead> beads) {
		this.beads = beads;
	}

	@Override
	public String toString() {
		return this.brandName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!Brand.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final Brand other = (Brand) obj;
		return Objects.equals(this.idBrand, other.idBrand);
	}

}
