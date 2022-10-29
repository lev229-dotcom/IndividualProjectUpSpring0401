package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.*;
import ru.spring.P50519.Repository.AccountRepository;
import ru.spring.P50519.Repository.AdressRepository;
import ru.spring.P50519.Repository.EmployeeRepository;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Adress")
@PreAuthorize("hasAnyAuthority('CHAR', 'ADMIN')")
public class AdressController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AdressRepository adressRepository;
    @GetMapping("/Index")
    public String Adress(Model model)
    {

        Iterable<Adress> listAdress= adressRepository.findAll();

        model.addAttribute("listAdress",listAdress);
        return "/Adress/Index";
    }
    @GetMapping("/IndexAddAdress")
    public String EmpAddView(Model model,Adress adress )
    {

        return "/Adress/IndexAddAdress";
    }
    @PostMapping("/IndexAddAdress")
    public String AdressAdd(
            @Valid Adress adress,
            BindingResult bindingResult,


            Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "/Adress/IndexAddAdress";
        }
        adressRepository.save(adress);


        return "redirect:/Adress/Index";
    }
    @GetMapping("/AdressDetail/{id}")
    public String AdressDetails(@PathVariable Long id,

                             Model model)
    {

        ArrayList<Adress> res = new ArrayList<Adress>();
        adressRepository.findById(id).ifPresent(res::add);
        model.addAttribute("adress",res);

        return "/Adress/AdressDetail";
    }
    @GetMapping("/AdressEmployee/")
    public String AdressEmpView(Model model, Adress adress)
    {

        Iterable<Adress> listAdress= adressRepository.findAll();
        Iterable<Employee> listEmp= employeeRepository.findAll();
        model.addAttribute("listAdress",listAdress);
        model.addAttribute("listEmp",listEmp);

        return "/Adress/AdressEmployee";
    }
    @PostMapping("/AdressEmployee/")
    public String AdressEmp(Model model,
                            @RequestParam String listEmp, @RequestParam String listAdress)
    {
        Employee employee = employeeRepository.findFirstByName(listEmp);
        Adress adress = adressRepository.findByLocation(listAdress);

        adress.getEmployees().add(employee);
        adressRepository.save(adress);
        return "/Adress/Index";
    }

    @GetMapping("/Filter")
    public String DoljFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Adress> product=adressRepository.findByLocationContaining(name);
        model.addAttribute("wh",product);
        return "/Adress/Filter";
    }
    @GetMapping("/FilterCategorized")
    public String DoljFilterCategorized(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Adress> product=adressRepository.findByLocationContaining(name);
        model.addAttribute("wh",product);
        return "/Adress/Filter";
    }

    @GetMapping ("/AdressEdit/{id}")
    public String EmpEditView(@PathVariable Long id,Adress adress,
                              Model model)
    {
        adress= adressRepository.findById(id).orElseThrow();
        model.addAttribute("adress",adress);
        return "/Adress/AdressEdit";
    }

    @PostMapping ("/AdressEdit/{id}")
    public String EmpEdit(   @Valid Adress adress,
                             BindingResult bindingResult,
                             Model model)
    {
        model.addAttribute("adress",adress);

        if (bindingResult.hasErrors())
        {
            return "/Adress/AdressEdit";
        }
        adressRepository.save(adress);


        adressRepository.save(adress);

        return "redirect:/Adress/Index";
    }
}


