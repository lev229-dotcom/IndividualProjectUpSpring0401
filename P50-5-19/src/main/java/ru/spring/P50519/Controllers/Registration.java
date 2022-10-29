package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.P50519.Models.Role;
import ru.spring.P50519.Models.User;
import ru.spring.P50519.Repository.UserRepository;

import java.util.Collection;
import java.util.Collections;

@Controller
public class Registration {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/Registration")
    public String regView( User user)
    {
        return "Registration";
    }

    @PostMapping("/Registration")
    public String regUser(
            User user,
            Model model

    )
    {
       if( userRepository.findByUsername(user.getUsername())!=null)
        {
            model.addAttribute("error","Такой пользователь уже существует");
            return "Registration";
        }
       else if(user.getUsername().length()<6 || user.getPassword().length()<6 ){
           model.addAttribute("error","Логин должен быть длинее 6 символов");
           return "Registration";
       }
       user.setActive(true);
       user.setRole(Collections.singleton(Role.ADMIN));
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       userRepository.save(user);
        return "redirect:/Login";
    }
}
