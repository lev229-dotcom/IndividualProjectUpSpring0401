package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message="Поле не должно быть пустым")
    private String location;

    public Adress(Long id, String location, String date_of_arrival, List<Employee> employees) {
        this.id = id;
        this.location = location;
        this.date_of_arrival = date_of_arrival;
        this.employees = employees;
    }

    public String getDate_of_arrival() {
        return date_of_arrival;
    }

    public void setDate_of_arrival(String date_of_arrival) {
        this.date_of_arrival = date_of_arrival;
    }

    @NotEmpty(message="Поле не должно быть пустым")
    private String date_of_arrival;
    @ManyToMany
    @JoinTable(name="employee_adress",
            joinColumns=@JoinColumn(name = "adress_id"),
            inverseJoinColumns=@JoinColumn(name = "employee_id"))
    private List<Employee> employees;


    public Adress() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
