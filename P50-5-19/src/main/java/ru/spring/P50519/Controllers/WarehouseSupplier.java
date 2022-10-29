//package ru.spring.P50519.Controllers;
//
//import ru.spring.P50519.Models.Dolj;
//import ru.spring.P50519.Models.Supplier;
//import ru.spring.P50519.Models.WareHouse;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//
//@Entity
//public class WarehouseSupplier {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @NotEmpty(message="Поле не должно быть пустым")
//    private String supplyNumber;
//
//    @ManyToOne(optional =true)
//    private WareHouse warehouse;
//
//    @ManyToOne(optional =true)
//    private Supplier supplier;
//}
