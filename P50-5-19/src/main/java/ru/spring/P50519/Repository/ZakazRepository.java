package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Product;
import ru.spring.P50519.Models.Zakaz;

import java.util.List;

public interface ZakazRepository extends CrudRepository<Zakaz, Long> {
    public List<Zakaz> findByOrdernumber(String name);
    public List<Zakaz> findByOrdernumberContaining(String name);

}
