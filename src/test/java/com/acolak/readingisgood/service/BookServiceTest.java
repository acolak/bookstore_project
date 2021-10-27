package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.book.BookRequestDTO;
import com.acolak.readingisgood.exception.BookServiceException;
import com.acolak.readingisgood.repository.BookRepository;
import com.acolak.readingisgood.repository.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@Spy
	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;


	@Test
	public void addNewBookUnitTest() {

		BookRequestDTO bookRequestDTO = new BookRequestDTO();
		lenient().when(bookRepository.findBookByName(anyString())).thenReturn(Optional.empty());
		bookService.addNewBook(bookRequestDTO);
		verify(bookRepository).insert(any(Book.class));
	}

	@Test
	public void addNewBookWhenBookAlreadyExistUnitTest() {

		BookRequestDTO bookRequestDTO = new BookRequestDTO();
		bookRequestDTO.setName("Book1");

		Book book = new Book();
		book.setName("Book1");

		lenient().when(bookRepository.findBookByName(anyString())).thenReturn(Optional.of(book));

		BookServiceException thrown = assertThrows(
				BookServiceException.class,
				() -> bookService.addNewBook(bookRequestDTO));

		assertTrue(thrown.getErrorMessage().equalsIgnoreCase("Book Already Exist!"));
	}

	@Test
	public void updateBookStockUnitTest() {
		Book book = new Book();
		book.setName("Book1");
		lenient().when(bookRepository.findBookByName(anyString())).thenReturn(Optional.of(book));

		BookRequestDTO bookRequestDTO = new BookRequestDTO();
		bookRequestDTO.setName("Book1");
		bookRequestDTO.setStock(3L);
		assertNotNull(bookService.updateBookStock(bookRequestDTO));

		verify(bookRepository).save(book);
	}

	@Test
	public void updateBookStockUnitTestWhenNotFoundBook() {
		lenient().when(bookRepository.findBookByName(anyString())).thenReturn(Optional.empty());

		BookRequestDTO bookRequestDTO = new BookRequestDTO();
		bookRequestDTO.setName("Book1");
		bookRequestDTO.setStock(3L);

		BookServiceException thrown = assertThrows(
				BookServiceException.class,
				() -> bookService.updateBookStock(bookRequestDTO));

		assertTrue(thrown.getErrorMessage().equalsIgnoreCase("Book Not Found!"));

	}

	@Test
	public void updateBookStockByValueUnitTest() {
		Book book = new Book();
		book.setName("Book1");
		book.setStock(5L);
		Book returnBook = bookService.updateBookStockByValue(book, 2L);
		assertTrue(returnBook.getStock().equals(2L));
		verify(bookRepository).save(book);
	}

	@Test
	public void getBookByIdUnitTestWhenNotFoundBook() {
		lenient().when(bookRepository.findBookByName(anyString())).thenReturn(Optional.empty());

		BookServiceException thrown = assertThrows(
				BookServiceException.class,
				() -> bookService.getBookById(anyString()));

		assertTrue(thrown.getErrorMessage().equalsIgnoreCase("Book Not Found!"));

	}


	@Test
	public void getBookByIdUnitTest() {
		Book book = new Book();
		book.setName("Book1");
	    when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));

		assertNotNull(bookService.getBookById(book.getName()));
	}

}
