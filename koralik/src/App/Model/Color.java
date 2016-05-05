package App.Model;

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

	private int idColor;
	private String colorName;
	private String colorCode;
	private ColorFamily colorFamily;
	private Set<Beads> beads = new HashSet<Beads>(0);

	public Color() {
		this.colorName = null;
		this.colorCode = null;
	}

	public Color(int idColor, String colorName) {
		this.idColor = idColor;
		this.colorName = colorName;
	}

	public Color(int idColor, String colorName, String colorCode, ColorFamily colorFamily, Set<Beads> beads) {
		this.idColor = idColor;
		this.colorName = colorName;
		this.colorCode = colorCode;
		this.colorFamily = colorFamily;
		this.beads = beads;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idColor", unique = true, nullable = false)
	public int getIdColor() {
		return this.idColor;
	}

	public void setIdColor(int idColor) {
		this.idColor = idColor;
	}

	@Column(name = "color_name", nullable = false, length = 100)
	public String getColorName() {
		return this.colorName;
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
	public Set<Beads> getBeads() {
		return this.beads;
	}

	public void setBeads(Set<Beads> beads) {
		this.beads = beads;
	}
	
	@Transient
	public String getColorFamilyName() {
		return colorFamily.getColorFamilyName();
	}

	public void setColorFamilyName(String colorFamily) {
		this.colorFamily.setColorFamilyName(colorFamily);
	}
	
	@Override
	public String toString(){
		return this.getColorName() + " " + this.getColorCode();
	}

}
