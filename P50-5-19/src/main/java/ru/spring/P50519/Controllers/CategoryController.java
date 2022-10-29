package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.Category;
import ru.spring.P50519.Models.Dolj;
import ru.spring.P50519.Models.Supplier;
import ru.spring.P50519.Repository.CategoryRepository;
import ru.spring.P50519.Repository.SupplierRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Category")
@PreAuthorize("hasAnyAuthority('SELLER','STOREKEEPER', 'ADMIN')")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/Index")
    public String Category(Model model)
    {
        Iterable<Category> listWh= categoryRepository.findAll();
        model.addAttribute("wh",listWh);
        return "/Category/Index";
    }

    @GetMapping("/IndexAddCategory")
    public String AddView(Model model,Category category)
    {
        return "/Category/IndexAddCategory";
    }

    @PostMapping("/IndexAddCategory")
    public String AddWarehouse(@Valid Category category,
                               BindingResult bindingResult,
                               Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "/Category/IndexAddCategory";
        }
        categoryRepository.save(category);
        return "redirect:/Category/Index";
    }

    @GetMapping("/CategoryDetail/{id}")
    public String AdressDetails(@PathVariable Long id,

                                Model model)
    {

        ArrayList<Category> res = new ArrayList<Category>();
        categoryRepository.findById(id).ifPresent(res::add);
        model.addAttribute("category",res);

        return "/Category/CategoryDetail";
    }

    @GetMapping ("/CategoryEdit/{id}")
    public String CategoryEditView(@PathVariable Long id,
                                   Model model,Category category)
    {
        category= categoryRepository.findById(id).orElseThrow();
        model.addAttribute("category",category);
        return "/Category/CategoryEdit";
    }
    @PostMapping ("/CategoryEdit/{id}")
    public String CategoryEdit(
            @Valid Category category,
            BindingResult bindingResult,
            Model model)
    {
        model.addAttribute("category",category);
        if (bindingResult.hasErrors())
        {
            return "/Category/CategoryEdit";
        }
        categoryRepository.save(category);
        return "redirect:/Category/Index";
    }

    @GetMapping ("/CategoryDelete/{id}")
    public String CategoryDelete(@PathVariable Long id)
    {
        categoryRepository.deleteById(id);
        return "redirect:/Category/Index";
    }

    @GetMapping("/Filter")
    public String DoljFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Category> category=categoryRepository.findByCategoryname(name);
        model.addAttribute("category_filtered",category);
        return "/Category/Filter";
    }
    @GetMapping("/FilterCategorized")
    public String DoljFilterCategorized(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Category> category=categoryRepository.findByCategorynameContaining(name);
        model.addAttribute("category_filtered",category);
        return "/Category/Filter";
    }
}
