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
@Table(name = "Base", schema = "dbo", catalog = "koraliki")
public class Base implements java.io.Serializable {

	private Integer idBase;
	private String nameBase;
	private String codeBase;
	private Set<Color> colors = new HashSet<Color>(0);

	public Base() {
	}

	public Base(Integer idBase, String nameBase) {
		this.idBase = idBase;
		this.nameBase = nameBase;
	}

	public Base(Integer idBase, String nameBase, String codeBase, Set<Color> colors) {
		this.idBase = idBase;
		this.nameBase = nameBase;
		this.codeBase = codeBase;
		this.colors = colors;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idBase", unique = true, nullable = false)
	public Integer getIdBase() {
		return this.idBase;
	}

	public void setIdBase(Integer idBase) {
		this.idBase = idBase;
	}

	@Column(name = "nameBase", nullable = false)
	public String getNameBase() {
		return this.nameBase;
	}

	public void setNameBase(String nameBase) {
		this.nameBase = nameBase;
	}

	@Column(name = "codeBase", length = 45)
	public String getCodeBase() {
		return this.codeBase;
	}

	public void setCodeBase(String codeBase) {
		this.codeBase = codeBase;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "base")
	public Set<Color> getColors() {
		return this.colors;
	}

	public void setColors(Set<Color> colors) {
		this.colors = colors;
	}
	
	@Override
	public String toString() {
		if(codeBase != null){
			return codeBase + " - " + nameBase;
		}
		else return nameBase;
	}

}
