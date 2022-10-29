package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spring.P50519.Models.Paymentstatus;
import ru.spring.P50519.Models.Zakazstatus;
import ru.spring.P50519.Repository.PaymentstatuszakazRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/Paymentstatus")
public class PaymentstatusController {

    @Autowired
    PaymentstatuszakazRepository paymentstatuszakazRepository;

    @GetMapping("/Index")
    public String Category(Model model)
    {
        Iterable<Paymentstatus> listWh= paymentstatuszakazRepository.findAll();
        model.addAttribute("wh",listWh);
        return "/Paymentstatus/Index";
    }

    @GetMapping("/IndexAddPaymentstatus")
    public String AddView(Model model, Paymentstatus  paymentstatus)
    {
        return "/Paymentstatus/IndexAddPaymentstatus";
    }

    @PostMapping("/IndexAddPaymentstatus")
    public String AddWarehouse(@Valid Paymentstatus paymentstatus,
                               BindingResult bindingResult,
                               Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "/Paymentstatus/IndexAddPaymentstatus";
        }
        paymentstatuszakazRepository.save(paymentstatus);
        return "redirect:/Paymentstatus/Index";
    }
}
