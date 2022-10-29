package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Zoo;

import java.util.List;

public interface ZooRepository extends CrudRepository<Zoo,Long> {

    public List<Zoo> findByName(String name);
    public List<Zoo> findByNameContaining(String name);
}
