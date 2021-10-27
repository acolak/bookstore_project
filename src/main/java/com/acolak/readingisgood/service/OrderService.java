package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.order.OrderDTO;
import com.acolak.readingisgood.dto.order.OrderRequestDTO;
import com.acolak.readingisgood.dto.order.OrderResponseDTO;
import com.acolak.readingisgood.exception.OrderServiceException;
import com.acolak.readingisgood.repository.OrderRepository;
import com.acolak.readingisgood.repository.entity.Book;
import com.acolak.readingisgood.repository.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@Service
@Slf4j
public class OrderService {

	private OrderRepository orderRepository;
	private BookService bookService;
	private CustomerService customerService;

	public OrderService(OrderRepository orderRepository, BookService bookService, CustomerService customerService) {
		this.orderRepository = orderRepository;
		this.bookService = bookService;
		this.customerService = customerService;
	}

	public List<Order> getAllOrdersByCustomerId(String customerId, Integer pageNo, Integer pageSize, String sortBy) {

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Order> orderWithPaged = orderRepository.findAllByCustomerId(customerId, paging);

		if(orderWithPaged.hasContent()){
			return orderWithPaged.getContent();
		}

		return new ArrayList<>();
	}

	@Transactional
	public OrderDTO createOrder(OrderRequestDTO requestDTO) {

		//to check customer is valid!
		customerService.getCustomerById(requestDTO.getCustomerId());

		Book orderedBook = bookService.getBookById(requestDTO.getBookId());
		checkBooksStock(requestDTO.getAmount(), orderedBook);

		Order order = buildOrderEntity(requestDTO, orderedBook);
		orderRepository.insert(order);
		bookService.updateBookStockByValue(orderedBook, (orderedBook.getStock() - requestDTO.getAmount()));

		log.info("New Order has completed: " + order);

		return convertToOrderDTO(order);
	}

	public void checkBooksStock(Long numberOfBookOrder, Book book) {
		if(numberOfBookOrder < 1) {
			throw new OrderServiceException(605, "You can order at least 1 Book!");
		}
		if(numberOfBookOrder > book.getStock()){
			throw new OrderServiceException(604, "Stock Not Sufficient!");
		}
	}

	public Order buildOrderEntity(OrderRequestDTO requestDTO, Book book) {
		return Order.builder()
				.amount(requestDTO.getAmount())
				.bookId(requestDTO.getBookId())
				.customerId(requestDTO.getCustomerId())
				.totalPrice(requestDTO.getAmount() * book.getPrice())
				.build();
	}

	public OrderDTO convertToOrderDTO(Order order){
		return OrderDTO.builder()
				.totalPrice(order.getTotalPrice())
				.amount(order.getAmount())
				.bookId(order.getBookId())
				.customerId(order.getCustomerId())
				.orderId(order.getOrderId())
				.created(order.getCreateDate())
				.build();
	}


	public Order getOrderById(String orderId) {
		Optional<Order> orderRecord = orderRepository.findById(orderId);

		if(orderRecord.isPresent()) {
			Order order = orderRecord.get();
			log.info("getOrderById is Successfully: " + order);
			return order;
		} else {
			throw new OrderServiceException(606, "Order Not Found!");
		}

	}

	public List<Order> getOrdersByDateInterval(LocalDateTime startDate, LocalDateTime endDate) {
		Optional<List<Order>> ordersRecord = orderRepository.findAllByCreateDateBetween(startDate, endDate);

		if(ordersRecord.isPresent()){
			log.info("Orders found in between " + startDate + "-" + endDate + ": " + ordersRecord.get());
			return ordersRecord.get();
		} else {
			throw new OrderServiceException(609, "No orders has found between " + startDate + "-" + endDate + " dates");
		}
	}

	public OrderResponseDTO convertOrdersToResponseDTO(List<Order> ordersByInterval) {
		OrderResponseDTO responseDTO = new OrderResponseDTO();
		responseDTO.setOrderList(ordersByInterval.stream().map(order -> convertToOrderDTO(order)).collect(Collectors.toList()));
		return responseDTO;
	}
}
