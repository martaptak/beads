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
@Table(name = "Shapes", schema = "dbo", catalog = "koraliki")
public class Shape implements java.io.Serializable {

	private Integer idShape;
	private String shapeName;
	private Set<Bead> beads = new HashSet<>(0);

	public Shape() {
	}

	public Shape(String shapeName) {
		this.shapeName = shapeName;
	}

	public Shape(String shapeName, Set<Bead> beads) {

		this.shapeName = shapeName;
		this.beads = beads;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_shape", unique = true, nullable = false)
	public Integer getIdShape() {
		return this.idShape;
	}

	public void setIdShape(Integer idShape) {
		this.idShape = idShape;
	}

	@Column(name = "shape_name", length = 50)
	public String getShapeName() {
		return this.shapeName;
	}

	public void setShapeName(String shapeName) {
		this.shapeName = shapeName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shapes")
	public Set<Bead> getBeads() {
		return this.beads;
	}

	public void setBeads(Set<Bead> beads) {
		this.beads = beads;
	}

	@Override
	public String toString() {
		return this.shapeName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!Shape.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final Shape other = (Shape) obj;
		return Objects.equals(this.idShape, other.idShape);
	}


}
