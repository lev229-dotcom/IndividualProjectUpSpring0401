package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.*;
import ru.spring.P50519.Repository.CategoryRepository;
import ru.spring.P50519.Repository.ProductRepository;
import ru.spring.P50519.Repository.WareHouseRepository;
import ru.spring.P50519.Repository.ZakazRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Product")
@PreAuthorize("hasAnyAuthority('SELLER','STOREKEEPER', 'ADMIN')")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ZakazRepository zakazRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    WareHouseRepository wareHouseRepository;

    @GetMapping("/Index")
    public String Product(Model model)
    {
        Iterable<Product> listWh= productRepository.findAll();
        model.addAttribute("wh",listWh);
        return "/Product/Index";
    }

    @GetMapping("/IndexAddProduct")
    public String AddView(Model model,Product product, Category category, WareHouse warehouse)
    {

        Iterable<Category> listCategory= categoryRepository.findAll();
        Iterable<WareHouse> listWareHouse = wareHouseRepository.findAll();
        model.addAttribute("listCategory", listCategory);
        model.addAttribute("listWareHouse", listWareHouse);
        return "/Product/IndexAddProduct";
    }

    @PostMapping("/IndexAddProduct")
    public String AddProduct(@Valid Product product,
                               BindingResult bindingResult,
                               @RequestParam Long listCategory,
                               @RequestParam Long listWareHouse,
                               Model model)
    {
        if(bindingResult.hasErrors())
        {
            Iterable<Category> listCategor= categoryRepository.findAll();
            Iterable<WareHouse> listWareHous = wareHouseRepository.findAll();
            model.addAttribute("listCategory", listCategor);
            model.addAttribute("listWareHouse", listWareHous);
            return "/Product/IndexAddProduct";
        }
        product.setCategory(categoryRepository.findById(listCategory).orElseThrow());
        product.setWarehouse(wareHouseRepository.findById(listWareHouse).orElseThrow());
        productRepository.save(product);
        return "redirect:/Product/Index";
    }

    @GetMapping("/ProductDetail/{id}")
    public String AdressDetails(@PathVariable Long id,

                                Model model)
    {

        ArrayList<Product> res = new ArrayList<Product>();
        productRepository.findById(id).ifPresent(res::add);
        model.addAttribute("product",res);

        return "/Product/ProductDetail";
    }

    @GetMapping ("/ProductEdit/{id}")
    public String ProductEditView(@PathVariable Long id, Category category, WareHouse warehouse,
                                   Model model,Product product)
    {
        Iterable<Category> listCategory= categoryRepository.findAll();
        Iterable<WareHouse> listWareHouse = wareHouseRepository.findAll();
        model.addAttribute("listCategory", listCategory);
        model.addAttribute("listWareHouse", listWareHouse);
        product= productRepository.findById(id).orElseThrow();
        model.addAttribute("product",product);
        return "/Product/ProductEdit";
    }
    @PostMapping ("/ProductEdit/{id}")
    public String ProductEdit(
            @Valid Product product,
            BindingResult bindingResult,
            @RequestParam Long listCategory,
            @RequestParam Long listWareHouse,
            Model model)
    {
        model.addAttribute("Product",product);
        if (bindingResult.hasErrors())
        {
            Iterable<Category> listCategor= categoryRepository.findAll();
            Iterable<WareHouse> listWareHous = wareHouseRepository.findAll();
            model.addAttribute("listCategory", listCategor);
            model.addAttribute("listWareHouse", listWareHous);
            return "/Product/ProductEdit";
        }
        product.setCategory(categoryRepository.findById(listCategory).orElseThrow());
        product.setWarehouse(wareHouseRepository.findById(listWareHouse).orElseThrow());
        productRepository.save(product);
        return "redirect:/Product/Index";
    }

    @GetMapping ("/ProductDelete/{id}")
    public String ProductDelete(@PathVariable Long id)
    {
        productRepository.deleteById(id);
        return "redirect:/Product/Index";
    }

    @GetMapping("/OrderProduct/")
    public String AdressEmpView(Model model, Product product)
    {

        Iterable<Product> listProduct= productRepository.findAll();
        Iterable<Zakaz> listZakaz= zakazRepository.findAll();
        model.addAttribute("listProduct",listProduct);
        model.addAttribute("listZakaz",listZakaz);

        return "/Product/OrderProduct";
    }
    @PostMapping("/OrderProduct/")
    public String AdressEmp(Model model,
                            @RequestParam Long listProduct, @RequestParam Long listZakaz)
    {
        Product product = productRepository.findById(listProduct).orElseThrow();
        Zakaz zakaz = zakazRepository.findById(listZakaz).orElseThrow();

        product.getOrders().add(zakaz);
        productRepository.save(product);
        return "redirect:/Product/Index";
    }

    @GetMapping("/Filter")
    public String DoljFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Product> product=productRepository.findByCategoryname(name);
        model.addAttribute("wh",product);
        return "/Product/Filter";
    }
    @GetMapping("/FilterCategorized")
    public String DoljFilterCategorized(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Product> product=productRepository.findByCategorynameContaining(name);
        model.addAttribute("wh",product);
        return "/Product/Filter";
    }
}
