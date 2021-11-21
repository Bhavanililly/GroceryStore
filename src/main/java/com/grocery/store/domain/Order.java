package com.grocery.store.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name ="ORDER_ID")
public class Order {
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private double price;

	
	private List<Item> lisItem;
	
	public List<Item> getLisItem() {
		return lisItem;
	}
	public void setLisItem(List<Item> lisItem) {
		this.lisItem = lisItem;
	}
	public Order(int id, String firstName, String lastName, double price, List<Item> lisItem) {
		this.id = id;
		this.firstName= firstName;
		this.lastName = lastName;
		this.price=price;
		this.lisItem = lisItem;
		
	}
	public Order() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name ="LAST_NAME")
	public String getLastName() {
		
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="TOTALPRICE")
	public double getPrice() {
		return price;
	}

	
	public void setPrice(double price) {
		this.price = price;
	}

	

}
