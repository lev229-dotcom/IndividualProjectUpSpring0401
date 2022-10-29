package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Warehousetype {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    private String warehousetypename;

    @NotEmpty(message="Поле не должно быть пустым")
    private String warehousetypedescription;

    @OneToMany(mappedBy = "warehousetype", fetch =FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<WareHouse> warehouse;

    public Warehousetype() {
    }

    public Warehousetype(Long id, String warehousetypename, String warehousetypedescription, Collection<WareHouse> warehouse) {
        this.id = id;
        this.warehousetypename = warehousetypename;
        this.warehousetypedescription = warehousetypedescription;
        this.warehouse = warehouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehousetypename() {
        return warehousetypename;
    }

    public void setWarehousetypename(String warehousetypename) {
        this.warehousetypename = warehousetypename;
    }

    public String getWarehousetypedescription() {
        return warehousetypedescription;
    }

    public void setWarehousetypedescription(String warehousetypedescription) {
        this.warehousetypedescription = warehousetypedescription;
    }

    public Collection<WareHouse> getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Collection<WareHouse> warehouse) {
        this.warehouse = warehouse;
    }
}
