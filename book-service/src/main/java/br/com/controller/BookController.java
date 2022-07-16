package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.Book;
import br.com.proxy.CambioProxy;
import br.com.repository.BookRepository;
import br.com.response.Cambio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

	@Autowired
	private Environment environment;

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired	
	private CambioProxy proxy;

	@GetMapping(value = "/{id}/{currency}")
	@Operation(summary = "Find a specific book by your ID")
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

		Book book = bookRepository.getById(id);
		if (book == null)
			throw new RuntimeException("Book not found");
		
		
		Cambio cambio = proxy.getCambio(book.getPrice(), "USD", currency);

		String port = environment.getProperty("local.server.port");
		book.setEnviroment(port);
		book.setCurrency(cambio.getConvertedValue().toString());

		return book;
	}
}
