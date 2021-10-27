package com.acolak.readingisgood.controller;

import com.acolak.readingisgood.constant.ControllerConstants;
import com.acolak.readingisgood.dto.book.BookRequestDTO;
import com.acolak.readingisgood.dto.book.BookResponseDTO;
import com.acolak.readingisgood.repository.entity.Book;
import com.acolak.readingisgood.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@RestController
@RequestMapping(ControllerConstants.BOOK_URL)
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> addBook(@RequestBody BookRequestDTO requestDTO) {
		BookResponseDTO responseDTO = bookService.addNewBook(requestDTO);
		responseDTO.setResultMessage("New Book Successfully Added!");
		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateBook(@RequestBody BookRequestDTO requestDTO) {
		Book book = bookService.updateBookStock(requestDTO);
		BookResponseDTO responseDTO = bookService.convertToBookResponseDTO(book);
		responseDTO.setResultMessage("Book Information Updated!");
		return ResponseEntity.ok(responseDTO);
	}

}
