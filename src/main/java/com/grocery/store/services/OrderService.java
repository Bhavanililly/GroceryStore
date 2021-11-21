package com.grocery.store.services;

import java.util.List;

import com.grocery.store.domain.Order;


public interface OrderService {
	
	
	//get all order
	public List<Order> getAllOrders();
	
	//Save order
	public Order createIteam(Order order);
	
	//get single order id	
	public Order getOrderId(int orderId);

	
}
