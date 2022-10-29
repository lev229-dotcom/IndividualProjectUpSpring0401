package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Account;
import ru.spring.P50519.Models.Dolj;
import ru.spring.P50519.Models.Employee;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account,Long> {


}
