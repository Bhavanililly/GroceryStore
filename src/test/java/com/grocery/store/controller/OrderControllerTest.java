package com.grocery.store.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grocery.store.config.ServiceConfig;
import com.grocery.store.domain.Item;
import com.grocery.store.domain.Order;
import com.grocery.store.services.OrderServiceDummy;

@WebMvcTest(OrderController.class)
@ActiveProfiles("test")
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ServiceConfig serviceconfig;

	@MockBean
	OrderServiceDummy orderService;

	@Autowired
	ObjectMapper objectMapper;


	@BeforeEach
	public void setUp() {


	}


	@Test public void get_allOrder_returnOkWithOfOrder() throws Exception{
		
		List<Order> listOrder = new ArrayList();
		Item item1 = new Item( 1,"Apple",0.60,2, "1");
		Item item2 = new Item(2,"Orange",0.60,3, "1"); 
		Item item3 = new Item( 1,"Apple",0.60, 4,"2");
		Item item4 = new Item( 2,"Orange",0.60,5, "2"); 
		Order order1 = new Order(1,"Tom", "Shehang",10.0, new ArrayList(Arrays.asList(item1,item2))); 
		Order order2 = new Order(2, "Sam", "Sam",10.0, new ArrayList(Arrays.asList(item3,item4)));

		listOrder.add(order1); listOrder.add(order2);
		when(orderService.getAllOrders()).thenReturn(listOrder);

		mockMvc.perform(MockMvcRequestBuilders.get(
				"http://localhost:8080/api/orderId")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("[0].id", is(1))) .andExpect(jsonPath("[0].firstName",
				is("Tom"))) .andExpect(jsonPath("[0].lastName", is("Shehang")))
		.andExpect(jsonPath("[1].id", is(2))) .andExpect(jsonPath("[1].firstName",
				is("Sam")));

	}


	@Test
	public void post_createNewOrderWith201() throws Exception{
		Item item4 = new Item( 1,"Apple",0.60, 4,"2");
		Item item5 = new Item( 2,"Orange",0.60,5, "2");
		Order order3 = new Order(1, "Tom", "Tom",10.0, new ArrayList(Arrays.asList(item4,item5)));
		Mockito.when(orderService.createIteam(Mockito.any(Order.class))).thenReturn(order3);
		
		// Build post request with Order object payload
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/orderId/create/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(this.objectMapper.writeValueAsBytes(order3));

		mockMvc.perform(builder).andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(1)))
		.andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(order3)));
	}

}






