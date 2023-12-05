package com.org.ecommerce.modal;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name= "purchases")   
public class Purchase { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "userId")
	private long userId;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "total")
	private BigDecimal total;

	
	public long getID() {return this.id; }
	public long getUserId() { return this.userId;}
	public BigDecimal getTotal() { return this.total;}
	public Date getDate() { return this.date;}
	
	public void setID(long id) { this.id = id;}
	public void setUserId(long value) { this.userId = value;}
	public void setTotal(BigDecimal value) { this.total = value;}
	public void setDate(Date date) { this.date = date;}   
}
