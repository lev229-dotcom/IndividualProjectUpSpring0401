package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;


@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotEmpty(message="Поле не должно быть пустым")
    private String categoryname;

    @NotEmpty(message="Поле не должно быть пустым")
    private String categorydescription;

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

    @OneToMany(mappedBy = "category", fetch =FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<Product> product;

    public Category(Long id, String categoryname, String categorydescription, Collection<Product> product) {
        this.id = id;
        this.categoryname = categoryname;
        this.categorydescription = categorydescription;
        this.product = product;
    }

    public Category() {
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

    public Collection<Product> getProduct() {
        return product;
    }

    public void setProduct(Collection<Product> product) {
        this.product = product;
    }
}
