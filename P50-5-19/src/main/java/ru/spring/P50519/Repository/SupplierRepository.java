package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Supplier;
import ru.spring.P50519.Models.WareHouse;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    public List<Supplier> findByRepresentive(String name);
    public List<Supplier> findByRepresentiveContaining(String name);
}
