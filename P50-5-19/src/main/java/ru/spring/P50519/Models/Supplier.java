package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    private String supplier_name;

    @NotEmpty(message="Поле не должно быть пустым")
    private String representive;

    @NotEmpty(message="Поле не должно быть пустым")
    private String supplier_address;

    @ManyToMany
    @JoinTable(name="warehouse_supplier",
            joinColumns=@JoinColumn(name = "supplier_id"),
            inverseJoinColumns=@JoinColumn(name = "warehouse_id"))
    private List<WareHouse> wareHouses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public Supplier(Long id, String supplier_name, String representive, String supplier_address, List<WareHouse> wareHouses) {
        this.id = id;
        this.supplier_name = supplier_name;
        this.representive = representive;
        this.supplier_address = supplier_address;
        this.wareHouses = wareHouses;
    }

    public Supplier() {
    }

    public String getRepresentive() {
        return representive;
    }

    public void setRepresentive(String representive) {
        this.representive = representive;
    }

    public String getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(String supplier_address) {
        this.supplier_address = supplier_address;
    }

    public List<WareHouse> getWareHouses() {
        return wareHouses;
    }

    public void setWareHouses(List<WareHouse> wareHouses) {
        this.wareHouses = wareHouses;
    }
}
