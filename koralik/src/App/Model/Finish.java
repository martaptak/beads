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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Finish", schema = "dbo", catalog = "koraliki")
public class Finish implements java.io.Serializable {

	private Integer idFinish;
	private String nameFinish;
	private Set<Beads> beads = new HashSet<Beads>(0);

	public Finish() {
	}

	public Finish(String nameFinish) {
		this.nameFinish = nameFinish;
	}

	public Finish(String nameFinish, Set<Beads> beads) {
		this.nameFinish = nameFinish;
		this.beads = beads;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idFinish", unique = true, nullable = false)
	public Integer getIdFinish() {
		return this.idFinish;
	}

	public void setIdFinish(Integer idFinish) {
		this.idFinish = idFinish;
	}

	@Column(name = "NameFinish", nullable = false, length = 80)
	public String getNameFinish() {
		return this.nameFinish;
	}

	public void setNameFinish(String nameFinish) {
		this.nameFinish = nameFinish;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "beads_has_finish", schema = "dbo", catalog = "koraliki", joinColumns = {
			@JoinColumn(name = "Finish_idFinish", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Beads_IdBeads", nullable = false, updatable = false) })

	public Set<Beads> getBeads() {
		return this.beads;
	}

	public void setBeads(Set<Beads> beads) {
		this.beads = beads;
	}

	
	@Override
	public String toString() {
		return nameFinish;
	}
}
