package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.*;
import ru.spring.P50519.Repository.WareHouseRepository;
import ru.spring.P50519.Repository.WarehousetypeRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Warehouse")
@PreAuthorize("hasAnyAuthority('STOREKEEPER', 'ADMIN')")
public class WareHouseController {

    @Autowired
    WareHouseRepository wareHouseRepository;

    @Autowired
    WarehousetypeRepository warehousetypeRepository;

    @GetMapping("/Index")
    public String Employee(Model model)
    {
        Iterable<WareHouse> listWh= wareHouseRepository.findAll();
        model.addAttribute("wh",listWh);
        return "/Warehouse/Index";
    }

    @GetMapping("/IndexAddWarehouse")
    public String AddView(Model model,WareHouse ware_house, Warehousetype warehousetype)
    {
        Iterable<Warehousetype> listwarehousetype = warehousetypeRepository.findAll();
        model.addAttribute("listwarehousetype", listwarehousetype);
        model.addAttribute("ware_house", ware_house);
        return "/Warehouse/IndexAddWarehouse";
    }

   @PostMapping("/IndexAddWarehouse")
    public String AddWarehouse(@Valid WareHouse ware_house,
                               @RequestParam Long listwarehousetype,
                               BindingResult bindingResult,
                               Model model)
   {
       if(bindingResult.hasErrors())
       {
           Iterable<Warehousetype> listwarehousetyp = warehousetypeRepository.findAll();
           model.addAttribute("listwarehousetype", listwarehousetyp);
           model.addAttribute("ware_house", ware_house);
           return "/Warehouse/IndexAddWarehouse";
       }

       ware_house.setWarehousetype(warehousetypeRepository.findById(listwarehousetype).orElseThrow());
       wareHouseRepository.save(ware_house);
       return "redirect:/Warehouse/Index";
    }

    @GetMapping("/WarehouseDetail/{id}")
    public String AdressDetails(@PathVariable Long id,

                                Model model)
    {

        ArrayList<WareHouse> res = new ArrayList<WareHouse>();
        wareHouseRepository.findById(id).ifPresent(res::add);
        model.addAttribute("warehouse",res);

        return "/Warehouse/WarehouseDetail";
    }
    @GetMapping ("/WarehouseDelete/{id}")
    public String WarehouseDelete(@PathVariable Long id)
    {
        wareHouseRepository.deleteById(id);
        return "redirect:/Warehouse/Index";
    }

    @GetMapping ("/WarehouseEdit/{id}")
    public String WarehouseEditView(@PathVariable Long id,
                               Model model,WareHouse warehouse, Warehousetype warehousetype)
    {
        Iterable<Warehousetype> listwarehousetype = warehousetypeRepository.findAll();
        model.addAttribute("listwarehousetype", listwarehousetype);

        warehouse= wareHouseRepository.findById(id).orElseThrow();
        model.addAttribute("warehouse",warehouse);
        return "/Warehouse/WarehouseEdit";
    }
    @PostMapping ("/WarehouseEdit/{id}")
    public String WarehouseEdit(
            @Valid WareHouse warehouse,
            @RequestParam Long listwarehousetype,
            BindingResult bindingResult,
            Model model)
    {
        model.addAttribute("warehouse",warehouse);
        if (bindingResult.hasErrors())
        {
            return "/Warehouse/WarehouseEdit";
        }
        warehouse.setWarehousetype(warehousetypeRepository.findById(listwarehousetype).orElseThrow());
        wareHouseRepository.save(warehouse);
        return "redirect:/Warehouse/Index";
    }

    @GetMapping("/Filter")
    public String DoljFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<WareHouse> warehouse=wareHouseRepository.findByWarehousename(name);
        model.addAttribute("wh",warehouse);
        return "/Warehouse/Filter";
    }
    @GetMapping("/FilterCategorized")
    public String DoljFilterCategorized(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<WareHouse> warehouse=wareHouseRepository.findByWarehousenameContaining(name);
        model.addAttribute("wh",warehouse);
        return "/Warehouse/Filter";
    }
}
