package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spring.P50519.Models.Category;
import ru.spring.P50519.Models.Warehousetype;
import ru.spring.P50519.Models.Zakazstatus;
import ru.spring.P50519.Repository.WarehousetypeRepository;
import ru.spring.P50519.Repository.ZakazstatusRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/Zakazstatus")
public class ZakazstatusController {

    @Autowired
    ZakazstatusRepository zakazstatusRepository;

    @GetMapping("/Index")
    public String Category(Model model)
    {
        Iterable<Zakazstatus> listWh= zakazstatusRepository.findAll();
        model.addAttribute("wh",listWh);
        return "/Zakazstatus/Index";
    }

    @GetMapping("/IndexAddZakazstatus")
    public String AddView(Model model, Zakazstatus  zakazstatus)
    {
        return "/Zakazstatus/IndexAddZakazstatus";
    }

    @PostMapping("/IndexAddZakazstatus")
    public String AddWarehouse(@Valid Zakazstatus zakazstatus,
                               BindingResult bindingResult,
                               Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "/Zakazstatus/IndexAddZakazstatus";
        }
        zakazstatusRepository.save(zakazstatus);
        return "redirect:/Zakazstatus/Index";
    }
}
