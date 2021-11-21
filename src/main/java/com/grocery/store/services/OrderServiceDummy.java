package com.grocery.store.services;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.grocery.store.domain.Item;
import com.grocery.store.domain.Order;

@Service
public class OrderServiceDummy implements OrderService{

	private static List<Order> OrderList = new ArrayList();
	private static List<Item> items = new ArrayList();


	static {
		Item item1 = new Item( 1,"Apple",0.60,2, "1");
		Item item2 = new Item( 2,"Orange",0.60,3, "1");
		Item item3 = new Item( 1,"Apple",0.60, 4,"2");
		Item item4 = new Item( 2,"Orange",0.60,5, "2");			
		Order order1 = new Order(1, "Tom", "Shehang",10.0, new ArrayList(Arrays.asList(item1,item2)));
		Order order2 = new Order(12, "Sam", "Sam",10.0, new ArrayList(Arrays.asList(item3,item4)));

		//Order order1 = new Order(1, "Tom", "Shehang",10.0, item1);
		//Order order2 = new Order(12, "Sam", "Sam",10.0, item4);
		OrderList.add(order1);
		OrderList.add(order2);
	}

	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return OrderList;
	}


	private SecureRandom secureRandom = new SecureRandom();
	public Order createIteam(Order order) {
		// TODO Auto-generated method stub
		PricingService prcingService = new PricingService();
		String randomId = new BigInteger(130,secureRandom).toString(32);
		order.setId(Integer.parseInt(randomId));
		order.setLisItem(items);
		order.setPrice(prcingService.getTotalPrice(items));
		return order;
	}

	public Order getOrderId(int orderId) {
		// TODO Auto-generated method stub

		for(Order order:OrderList) {
			if(order.getId() == (orderId)) {
				return order;
			}

		}
		return null;
	}

}
