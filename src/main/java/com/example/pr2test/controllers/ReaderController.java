package com.example.pr2test.controllers;

import com.example.pr2test.models.Book;
import com.example.pr2test.models.Reader;
import com.example.pr2test.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String readerAdd(Model model){
        return "/reader/reader-add";
    }

    @PostMapping("/reader/add")
    public String readerPostAdd(@RequestParam String surname,
                              @RequestParam String name,
                              @RequestParam Boolean middle,
                                @RequestParam int age,
                                @RequestParam int groupName,
                                Model model){
        Reader reader = new Reader(surname,name, middle, age, groupName);
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
        if(!readerRepository.existsById(id)){
            return "redirect:/reader";
        }
        Optional<Reader> post = readerRepository.findById(id);
        ArrayList<Reader> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("reader",res);
        return "/reader/reader-edit";
    }
    @PostMapping("/reader/{id}/edit")
    public String readerUpdate(@PathVariable("id")long id,
                             @RequestParam String surname,
                             @RequestParam String name,
                             @RequestParam Boolean middle,
                             @RequestParam int age,
                             @RequestParam int groupName,
                             Model model)
    {
        Reader reader = readerRepository.findById(id).orElseThrow();
        reader.setSurname(surname);
        reader.setName(name);
        reader.setMiddle(middle);
        reader.setAge(age);
        reader.setGroupName(groupName);
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