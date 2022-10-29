package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.Zoo;
import ru.spring.P50519.Repository.ZooRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Zoo")
public class ZooController {
    @Autowired
    ZooRepository zooRepository;
    @GetMapping("/Index")
    public String Zoo(Model model)
    {
      Iterable<Zoo> listZoo= zooRepository.findAll();

      model.addAttribute("listZoo",listZoo);
        return "/Zoo/Index";
    }
    @GetMapping("/IndexAddZoo")
    public String ZooAddView(Model model,Zoo zoo)
    {

        return "/Zoo/IndexAddZoo";
    }
    @PostMapping("/IndexAddZoo")
    public String ZooAdd(
            @Valid Zoo zoo,
            BindingResult bindingResult,
//            @RequestParam String name,
//            @RequestParam String description,
//            @RequestParam Integer age,
//            @RequestParam Integer mass,
            Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "/Zoo/IndexAddZoo";
        }
        //Zoo animal=new Zoo(name,age,description,mass);
        zooRepository.save(zoo);
        return "redirect:/Zoo/Index";
    }
    @GetMapping("/Filter")
    public String ZooFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Zoo> animal=zooRepository.findByNameContaining(name);
        model.addAttribute("anim",animal);
        return "/Zoo/Filter";
    }
    @GetMapping("/ZooDetail/{id}")
    public String ZooDetails(@PathVariable Long id,
                             Model model)
    {
        Optional<Zoo> animal = zooRepository.findById(id);
        ArrayList<Zoo> res = new ArrayList<Zoo>();
        animal.ifPresent(res::add);
        model.addAttribute("animal",res);
        return "/Zoo/ZooDetail";
    }
    @GetMapping ("/ZooDelete/{id}")
    public String ZooDelete(@PathVariable Long id)
    {
        zooRepository.deleteById(id);
        return "redirect:/Zoo/Index";
    }
    @GetMapping ("/ZooEdit/{id}")
    public String ZooEditView(@PathVariable Long id,
                              Model model)
    {
        Zoo animal= zooRepository.findById(id).orElseThrow();
        model.addAttribute("animal",animal);
        return "/Zoo/ZooEdit";
    }
    @PostMapping ("/ZooEdit/{id}")
    public String ZooEdit( @PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String description,
                           @RequestParam Integer age,
                           @RequestParam Integer mass,
                              Model model)
    {
        Zoo animal= zooRepository.findById(id).orElseThrow();
        animal.setName(name);
        animal.setDescription(description);
        animal.setAge(age);
        animal.setMass(mass);
        zooRepository.save(animal);
        model.addAttribute("animal",animal);
        return "redirect:/Zoo/Index";
    }
}

