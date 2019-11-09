package app.model;

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
@Table(name = "Coatings", schema = "dbo", catalog = "koraliki")
public class Coatings implements java.io.Serializable {

	private Integer idCoating;
	private String nameCoating;
	private String codeCoating;
	private Set<Color> colors = new HashSet<>(0);

	public Coatings() {
	}

	public Coatings(Integer idCoating) {
		this.idCoating = idCoating;
	}

	public Coatings(Integer idCoating, String nameCoating, String codeCoating, Set<Color> colors) {
		this.idCoating = idCoating;
		this.nameCoating = nameCoating;
		this.codeCoating = codeCoating;
		this.colors = colors;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idCoating", unique = true, nullable = false)
	public Integer getIdCoating() {
		return this.idCoating;
	}

	public void setIdCoating(Integer idCoating) {
		this.idCoating = idCoating;
	}

	@Column(name = "nameCoating")
	public String getNameCoating() {
		return this.nameCoating;
	}

	public void setNameCoating(String nameCoating) {
		this.nameCoating = nameCoating;
	}

	@Column(name = "codeCoating", length = 50)
	public String getCodeCoating() {
		return this.codeCoating;
	}

	public void setCodeCoating(String codeCoating) {
		this.codeCoating = codeCoating;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "coatings")
	public Set<Color> getColors() {
		return this.colors;
	}

	public void setColors(Set<Color> colors) {
		this.colors = colors;
	}

	@Override
	public String toString() {
		if (codeCoating != null) {
			return codeCoating + " - " + nameCoating;
		} else {
			return nameCoating;
		}
	}

}
