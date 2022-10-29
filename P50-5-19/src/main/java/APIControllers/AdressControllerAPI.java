package APIControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.spring.P50519.Models.Account;
import ru.spring.P50519.Models.Adress;
import ru.spring.P50519.Models.Dolj;
import ru.spring.P50519.Models.Employee;
import ru.spring.P50519.Repository.AccountRepository;
import ru.spring.P50519.Repository.AdressRepository;
import ru.spring.P50519.Repository.EmployeeRepository;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Adress")
@PreAuthorize("hasAnyAuthority('HR')")
public class AdressControllerAPI {
    @Autowired
    AdressRepository adressRepository;

    @GetMapping("/Index")
    public List<Adress> findAllUsers() {
        Iterable<Adress> listAdress= adressRepository.findAll();
       return (List<Adress>) listAdress;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Adress> findUserById(@PathVariable(value = "id") long id) {
//        // Implement
//    }
//
//    @PostMapping
//    public Adress saveUser(@Validated @RequestBody Adress user) {
//        // Implement
//    }
}


