package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Product;
import ru.spring.P50519.Models.Supplier;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    public List<Product> findByCategoryname(String name);
    public List<Product> findByCategorynameContaining(String name);

}
