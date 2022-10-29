package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Paymentstatus;
import ru.spring.P50519.Models.Zakazstatus;

public interface PaymentstatuszakazRepository extends CrudRepository<Paymentstatus, Long> {
}
