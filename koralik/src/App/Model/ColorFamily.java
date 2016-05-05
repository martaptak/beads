package App.Model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ColorFamily", schema = "dbo", catalog = "koraliki")
public class ColorFamily implements java.io.Serializable {

	private Integer idColorFamily;
	private String colorFamilyName;
	private Set<Color> colors = new HashSet<Color>(0);

	public ColorFamily() {
	}

	public ColorFamily(String colorFamilyName, Set<Color> colors) {
		this.colorFamilyName = colorFamilyName;
		this.colors = colors;
	}

	@Id

	@Column(name = "idColorFamily", unique = true, nullable = false)
	public Integer getIdColorFamily() {
		return this.idColorFamily;
	}

	public void setIdColorFamily(Integer idColorFamily) {
		this.idColorFamily = idColorFamily;
	}

	@Column(name = "ColorFamilyName", length = 45)
	public String getColorFamilyName() {
		return this.colorFamilyName;
	}

	public void setColorFamilyName(String colorFamilyName) {
		this.colorFamilyName = colorFamilyName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "colorFamily")
	public Set<Color> getColors() {
		return this.colors;
	}

	public void setColors(Set<Color> colors) {
		this.colors = colors;
	}

	@Override
	public String toString() {
		return colorFamilyName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!ColorFamily.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final ColorFamily other = (ColorFamily) obj;
		if ((this.idColorFamily == null) ? (other.idColorFamily != null)
				: !this.idColorFamily.equals(other.idColorFamily)) {
			return false;
		}

		return true;
	}

}
