package App.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.scene.control.Hyperlink;

@SuppressWarnings("serial")
@Entity
@Table(name = "productsinstores", schema = "dbo", catalog = "koraliki")
public class ProductsInStores implements java.io.Serializable {

	private Integer idProductInStores;
	private Beads beads;
	private Stores stores;
	private Integer amount;
	private String unit;
	private Boolean avibility;
	private String website;

	public ProductsInStores() {
		
	}
	
	public ProductsInStores(Beads beads){
		this.beads = beads;
		this.stores = new Stores();
		this.amount = null;
		this.unit = null;
		this.website = null;
		this.avibility = false;
		
	}

	public ProductsInStores(Integer idProductInStores, Beads beads, Stores stores, Integer amount, String unit,
			String website) {
		this.idProductInStores = idProductInStores;
		this.beads = beads;
		this.stores = stores;
		this.amount = amount;
		this.unit = unit;
		this.website = website;
	}

	public ProductsInStores(Integer idProductInStores, Beads beads, Stores stores, Integer amount, String unit,
			Boolean avibility, String website) {
		this.idProductInStores = idProductInStores;
		this.beads = beads;
		this.stores = stores;
		this.amount = amount;
		this.unit = unit;
		this.avibility = avibility;
		this.website = website;
	}

	@Id

	@Column(name = "idProductInStores", unique = true, nullable = false)
	public Integer getIdProductInStores() {
		return this.idProductInStores;
	}

	public void setIdProductInStores(Integer idProductInStores) {
		this.idProductInStores = idProductInStores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Beads_IdBeads", nullable = false)
	public Beads getBeads() {
		return this.beads;
	}

	public void setBeads(Beads beads) {
		this.beads = beads;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Stores_idStores", nullable = false)
	public Stores getStores() {
		return this.stores;
	}

	public void setStores(Stores stores) {
		this.stores = stores;
	}

	@Column(name = "Amount", nullable = false)
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Column(name = "Unit", nullable = false, length = 10)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "Avibility")
	public Boolean getAvibility() {
		return this.avibility;
	}

	public void setAvibility(Boolean avibility) {
		this.avibility = avibility;
	}

	@Column(name = "Website")
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Transient
	public String getAmountWithUnit() {
		return this.amount + " " + this.unit;
	}

	public void setAmountWithUnit(String amountWithUnit) {
		String[] split = amountWithUnit.split(" ");
		this.amount = Integer.parseInt(split[0]);
		this.unit = split[1];
	}
	@Transient
	public Hyperlink getUrl(){
		return new Hyperlink(this.website);
	}
	public void setUrl(Hyperlink url){
		this.website = url.getText();
	}
	
	

}