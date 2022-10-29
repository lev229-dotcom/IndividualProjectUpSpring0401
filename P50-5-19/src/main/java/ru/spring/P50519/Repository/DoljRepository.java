package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Dolj;

import java.util.List;

public interface DoljRepository extends CrudRepository<Dolj,Long> {
    public List<Dolj> findByName(String name);
    public List<Dolj> findByNameContaining(String name);
}
