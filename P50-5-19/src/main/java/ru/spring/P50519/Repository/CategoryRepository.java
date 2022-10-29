package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Category;
import ru.spring.P50519.Models.Dolj;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    public List<Category> findByCategoryname(String categoryname);
    public List<Category> findByCategorynameContaining(String categoryname);

}
