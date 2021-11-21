package com.grocery.store.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.store.domain.Order;
import com.grocery.store.services.OrderServiceDummy;

@RestController
@RequestMapping(value="api/orderId")
public class OrderController {

	private List<Order> order = new ArrayList();
	private OrderServiceDummy orderService;
	public OrderController(OrderServiceDummy orderService) {
		this.orderService = orderService;

	}

	@GetMapping(path ="{id}")
	@ResponseStatus(value =HttpStatus.OK)
	public ResponseEntity<Order> getOrder (@PathVariable (value ="id") int id){
		Order response = orderService.getOrderId(id);
		if(response !=null ) {
			return new ResponseEntity(response, HttpStatus.OK);

		}
		else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity <List <Order>> getAllOrders(){


		List<Order> allOrder = orderService.getAllOrders();

		if(allOrder.size() <=0 && allOrder == null) {
			return ResponseEntity.noContent().build();

		}else {
			return new ResponseEntity(allOrder, HttpStatus.OK);		

		}


	}

	@PostMapping("/create/{order}")
	@ResponseStatus(value = HttpStatus.OK)	
	public  ResponseEntity <Order> postBody(@RequestBody Order order){
		
		if(order.getPrice() == 0) {
			return new ResponseEntity("Order not created", HttpStatus.BAD_REQUEST);
			
		}
		Order orderToSave = orderService.createIteam (order);
		if(orderToSave == null) {
			return new ResponseEntity("Order not created", HttpStatus.BAD_REQUEST);
		}
		else {
			return ResponseEntity.created(URI.create(String.format("/orderId/%s", order.getId()))).body(orderToSave);
		}

	}


}
