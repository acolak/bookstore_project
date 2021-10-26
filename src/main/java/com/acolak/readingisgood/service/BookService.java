package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.book.BookRequestDTO;
import com.acolak.readingisgood.dto.book.BookResponseDTO;
import com.acolak.readingisgood.exception.BookAlreadyExistException;
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
			throw new BookAlreadyExistException(601);
		} else {
			Book book = buildBookEntity(requestDTO);
			bookRepository.insert(book);
			log.info("New Book is added: " + book);
		}

		BookResponseDTO responseDTO = new BookResponseDTO();
		return responseDTO;
	}


	public Book buildBookEntity(BookRequestDTO requestDTO) {
		return Book.builder()
				.name(requestDTO.getName())
				.author(requestDTO.getAuthor())
				.price(requestDTO.getPrice())
				.stock(requestDTO.getStock())
				.build();
	}


	public BookResponseDTO updateBookStock(BookRequestDTO requestDTO) {

		Optional<Book> bookRecord = bookRepository.findBookByName(requestDTO.getName());

		if(bookRecord.isPresent()) {
			Book book = bookRecord.get();
			book.setUpdatedDate(LocalDateTime.now());
			book.setStock(requestDTO.getStock());
			bookRepository.save(book);
			log.info("Book stock updated: " + book);
		} else {
			throw new BookAlreadyExistException(601);

		}

		return null;
	}

}
