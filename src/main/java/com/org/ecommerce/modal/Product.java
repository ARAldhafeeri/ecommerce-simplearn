package com.org.ecommerce.modal;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name= "product")   
public class Product { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "dateAdded")
	private String dateAdded;  

	@Column(name = "categoryId")
	private long categoryId;  
	

	public long getID() {return this.id; }  
	public String getName() { return this.name;} 
	public BigDecimal getPrice() { return this.price;} 
	public long getCategoryId() { return this.categoryId;}
	public String getDateAdded() { return this.dateAdded;}

	
	public void setID(long id) { this.id = id;}
	public void setName(String value) { this.name = value;}
	public void setPrice(BigDecimal value) { this.price = value;}
	public void setCategoryId(long value) { this.categoryId = value;}
	public void setDateAdded(String date) { this.dateAdded = date;}
}
