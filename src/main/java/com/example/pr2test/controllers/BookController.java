package com.example.pr2test.controllers;

import com.example.pr2test.models.Book;
import com.example.pr2test.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String bookAdd(Book book, Model model){
        return "/book/book-add";
    }

    @PostMapping("/book/add")
    public String bookAdd(@ModelAttribute("book") @Valid Book book,
                          BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "/book/book-add";
        }
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

    @GetMapping("/book/{id}")
    public String bookDetails(@PathVariable(value = "id") long iDBook, Model model)
    {
        Optional<Book> book = bookRepository.findById(iDBook);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("book", res);
        if(!bookRepository.existsById(iDBook))
        {
            return "redirect:/book";
        }
        return "/book/book-details";
    }
    @GetMapping("/book/{id}/edit")
    public String bookEdit(@PathVariable("id")long iDBook,
                           Model model)
    {
        Book res = bookRepository.findById(iDBook).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + iDBook));
        model.addAttribute("book",res);

        return "/book/book-edit";
    }

    @PostMapping("/book/{id}/edit")
    public String bookUpdate(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") Long iDBook) {
        book.setiDBook(iDBook);
        if (bindingResult.hasErrors())
            return "/book/book-edit";

        bookRepository.save(book);
        return "redirect:/book";
    }

    @PostMapping("/book/{id}/remove")
    public String bookDelete(@PathVariable("id") long iDBook, Model model){
        Book book = bookRepository.findById(iDBook).orElseThrow();
        bookRepository.delete(book);
        return "redirect:/book";
    }

}
