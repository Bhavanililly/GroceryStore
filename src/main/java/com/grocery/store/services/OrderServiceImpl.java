package com.grocery.store.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grocery.store.domain.Item;
import com.grocery.store.domain.Order;
import com.grocery.store.repository.OrderRepository;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	LocalSessionFactoryBean sessionFactory;

	@Autowired
	private OrderRepository orderRepository;

	@PersistenceContext
	private EntityManager entityManager;
	/*
	 * public OrderServiceImpl(OrderRepository orderRepository) {
	 * this.orderRepository = orderRepository; }
	 */

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		List<Order> listOrder = orderRepository.findAll();
		return listOrder;
	}
//This need to be enabled for the JPA Repository
	@Transactional
	@Override
	public Order createIteam(Order order) {

		Order saveOrder = orderRepository.save(order);

		//return saveOrder;
		return saveOrder;
		

	}

	@Override
	public Order getOrderId(int orderId) {
		// TODO Auto-generated method stub

		Order orderById = orderRepository.getById((long) orderId);
		return orderById;
	}

}
