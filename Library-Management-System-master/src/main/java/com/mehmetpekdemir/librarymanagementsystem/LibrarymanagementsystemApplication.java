package com.mehmetpekdemir.librarymanagementsystem;

import com.mehmetpekdemir.librarymanagementsystem.entity.Book;
import com.mehmetpekdemir.librarymanagementsystem.entity.Category;
import com.mehmetpekdemir.librarymanagementsystem.entity.Member;
import com.mehmetpekdemir.librarymanagementsystem.entity.Publisher;
import com.mehmetpekdemir.librarymanagementsystem.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LibrarymanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarymanagementsystemApplication.class, args);
	}
	@Bean
	public CommandLineRunner initialCreate(BookService bookService) {
		return (args) -> {

			Book book = new Book("123456", "java", "130256", "xcbxcb");
			Member member=new Member("ali","23242421647");

			Category category = new Category("dcvds");
			Publisher publisher = new Publisher("dsvsdsb");

			book.addmembers(member);
			book.addCategories(category);
			book.addPublishers(publisher);

			bookService.createBook(book);

			Book book1 = new Book("Test isbn1", "Test name1", "Test serial name1", "Test description1");
			Member member1=new Member("dec","132424247");
			Category category1 = new Category("Test category name1");
			Publisher publisher1 = new Publisher("Test publisher name1");

			book1.addmembers(member1);
			book1.addCategories(category1);
			book1.addPublishers(publisher1);

			bookService.createBook(book1);

			Book book2 = new Book("Test isbn2", "Test name2", "Test serial name2", "Test description2");

			Category category2 = new Category("Test category name2");
			Publisher publisher2 = new Publisher("Test publisher name2");
			Member member2=new Member("ali1","13242421647");
			book2.addmembers(member2);
			book2.addCategories(category2);
			book2.addPublishers(publisher2);

			bookService.createBook(book2);

		};
	}

}
