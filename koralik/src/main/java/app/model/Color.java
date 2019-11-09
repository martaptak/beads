package app.model;

import static javax.persistence.GenerationType.IDENTITY;

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
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "Color", schema = "dbo", catalog = "koraliki")
public class Color implements java.io.Serializable {

	private Integer idColor;
	private Coatings coatings;
	private Base base;
	private String colorName;
	private String colorCode;
	private ColorFamily colorFamily;
	private Set<Synonyms> synonims = new HashSet<>(0);
	private Set<Bead> beads = new HashSet<>(0);

	public Color() {
		this.colorName = null;
		this.colorCode = null;
	}

	public Color(String colorName, String colorCode) {
		this.colorName = colorName;
		this.colorCode = colorCode;
	}

	public Color(String colorName, String colorCode, ColorFamily colorFamily) {
		this.colorName = colorName;
		this.colorCode = colorCode;
		this.colorFamily = colorFamily;
	}

	public Color(String colorName, String colorCode, ColorFamily colorFamily, Set<Bead> beads) {
		this.colorName = colorName;
		this.colorCode = colorCode;
		this.colorFamily = colorFamily;
		this.beads = beads;
	}

	public Color(Coatings coatings, Base base, String colorName, String colorCode, ColorFamily colorFamily) {
		this.coatings = coatings;
		this.base = base;
		this.colorName = colorName;
		this.colorCode = colorCode;
		this.colorFamily = colorFamily;
	}

	public Color(Coatings coatings, Base base, String colorName, String colorCode, ColorFamily colorFamily,
	             Set<Synonyms> synonims, Set<Bead> beads) {
		this.coatings = coatings;
		this.base = base;
		this.colorName = colorName;
		this.colorCode = colorCode;
		this.colorFamily = colorFamily;
		this.synonims = synonims;
		this.beads = beads;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idColor", unique = true, nullable = false)
	public Integer getIdColor() {
		return this.idColor;
	}

	public void setIdColor(Integer idColor) {
		this.idColor = idColor;
	}

	@Column(name = "color_name", nullable = false, length = 100)
	public String getColorName() {
		return this.colorName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCoating")
	public Coatings getCoatings() {
		return this.coatings;
	}

	public void setCoatings(Coatings coatings) {
		this.coatings = coatings;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBase")
	public Base getBase() {
		return this.base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	@Column(name = "codeColor", nullable = false, length = 45)
	public String getColorCode() {
		return this.colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ColorFamily_idColorFamily", nullable = false)
	public ColorFamily getColorFamily() {
		return this.colorFamily;
	}

	public void setColorFamily(ColorFamily colorFamily) {
		this.colorFamily = colorFamily;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "color")
	public Set<Bead> getBeads() {
		return this.beads;
	}

	public void setBeads(Set<Bead> beads) {
		this.beads = beads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "color")
	public Set<Synonyms> getSynonims() {
		return this.synonims;
	}

	public void setSynonims(Set<Synonyms> synonims) {
		this.synonims = synonims;
	}

	@Transient
	public String getColorFamilyName() {
		return colorFamily.getColorFamilyName();
	}

	public void setColorFamilyName(String colorFamily) {
		this.colorFamily.setColorFamilyName(colorFamily);
	}

	@Override
	public String toString() {
		return this.getColorName() + " " + this.getColorCode();
	}

}
