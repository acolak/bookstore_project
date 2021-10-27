package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.book.BookRequestDTO;
import com.acolak.readingisgood.repository.BookRepository;
import com.acolak.readingisgood.repository.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@Spy
	@InjectMocks
	private BookService bookService;

	@Spy
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
		Book book = new Book();
		book.setName("Book1");
		when(bookRepository.findBookByName(anyString())).thenReturn(Optional.of(book));
		bookService.addNewBook(bookRequestDTO);
		verify(bookRepository).insert(any(Book.class));
	}

}
