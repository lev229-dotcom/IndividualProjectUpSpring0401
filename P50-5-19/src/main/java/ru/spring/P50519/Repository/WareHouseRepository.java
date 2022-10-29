package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Category;
import ru.spring.P50519.Models.WareHouse;

import java.util.List;

public interface WareHouseRepository extends CrudRepository<WareHouse, Long> {
    public List<WareHouse> findByWarehousename(String name);
    public List<WareHouse> findByWarehousenameContaining(String name);
}
