package com.grocery.store.domain;

import javax.persistence.*;


@Entity
@Table(name ="Items")
public final class Item {
	
	private int id;
	
	private String itemName;
	
	private double price;
	
	private String orderId;
	
	private int quantity;
	
	private Order order;
	private double discount;
	
	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public Item(int id, String itemName, double price,int quantity, String orderId) {
		this.id=id;
		this.itemName= itemName;
		this.price= price;
		this.orderId = orderId;
		
	}
	
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="item_id")
	private int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
		

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	

	@Column(name = "Name")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Column(name = "PRICE")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + id + ", itemName=" + itemName + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}
	
	

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name ="ORDER_ID")
	public String getOrder() {
		if (orderId == null) {
			this.orderId = orderId;
			
		}
		return orderId;
	}
	
	public void setOrder(String orderId) {
		this.orderId = orderId;
	}

}
