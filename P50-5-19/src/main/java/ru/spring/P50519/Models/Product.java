package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategorydescription() {
        return categorydescription;
    }

    public void setCategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }

    @NotEmpty(message="Поле не должно быть пустым")
    private String categoryname;

    @NotEmpty(message="Поле не должно быть пустым")
    private String categorydescription;

    @ManyToOne(optional =true)
    private WareHouse warehouse;

    @ManyToOne(optional =true)
    private Category category;

    @ManyToMany
    @JoinTable(name="order_product",
            joinColumns=@JoinColumn(name = "product_id"),
            inverseJoinColumns=@JoinColumn(name = "order_id"))
    private List<Zakaz> orders;

    public Product(Long id, String categoryname, String categorydescription, WareHouse warehouse, Category category, List<Zakaz> orders) {
        this.id = id;
        this.categoryname = categoryname;
        this.categorydescription = categorydescription;
        this.warehouse = warehouse;
        this.category = category;
        this.orders = orders;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcategoryname() {
        return categoryname;
    }

    public void setcategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getcategorydescription() {
        return categorydescription;
    }

    public void setcategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }

    public WareHouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WareHouse warehouse) {
        this.warehouse = warehouse;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Zakaz> getOrders() {
        return orders;
    }

    public void setOrders(List<Zakaz> orders) {
        this.orders = orders;
    }
}
