package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.Product;
import ru.spring.P50519.Models.Supplier;
import ru.spring.P50519.Models.WareHouse;
import ru.spring.P50519.Models.Zakaz;
import ru.spring.P50519.Repository.SupplierRepository;
import ru.spring.P50519.Repository.WareHouseRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Supplier")
@PreAuthorize("hasAnyAuthority('STOREKEEPER', 'ADMIN')")
public class SupplierController {

    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    WareHouseRepository wareHouseRepository;

    @GetMapping("/Index")
    public String Supplier(Model model)
    {
        Iterable<Supplier> listWh= supplierRepository.findAll();
        model.addAttribute("wh",listWh);
        return "/Supplier/Index";
    }

    @GetMapping("/IndexAddSupplier")
    public String AddView(Model model,Supplier supplier)
    {
        return "/Supplier/IndexAddSupplier";
    }

    @PostMapping("/IndexAddSupplier")
    public String AddWarehouse(@Valid Supplier supplier,
                               BindingResult bindingResult,
                               Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "/Supplier/IndexAddSupplier";
        }
        supplierRepository.save(supplier);
        return "redirect:/Supplier/Index";
    }

    @GetMapping("/SupplierDetail/{id}")
    public String AdressDetails(@PathVariable Long id,

                                Model model)
    {

        ArrayList<Supplier> res = new ArrayList<Supplier>();
        supplierRepository.findById(id).ifPresent(res::add);
        model.addAttribute("supplier",res);

        return "/Supplier/SupplierDetail";
    }

    @GetMapping ("/SupplierEdit/{id}")
    public String SupplierEditView(@PathVariable Long id,
                                    Model model,Supplier supplier)
    {
        supplier= supplierRepository.findById(id).orElseThrow();
        model.addAttribute("supplier",supplier);
        return "/Supplier/SupplierEdit";
    }
    @PostMapping ("/SupplierEdit/{id}")
    public String SupplierEdit(
            @Valid Supplier supplier,
            BindingResult bindingResult,
            Model model)
    {
        model.addAttribute("supplier",supplier);
        if (bindingResult.hasErrors())
        {
            return "/Supplier/SupplierEdit";
        }
        supplierRepository.save(supplier);
        return "redirect:/Supplier/Index";
    }

    @GetMapping ("/SupplierDelete/{id}")
    public String SupplierDelete(@PathVariable Long id)
    {
        supplierRepository.deleteById(id);
        return "redirect:/Supplier/Index";
    }

    @GetMapping("/WarehouseSupplier/")
    public String AdressEmpView(Model model, WareHouse warehouse)
    {

        Iterable<WareHouse> listWareHouse= wareHouseRepository.findAll();
        Iterable<Supplier> listSupplier= supplierRepository.findAll();
        model.addAttribute("listWareHouse",listWareHouse);
        model.addAttribute("listSupplier",listSupplier);

        return "/Supplier/WarehouseSupplier";
    }
    @PostMapping("/WarehouseSupplier/")
    public String AdressEmp(Model model,
                            @RequestParam Long listWareHouse, @RequestParam Long listSupplier)
    {
        WareHouse wareHouse = wareHouseRepository.findById(listWareHouse).orElseThrow();
        Supplier supplier = supplierRepository.findById(listSupplier).orElseThrow();

        wareHouse.getSuppliers().add(supplier);
        wareHouseRepository.save(wareHouse);
        return "/Supplier/Index";
    }

    @GetMapping("/Filter")
    public String DoljFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Supplier> supplier=supplierRepository.findByRepresentive(name);
        model.addAttribute("wh",supplier);
        return "/Supplier/Filter";
    }
    @GetMapping("/FilterCategorized")
    public String DoljFilterCategorized(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Supplier> supplier=supplierRepository.findByRepresentiveContaining(name);
        model.addAttribute("wh",supplier);
        return "/Supplier/Filter";
    }
}
