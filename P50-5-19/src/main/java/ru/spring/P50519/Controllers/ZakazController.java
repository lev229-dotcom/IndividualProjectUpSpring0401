package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.*;
import ru.spring.P50519.Repository.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Zakaz")
@PreAuthorize("hasAnyAuthority('SELLER', 'ADMIN')")
public class ZakazController {

    @Autowired
    ZakazRepository zakazRepository;

    @Autowired
    ZakazstatusRepository zakazstatusRepository;
    @Autowired
    PaymentstatuszakazRepository paymentzakazstatusRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/Index")
    public String Zakaz(Model model)
    {
        Iterable<Zakaz> listWh= zakazRepository.findAll();
        model.addAttribute("wh",listWh);
        return "/Zakaz/Index";
    }

    @GetMapping("/IndexAddZakaz")
    public String AddView(Model model, Zakaz zakaz, Employee employee, Zakazstatus zakazstatus, Paymentstatus paymentstatus)
    {

        Iterable<Zakazstatus> listzakazstatus = zakazstatusRepository.findAll();
        Iterable<Paymentstatus> listpaymentzakazstatus = paymentzakazstatusRepository.findAll();
        Iterable<Employee> listEmployee= employeeRepository.findAll();
        model.addAttribute("listpaymentzakazstatus", listpaymentzakazstatus);
        model.addAttribute("listEmployee", listEmployee);
        model.addAttribute("listzakazstatus", listzakazstatus);
        return "/Zakaz/IndexAddZakaz";
    }

    @PostMapping("/IndexAddZakaz")
    public String AddZakaz(@Valid Zakaz zakaz,
                             BindingResult bindingResult,
                             @RequestParam Long listEmployee,
                             @RequestParam Long listzakazstatus,
                             @RequestParam Long listpaymentzakazstatus,
                             Model model)
    {
        if(bindingResult.hasErrors())
        {
            Iterable<Employee> listEmploye= employeeRepository.findAll();
            Iterable<Zakazstatus> listzakazstatu = zakazstatusRepository.findAll();
            model.addAttribute("listEmployee", listEmploye);
            model.addAttribute("listzakazstatus", listzakazstatu);
            return "/Zakaz/IndexAddZakaz";
        }
        zakaz.setEmployee(employeeRepository.findById(listEmployee).orElseThrow());
        zakaz.setPaymentzakazstatus(paymentzakazstatusRepository.findById(listpaymentzakazstatus).orElseThrow());
        zakaz.setZakazstatus(zakazstatusRepository.findById(listzakazstatus).orElseThrow());
        zakazRepository.save(zakaz);
        return "redirect:/Zakaz/Index";
    }

    @GetMapping("/ZakazDetail/{id}")
    public String AdressDetails(@PathVariable Long id,

                                Model model)
    {

        ArrayList<Zakaz> res = new ArrayList<Zakaz>();
        zakazRepository.findById(id).ifPresent(res::add);
        model.addAttribute("zakaz",res);

        return "/Zakaz/ZakazDetail";
    }

    @GetMapping ("/ZakazEdit/{id}")
    public String ZakazEditView(@PathVariable Long id, Employee employee,
                                  Model model,Zakaz zakaz, Zakazstatus zakazstatus, Paymentstatus paymentstatus)
    {
        Iterable<Zakazstatus> listzakazstatus = zakazstatusRepository.findAll();
        Iterable<Paymentstatus> listpaymentzakazstatus = paymentzakazstatusRepository.findAll();
        Iterable<Employee> listEmployee= employeeRepository.findAll();
        model.addAttribute("listpaymentzakazstatus", listpaymentzakazstatus);
        model.addAttribute("listEmployee", listEmployee);
        model.addAttribute("listzakazstatus", listzakazstatus);
        zakaz= zakazRepository.findById(id).orElseThrow();
        model.addAttribute("zakaz",zakaz);
        return "/Zakaz/ZakazEdit";
    }

    @PostMapping ("/ZakazEdit/{id}")
    public String ZakazEdit(
            @Valid Zakaz zakaz,
            BindingResult bindingResult,
            @RequestParam Long listEmployee,
            @RequestParam Long listzakazstatus,
            @RequestParam Long listpaymentzakazstatus,
            Model model)
    {
        model.addAttribute("Zakaz",zakaz);
        if (bindingResult.hasErrors())
        {
            Iterable<Employee> listEmploye= employeeRepository.findAll();
            Iterable<Zakazstatus> listzakazstatu = zakazstatusRepository.findAll();
            model.addAttribute("listEmployee", listEmploye);
            model.addAttribute("listzakazstatus", listzakazstatu);
            return "/Zakaz/ZakazEdit";
        }
        zakaz.setEmployee(employeeRepository.findById(listEmployee).orElseThrow());
        zakaz.setPaymentzakazstatus(paymentzakazstatusRepository.findById(listpaymentzakazstatus).orElseThrow());
        zakaz.setZakazstatus(zakazstatusRepository.findById(listzakazstatus).orElseThrow());
        zakazRepository.save(zakaz);
        return "redirect:/Zakaz/Index";
    }

    @GetMapping ("/ZakazDelete/{id}")
    public String ZakazDelete(@PathVariable Long id)
    {
        zakazRepository.deleteById(id);
        return "redirect:/Zakaz/Index";
    }

    @GetMapping("/Filter")
    public String DoljFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Zakaz> product=zakazRepository.findByOrdernumber(name);
        model.addAttribute("wh",product);
        return "/Zakaz/Filter";
    }
    @GetMapping("/FilterCategorized")
    public String DoljFilterCategorized(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Zakaz> product=zakazRepository.findByOrdernumberContaining(name);
        model.addAttribute("wh",product);
        return "/Zakaz/Filter";
    }

}
