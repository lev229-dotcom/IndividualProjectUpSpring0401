package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;


@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=30,message="Поле должно содержать не менне 3х и не более 30 символов")
    private String name;
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=30,message="Поле должно содержать не менне 3х и не более 30 символов")
    private String surname;

    @NotBlank(message="Пробелы недопустимы")
    private String patronymic;
    @Max(value=150,message="Сотруднику должно быть не более 150 лет")
    @Min(value=16,message="Сотруднику должно быть не менее 16 лет")
    @NotNull(message="Поле не должно быть пустым")
    private int age;
    @PositiveOrZero
    @NotNull(message="Поле не должно быть пустым")
    private int amount_of_kids;
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=50,message="Поле должно содержать не менне 3х и не более 30 символов")
    private String nationality;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<Zakaz> zakaz;

    @ManyToOne(optional =true)
    private Dolj dolj;

    @OneToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToMany
    @JoinTable(name="employee_adress",
    joinColumns=@JoinColumn(name = "employee_id"),
    inverseJoinColumns=@JoinColumn(name = "adress_id"))
    private List<Adress> adresses;

    public Dolj getDolj() {
        return dolj;
    }

    public void setDolj(Dolj dolj) {
        this.dolj = dolj;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Adress> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adress> adresses) {
        this.adresses = adresses;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Employee(Long id, String name, String surname, String patronymic, int age, int amount_of_kids, String nationality, Dolj dolj, Account account, List<Adress> adresses) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.amount_of_kids = amount_of_kids;
        this.nationality = nationality;
        this.dolj = dolj;
        this.account = account;
        this.adresses = adresses;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAmount_of_kids() {
        return amount_of_kids;
    }

    public void setAmount_of_kids(int amount_of_kids) {
        this.amount_of_kids = amount_of_kids;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Collection<Zakaz> getOrder() {
        return zakaz;
    }

    public void setOrder(Collection<Zakaz> zakaz) {
        this.zakaz = zakaz;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
