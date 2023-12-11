package com.org.ecommerce.modal;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name= "purchaseitem")   
public class PurchaseItem { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "purchaseId")
	private long purchaseId;
	
	@Column(name = "productId")
	private long productId;

	@Column(name = "userId")
	private long userId;

	@Column(name = "rate")
	private BigDecimal rate;

	@Column(name = "qty")
	private int qty;

	@Column(name = "price")
	private BigDecimal price;

	
	public long getID() {return this.id; }
	public long getPurchaseId() { return this.purchaseId;}
	public long getProductId() { return this.productId;}
	public long getUserId() { return this.userId;}
	public BigDecimal getRate() { return this.rate;}	
	public int getQty() { return this.qty;}	
	public BigDecimal getPrice() { return this.price;}
	
	public void setID(long id) { this.id = id;}
	public void setPurchaseId(long value) { this.purchaseId = value;}
	public void setProductId(long value) { this.productId = value;}
	public void setUserId(long value) { this.userId = value;}
	public void setRate(BigDecimal value) { this.rate = value;}
	public void setQty(int value) { this.qty= value;}
	public void setPrice(BigDecimal value) { this.price= value;}
	
	

}


