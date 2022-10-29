package ru.spring.P50519.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Zoo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="The field should not be empty")
    @Size(min=1,max=150,message="The field should contain more than one symbol and less than 150 symbols")
    private String name;
@Max(value=50,message="Can't be older than 50")
@Min(value=1,message="Can't be younger than 1")
@NotNull(message="The field should not be empty")
    private Integer age;
@NotBlank(message="No spaces allowed")
    private String description;
@Positive(message="What kind of animal is this?")
@NotNull(message="The field should not be empty")
    private Integer mass;


//@PositiveOrZero
//
//@DecimalMax()
//@DecimalMin()
//
//@Future
//
//@FutureOrPresent
//
//@Past
//@PastOrPresent
//
//@Email
//
//@AssertTrue
//
//@AssertFalse
    public Zoo() {
    }

    public Zoo(String name, Integer age, String description, Integer mass) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.mass = mass;
    }
//geters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }
}
