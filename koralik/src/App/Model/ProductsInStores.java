package App.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	private String amount;
	private String unit;
	private Boolean avibility;
	private String website;
	private String name;
	private String image;

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

	public ProductsInStores(Integer idProductInStores, Beads beads, Stores stores, String amount, String unit,
			String website) {
		this.idProductInStores = idProductInStores;
		this.beads = beads;
		this.stores = stores;
		this.amount = amount;
		this.unit = unit;
		this.website = website;
	}

	public ProductsInStores(Integer idProductInStores, Beads beads, Stores stores, String amount, String unit,
			Boolean avibility, String website, String name, String image) {
		this.idProductInStores = idProductInStores;
		this.beads = beads;
		this.stores = stores;
		this.amount = amount;
		this.unit = unit;
		this.avibility = avibility;
		this.website = website;
		this.name = name;
		this.image = image;
	}
	
	

	public ProductsInStores(Beads beads, Stores stores, String amount, String unit, Boolean avibility, String website,
			String name, String image) {
		this.beads = beads;
		this.stores = stores;
		this.amount = amount;
		this.unit = unit;
		this.avibility = avibility;
		this.website = website;
		this.name = name;
		this.image = image;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Basic(optional = false)
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
	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
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
	
	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "Image")
	public String getImage(){
		return this.image;
	}
	
	public void setImage(String image){
		this.image = image;
	}

	@Transient
	public String getAmountWithUnit() {
		return this.amount + " " + this.unit;
	}

	public void setAmountWithUnit(String amountWithUnit) {
		String[] split = amountWithUnit.split(" ");
		this.amount = split[0];
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