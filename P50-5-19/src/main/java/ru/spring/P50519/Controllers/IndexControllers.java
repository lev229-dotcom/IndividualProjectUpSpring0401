package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexControllers {

    @GetMapping("/Home")
    public String Home ()
    {
        return "Home";
    }
    @GetMapping("/Index")
    public String IndexGet (@RequestParam(name="firstdigit",required = false) Double first,
                        @RequestParam(required = false, name="option_get") String option,
                        @RequestParam(required = false,name = "seconddigit") Double second,
                        Model model)
    {
        double result=0.0;
        switch (option)
        {
            case "plus":
            {
                result=first+second;
                break;
            }
            case "subtract":
            {
                result=first-second;
                break;
            }
            case "multiplication":
            {
                result=first*second;
                break;
            }
            case "divide":
            {
                if(second!=0)
                result=first/second;
                        else
                {
                    result=0.0;
                }
                break;
            }
        }
        model.addAttribute("result",result);
        return "Index";
    }

    @PostMapping("/Index")
    public String IndexPost (@RequestParam(name="firstdigit",required = false) Double first,
                        @RequestParam(required = false, name="option_post") String option,
                        @RequestParam(required = false,name = "seconddigit") Double second,
                        Model model)
    {
        double result=0.0;
        switch (option)
        {
            case "plus":
            {
                result=first+second;
                break;
            }
            case "subtract":
            {
                result=first-second;
                break;
            }
            case "multiplication":
            {
                result=first*second;
                break;
            }
            case "divide":
            {
                if(second!=0)
                    result=first/second;
                else
                {
                    result=0.0;
                }
                break;
            }
        }
        model.addAttribute("result",result);
        return "Index";
    };
}
