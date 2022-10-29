package ru.spring.P50519.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.Role;
import ru.spring.P50519.Models.User;
import ru.spring.P50519.Repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/User")
@PreAuthorize("hasAnyAuthority('ADMIN', 'CHAR')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

@GetMapping("/userView")
    public String userView(Model model)
    {
        model.addAttribute("listUser",userRepository.findAll());
        return "User/userView";
    }
    @GetMapping("/userEdit/{id}")
    public String userEditView(@PathVariable(name="id") Long id, Model model, User user)
    {
        model.addAttribute("listRole", Role.values());
        user = userRepository.findById(id).orElseThrow();
        model.addAttribute("userOne",user);
        return "User/userEdit";
    }
    @PostMapping("/userEdit/{id}")
    public String userEditView(@PathVariable(name="id") Long id,
                               @Valid User user,
                               BindingResult bindingResult,
                               @RequestParam(name="role[]") String[] userRoles,
                               Model model)
    {
        model.addAttribute("userOne", user);
        if(bindingResult.hasErrors()){
            model.addAttribute("listRole", Role.values());
            return "User/userEdit";
        }
        //user.setUsername(username);
        user.getRole().clear();
        for (String roleOne:
        userRoles)
        {
            user.getRole().add(Role.valueOf(roleOne));
        }
        userRepository.save(user);
      return "redirect:/User/userView";
    }

    @GetMapping ("/userDelete/{id}")
    public String UserDelete(@PathVariable Long id)
    {
        userRepository.deleteById(id);
        return "redirect:/User/userView";
    }
}
