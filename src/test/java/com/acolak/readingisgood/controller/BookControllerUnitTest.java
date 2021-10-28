package com.acolak.readingisgood.controller;

import com.acolak.readingisgood.dto.book.BookRequestDTO;
import com.acolak.readingisgood.dto.book.BookResponseDTO;
import com.acolak.readingisgood.repository.entity.Book;
import com.acolak.readingisgood.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * @author AhmetColak date 28.10.2021 Copyright © 2021.
 **/

@ExtendWith(MockitoExtension.class)
public class BookControllerUnitTest {

	@Spy
	@InjectMocks
	private BookController bookController;

	@Mock
	private BookService bookService;

	@Test
	public void addBookUnitTest() {
		BookResponseDTO bookResponseDTO = new BookResponseDTO();
		bookResponseDTO.setResultMessage("success");
		when(bookService.addNewBook(any(BookRequestDTO.class))).thenReturn(bookResponseDTO);

		BookRequestDTO bookRequestDTO = new BookRequestDTO();
		ResponseEntity responseEntity = bookController.addBook(bookRequestDTO);
		assertNotNull(responseEntity);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void updateBookUnitTest() {
		Book book = new Book();
		book.setBookId("1231");
		when(bookService.updateBookStock(any(BookRequestDTO.class))).thenReturn(book);

		BookResponseDTO responseDTO = new BookResponseDTO();
		when(bookService.convertToBookResponseDTO(book)).thenReturn(responseDTO);

		BookRequestDTO bookRequestDTO = new BookRequestDTO();
		ResponseEntity responseEntity = bookController.updateBook(bookRequestDTO);
		assertNotNull(responseEntity);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}


}
