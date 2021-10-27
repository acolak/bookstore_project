package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.order.OrderRequestDTO;
import com.acolak.readingisgood.exception.OrderServiceException;
import com.acolak.readingisgood.repository.OrderRepository;
import com.acolak.readingisgood.repository.entity.Book;
import com.acolak.readingisgood.repository.entity.Customer;
import com.acolak.readingisgood.repository.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@Spy
	@InjectMocks
	private OrderService orderService;
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private CustomerService customerService;
	@Mock
	private BookService bookService;

	@Test
	public void getAllOrdersByCustomerIdUnitTest() {

		when(orderRepository.findAllByCustomerId(anyString(), any(Pageable.class))).thenReturn(any(Page.class));

		List<Order> orders = orderService.getAllOrdersByCustomerId(anyString(), anyInt(), anyInt(), anyString());
		assertNotNull(orders);

	}

	@Test
	public void createOrderUnitTest() {

		lenient().when(customerService.getCustomerById(anyString())).thenReturn(any(Customer.class));

		Book book = new Book();
		book.setName("Book1");
		book.setBookId("123");
		book.setStock(3L);
		lenient().when(bookService.getBookById(book.getBookId())).thenReturn(book);

	//	doNothing().doThrow(new OrderServiceException()).when(orderService).checkBooksStock(anyLong(), any(Book.class));

		OrderRequestDTO requestDTO = new OrderRequestDTO();
		requestDTO.setAmount(1L);
		requestDTO.setBookId("123");
		orderService.createOrder(requestDTO);

		verify(orderRepository).save(any(Order.class));
	}

	@Test
	public void checkBooksStockUnitTest() {
		Book book = new Book();
		book.setName("Book1");
		book.setBookId("123");
		book.setStock(3L);
		lenient().doNothing().when(orderService).checkBooksStock(3L, book);
	}


	@Test
	public void checkBooksStockWhenOutOfStockTest() {
		Book book = new Book();
		book.setName("Book1");
		book.setBookId("123");
		book.setStock(0L);

		OrderServiceException thrown = assertThrows(
				OrderServiceException.class,
				() -> orderService.checkBooksStock(3L, book));

		assertTrue(thrown.getErrorMessage().equalsIgnoreCase("Stock Not Sufficient!"));

	}


	@Test
	public void checkBooksStockWheInvalidOrderStockTest() {
		Book book = new Book();
		book.setName("Book1");
		book.setBookId("123");
		book.setStock(0L);

		OrderServiceException thrown = assertThrows(
				OrderServiceException.class,
				() -> orderService.checkBooksStock(-1L, book));

		assertTrue(thrown.getErrorMessage().equalsIgnoreCase("You can order at least 1 Book!"));
	}

	@Test
	public void getOrderByIdUnitTestWhenNull() {

		lenient().when(orderRepository.findById(anyString())).thenReturn(Optional.empty());

		OrderServiceException thrown = assertThrows(
				OrderServiceException.class,
				() -> orderService.getOrderById(anyString()));

		assertTrue(thrown.getErrorMessage().equalsIgnoreCase("Order Not Found!"));

	}

	@Test
	public void getOrderByIdUnitTest() {
		Order order = new Order();
		order.setOrderId("123");
		when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));

		assertNotNull(orderService.getOrderById(order.getOrderId()));
	}

	@Test
	public void getOrdersByDateInterval() {

		Order order = new Order();
		order.setOrderId("123");
		List<Order> orderList = new ArrayList<>();
		orderList.add(order);
		lenient().when(orderRepository.findAllByCreateDateBetween(LocalDateTime.now(), LocalDateTime.now())).thenReturn(Optional.of(orderList));

		assertNotNull(orderService.getOrdersByDateInterval(LocalDateTime.now(), LocalDateTime.now()));

	}



}
