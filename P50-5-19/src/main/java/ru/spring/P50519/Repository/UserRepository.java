package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Employee;
import ru.spring.P50519.Models.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
