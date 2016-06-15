package App.Model;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Synonims", schema = "dbo", catalog = "koraliki")
public class Synonims implements java.io.Serializable {

	private Integer idSynonim;
	private Color color;
	private String nameSynonim;

	public Synonims() {
	}

	public Synonims(Color color, String nameSynonim) {
		this.color = color;
		this.nameSynonim = nameSynonim;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idSynonim", unique = true, nullable = false)
	public Integer getIdSynonim() {
		return this.idSynonim;
	}

	public void setIdSynonim(Integer idSynonim) {
		this.idSynonim = idSynonim;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idColor", nullable = false)
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Column(name = "nameSynonim", nullable = false, length = 100)
	public String getNameSynonim() {
		return this.nameSynonim;
	}

	public void setNameSynonim(String nameSynonim) {
		this.nameSynonim = nameSynonim;
	}
	
	@Override
	public String toString() {
		return nameSynonim;
	}

}
