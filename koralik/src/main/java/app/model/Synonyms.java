package app.model;


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
@Table(name = "Synonyms", schema = "dbo", catalog = "koraliki")
public class Synonyms implements java.io.Serializable {

	private Integer idSynonym;
	private Color color;
	private String nameSynonym;

	public Synonyms() {
	}

	public Synonyms(Color color, String nameSynonym) {
		this.color = color;
		this.nameSynonym = nameSynonym;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idSynonym", unique = true, nullable = false)
	public Integer getIdSynonym() {
		return this.idSynonym;
	}

	public void setIdSynonym(Integer idSynonym) {
		this.idSynonym = idSynonym;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idColor", nullable = false)
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Column(name = "nameSynonym", nullable = false, length = 100)
	public String getNameSynonym() {
		return this.nameSynonym;
	}

	public void setNameSynonym(String nameSynonym) {
		this.nameSynonym = nameSynonym;
	}

	@Override
	public String toString() {
		return nameSynonym;
	}

}
