package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.book.BookRequestDTO;
import com.acolak.readingisgood.dto.book.BookResponseDTO;
import com.acolak.readingisgood.exception.BookServiceException;
import com.acolak.readingisgood.repository.BookRepository;
import com.acolak.readingisgood.repository.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@Service
@Slf4j
public class BookService {

	private BookRepository bookRepository;


	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Transactional
	public BookResponseDTO addNewBook(BookRequestDTO requestDTO) {

		Optional<Book> bookRecord = bookRepository.findBookByName(requestDTO.getName());

		if(bookRecord.isPresent()) {
			throw new BookServiceException(601, "Book Already Exist!");
		} else {
			Book book = buildBookEntity(requestDTO);
			bookRepository.insert(book);
			log.info("New Book is added: " + book);
			return convertToBookResponseDTO(book);
		}
	}


	public Book buildBookEntity(BookRequestDTO requestDTO) {
		return Book.builder()
				.name(requestDTO.getName())
				.author(requestDTO.getAuthor())
				.price(requestDTO.getPrice())
				.stock(requestDTO.getStock())
				.build();
	}


	@Transactional
	public BookResponseDTO updateBookStock(BookRequestDTO requestDTO) {

		Optional<Book> bookRecord = bookRepository.findBookByName(requestDTO.getName());

		if(bookRecord.isPresent()) {
			Book book = bookRecord.get();
			book.setUpdatedDate(LocalDateTime.now());
			book.setStock(requestDTO.getStock());
			bookRepository.save(book);
			log.info("Book stock updated: " + book);
			return convertToBookResponseDTO(book);

		} else {
			throw new BookServiceException(602, "Book Not Found!");
		}
	}

	public BookResponseDTO convertToBookResponseDTO(Book book){
		return BookResponseDTO.builder()
				.name(book.getName())
				.author(book.getAuthor())
				.price(book.getPrice())
				.stock(book.getStock())
				.bookId(book.getBookId())
				.build();
	}

	public Book getBookById(String bookId) {

		Optional<Book> bookRecord = bookRepository.findById(bookId);

		if(bookRecord.isPresent()) {
			Book book = bookRecord.get();
			log.info("getBookById is Successfully: " + book);
			return book;
		} else {
			throw new BookServiceException(602, "Book Not Found!");
		}
	}
}
