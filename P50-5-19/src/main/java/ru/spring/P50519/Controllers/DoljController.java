package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.Dolj;
import ru.spring.P50519.Models.Employee;
import ru.spring.P50519.Repository.DoljRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Dolj")
@PreAuthorize("hasAnyAuthority('CHAR', 'ADMIN')")
public class DoljController {
    @Autowired
    DoljRepository doljRepository;
    @GetMapping("/Index")
    public String Dolj(Model model)
    {

        Iterable<Dolj> listDolj= doljRepository.findAll();
        model.addAttribute("doljs",listDolj);
        return "/Dolj/Index";
    }
    @GetMapping("/IndexAddDolj")
    public String DoljAddView(Model model,Dolj dolj)
    {

        return "/Dolj/IndexAddDolj";
    }
    @PostMapping("/IndexAddDolj")
    public String DoljAdd(
            @Valid Dolj dolj,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "/Dolj/IndexAddDolj";
        }
        //Dolj dolj=new Dolj(name,tax,description,salary,max_bonus);
        doljRepository.save(dolj);
        return "redirect:/Dolj/Index";
    }
    @GetMapping("/Filter")
    public String DoljFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Dolj> doljs=doljRepository.findByName(name);
        model.addAttribute("doljs_filtered",doljs);
        return "/Dolj/Filter";
    }
    @GetMapping("/FilterCategorized")
    public String DoljFilterCategorized(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Dolj> doljs=doljRepository.findByNameContaining(name);
        model.addAttribute("doljs_filtered",doljs);
        return "/Dolj/Filter";
    }
    @GetMapping("/DoljDetail/{id}")
    public String DoljDetails(@PathVariable Long id,
                             Model model)
    {
        Optional<Dolj> dolj = doljRepository.findById(id);
        ArrayList<Dolj> res = new ArrayList<Dolj>();
        dolj.ifPresent(res::add);
        model.addAttribute("doljs",res);
        return "/Dolj/DoljDetail";
    }
    @GetMapping ("/DoljDelete/{id}")
    public String DoljDelete(@PathVariable Long id)
    {
        doljRepository.delete(doljRepository.findById(id).orElseThrow());
        return "redirect:/Dolj/Index";
    }
    @GetMapping ("/DoljEdit/{id}")
    public String DoljEditView(@PathVariable Long id,
                              Model model,Dolj dolj)
    {
        dolj= doljRepository.findById(id).orElseThrow();
        model.addAttribute("dolj",dolj);
        return "/Dolj/DoljEdit";
    }
    @PostMapping ("/DoljEdit/{id}")
    public String DoljEdit(
            @Valid Dolj dolj,
            BindingResult bindingResult,
                           Model model)
    {
        model.addAttribute("dolj",dolj);
        if (bindingResult.hasErrors())
        {
            return "/Dolj/DoljEdit";
        }
        doljRepository.save(dolj);
        return "redirect:/Dolj/Index";
    }
}
