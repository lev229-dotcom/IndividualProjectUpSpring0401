package ru.spring.P50519.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Пробелы недопустимы")
private String nickname;
    @NotBlank(message = "Пробелы недопустимы")
    @Size(min=5,max=50,message = "Минимум 5 и максимум 50 символов")
    private String description_user;
@OneToOne(optional = true,mappedBy = "account", cascade = CascadeType.ALL)
private Employee employee;

    public Account() {
    }

    public Account(Long id, String nickname, String description_user, Employee employee) {
        this.id = id;
        this.nickname = nickname;
        this.description_user = description_user;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription_user() {
        return description_user;
    }

    public void setDescription_user(String description_user) {
        this.description_user = description_user;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
