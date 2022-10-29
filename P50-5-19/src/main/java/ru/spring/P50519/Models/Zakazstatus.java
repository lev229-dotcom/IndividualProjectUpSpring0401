package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Zakazstatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    private String statusname;

    @OneToMany(mappedBy = "zakazstatus", fetch =FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<Zakaz> zakaz;

    public Zakazstatus(Long id, String statusname) {
        this.id = id;
        this.statusname = statusname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public Zakazstatus() {
    }
}
