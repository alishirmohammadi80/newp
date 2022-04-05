package com.mehmetpekdemir.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mehmetpekdemir.librarymanagementsystem.entity.Book;
import com.mehmetpekdemir.librarymanagementsystem.service.MemberService;
import com.mehmetpekdemir.librarymanagementsystem.service.BookService;
import com.mehmetpekdemir.librarymanagementsystem.service.CategoryService;
import com.mehmetpekdemir.librarymanagementsystem.service.PublisherService;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
@Autowired
BookService bookService;
@Autowired
MemberService memberService;
@Autowired
CategoryService categoryService;
@Autowired
PublisherService publisherService;

	public BookController(BookService bookService, MemberService memberService, CategoryService categoryService,
						  PublisherService publisherService) {
		this.bookService = bookService;
		this.memberService = memberService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
	}

	@RequestMapping(value = "/books",method = RequestMethod.GET)
	public String findAllBooks(Model model) {

		 List<Book> books = bookService.findAllBooks();

		model.addAttribute("books", books);
		return "list-books";
	}

	@RequestMapping(value = "/searchBook",method = RequestMethod.GET)
	public String searchBook(@Param("keyword") String keyword, Model model) {

		 List<Book> books = bookService.searchBooks(keyword);

		model.addAttribute("books", books);
		model.addAttribute("keyword", keyword);
		return "list-books";
	}

	@RequestMapping(value = "/book/{id}",method = RequestMethod.GET)
	public String findBookById(@PathVariable("id") Long id, Model model) {

		 Book book = bookService.findBookById(id);

		model.addAttribute("book", book);
		return "list-book";
	}

	@GetMapping("/add")
	public String showCreateForm(Book book, Model model) {
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("members", memberService.findAllMembers());
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "add-book";
	}

	@RequestMapping(value = "/add-book",method = RequestMethod.POST)
	public String createBook(Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-book";
		}

		bookService.createBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		 Book book = bookService.findBookById(id);

		model.addAttribute("book", book);
		return "update-book";
	}

	@RequestMapping(value = "/update-book/{id}")
	public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			book.setId(id);
			return "update-book";
		}

		bookService.updateBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

	@RequestMapping(value = "/remove-book/{id}",method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookService.deleteBook(id);

		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

}
