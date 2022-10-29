package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Paymentstatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    private String paymentstatusname;

    @OneToMany(mappedBy = "paymentzakazstatus", fetch =FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<Zakaz> zakaz;

    public Paymentstatus(Long id, String paymentstatusname, Collection<Zakaz> zakaz) {
        this.id = id;
        this.paymentstatusname = paymentstatusname;
        this.zakaz = zakaz;
    }

    public Paymentstatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentstatusname() {
        return paymentstatusname;
    }

    public void setPaymentstatusname(String paymentstatusname) {
        this.paymentstatusname = paymentstatusname;
    }

    public Collection<Zakaz> getZakaz() {
        return zakaz;
    }

    public void setZakaz(Collection<Zakaz> zakaz) {
        this.zakaz = zakaz;
    }
}
