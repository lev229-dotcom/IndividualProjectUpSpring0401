package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
public class Zakaz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    private String ordernumber;

    @NotNull
    @PositiveOrZero(message = "Значение не должно быть негативным")
    private Integer total_amount;

    @ManyToOne(optional =true)
    private Employee employee;

    @ManyToOne(optional =true)
    private Zakazstatus zakazstatus;

    @ManyToOne(optional =true)
    private Paymentstatus paymentzakazstatus;

    @ManyToMany
    @JoinTable(name="order_product",
            joinColumns=@JoinColumn(name = "order_id"),
            inverseJoinColumns=@JoinColumn(name = "product_id"))
    private List<Product> product;

    public Zakaz() {
    }

    public Zakaz(Long id, String ordernumber, Integer total_amount, Employee employee, Zakazstatus zakazstatus, Paymentstatus paymentzakazstatus, List<Product> product) {
        this.id = id;
        this.ordernumber = ordernumber;
        this.total_amount = total_amount;
        this.employee = employee;
        this.zakazstatus = zakazstatus;
        this.paymentzakazstatus = paymentzakazstatus;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Integer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Integer total_amount) {
        this.total_amount = total_amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Zakazstatus getZakazstatus() {
        return zakazstatus;
    }

    public void setZakazstatus(Zakazstatus zakazstatus) {
        this.zakazstatus = zakazstatus;
    }

    public Paymentstatus getPaymentzakazstatus() {
        return paymentzakazstatus;
    }

    public void setPaymentzakazstatus(Paymentstatus paymentzakazstatus) {
        this.paymentzakazstatus = paymentzakazstatus;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
