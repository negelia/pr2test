package com.example.pr2test.controllers;

import com.example.pr2test.models.Book;
import com.example.pr2test.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book")
    public String bookMain(Model model){
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "/book/book-main";
    }

    @GetMapping("/book/add")
    public String bookAdd(Model model){
        return "/book/book-add";
    }

    @PostMapping("/book/add")
    public String bookAdd(@RequestParam String title,
                          @RequestParam String author,
                          @RequestParam String date,
                          @RequestParam Boolean ordered,
                          @RequestParam int pages,
                          Model model){
        Book book = new Book(title, author, date, ordered,  pages);
        bookRepository.save(book);
        return "redirect:/book";
    }

    @GetMapping("/book/filter")
    public String bookFilter(Model model){return "/book/book-filter";}

    @PostMapping("/book/filter/result")
    public String bookResult(@RequestParam String title, Model model)
    {
        List<Book> result = bookRepository.findByTitleContains(title);
        model.addAttribute("result", result);
        return "/book/book-filter";
    }

}
