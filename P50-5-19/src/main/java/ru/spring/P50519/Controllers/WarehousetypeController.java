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
import ru.spring.P50519.Repository.CategoryRepository;
import ru.spring.P50519.Repository.WarehousetypeRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/Warehousetype")
public class WarehousetypeController {

    @Autowired
    WarehousetypeRepository warehousetypeRepository;

    @GetMapping("/Index")
    public String Category(Model model)
    {
        Iterable<Warehousetype> listWh= warehousetypeRepository.findAll();
        model.addAttribute("wh",listWh);
        return "/Warehousetype/Index";
    }

    @GetMapping("/IndexAddWarehousetype")
    public String AddView(Model model,Category category)
    {
        return "/Warehousetype/IndexAddWarehousetype";
    }

    @PostMapping("/IndexAddWarehousetype")
    public String AddWarehouse(@Valid Warehousetype warehousetype,
                               BindingResult bindingResult,
                               Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "/Warehousetype/IndexAddWarehousetype";
        }
        warehousetypeRepository.save(warehousetype);
        return "redirect:/Warehousetype/Index";
    }
}
