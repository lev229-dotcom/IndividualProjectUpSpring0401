package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
public class WareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    private String warehousename;

    public Warehousetype getWarehousetype() {
        return warehousetype;
    }

    public void setWarehousetype(Warehousetype warehousetype) {
        this.warehousetype = warehousetype;
    }

    @NotEmpty(message="Поле не должно быть пустым")
    private String warehouseaddress;

    @ManyToOne(optional =true)
    private Warehousetype warehousetype;


    public String getWarehousename() {
        return warehousename;
    }

    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename;
    }

    public String getWarehouseaddress() {
        return warehouseaddress;
    }

    public void setWarehouseaddress(String warehouseaddress) {
        this.warehouseaddress = warehouseaddress;
    }

    @OneToMany(mappedBy = "warehouse", fetch =FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<Product> product;

    @ManyToMany
    @JoinTable(name="warehouse_supplier",
            joinColumns=@JoinColumn(name = "warehouse_id"),
            inverseJoinColumns=@JoinColumn(name = "supplier_id"))
    private List<Supplier> suppliers;

    public WareHouse(Long id, String warehousename, String warehouseaddress, Collection<Product> product, List<Supplier> suppliers) {
        this.id = id;
        this.warehousename = warehousename;
        this.warehouseaddress = warehouseaddress;
        this.product = product;
        this.suppliers = suppliers;
    }

    public Collection<Product> getProduct() {
        return product;
    }

    public void setProduct(Collection<Product> product) {
        this.product = product;
    }

    public WareHouse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouse_name() {
        return warehousename;
    }

    public void setWarehouse_name(String warehousename) {
        this.warehousename = warehousename;
    }

    public String getWarehouse_address() {
        return warehouseaddress;
    }

    public void setWarehouse_address(String warehouseaddress) {
        this.warehouseaddress = warehouseaddress;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
