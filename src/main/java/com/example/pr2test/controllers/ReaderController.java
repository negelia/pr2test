package com.example.pr2test.controllers;

import com.example.pr2test.models.Book;
import com.example.pr2test.models.Reader;
import com.example.pr2test.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ReaderController {

    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping("/reader")
    public String readerMain(Model model){

        Iterable<Reader> readers = readerRepository.findAll();
        model.addAttribute("readers", readers);
        return "/reader/reader-main";
    }

    @GetMapping("/reader/add")
    public String readerAdd(Reader reader, Model model){
        return "/reader/reader-add";
    }

    @PostMapping("/reader/add")
    public String readerAdd(@ModelAttribute("reader") @Valid Reader reader,
                          BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "/reader/reader-add";
        }
        readerRepository.save(reader);
        return "redirect:/reader";
    }

    @GetMapping("/reader/filter")
    public String readerFilter(Model model){return "/reader/reader-filter";}

    @PostMapping("/reader/filter/result")
    public String readerResult(@RequestParam String surname, Model model)
    {
        List<Reader> result = readerRepository.findBySurnameContains(surname);
        model.addAttribute("result", result);
        return "/reader/reader-filter";
    }

    @GetMapping("/reader/{id}")
    public String readerDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Reader> reader = readerRepository.findById(id);
        ArrayList<Reader> res = new ArrayList<>();
        reader.ifPresent(res::add);
        model.addAttribute("reader", res);
        if(!readerRepository.existsById(id))
        {
            return "redirect:/reader";
        }
        return "/reader/reader-details";
    }
    @GetMapping("/reader/{id}/edit")
    public String readerEdit(@PathVariable("id")long id,
                           Model model)
    {
        Reader res = readerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("reader",res);

        return "/reader/reader-edit";
    }

    @PostMapping("/reader/{id}/edit")
    public String readerUpdate(@ModelAttribute("reader") @Valid Reader reader,
                             BindingResult bindingResult,
                             @PathVariable("id") Long id) {
        reader.setId(id);
        if (bindingResult.hasErrors())
            return "/reader/reader-edit";

        readerRepository.save(reader);
        return "redirect:/reader";
    }
    @PostMapping("/reader/{id}/remove")
    public String readerDelete(@PathVariable("id") long id, Model model){
        Reader reader = readerRepository.findById(id).orElseThrow();
        readerRepository.delete(reader);
        return "redirect:/reader";
    }


}