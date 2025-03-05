package edu.ICET.repocitory;

import edu.ICET.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepocitory extends JpaRepository<Customer , Integer> {

    Customer findByContactNumber (String contactNumber);
    Customer findByNic (String nic);
    List <Customer> findByFstName (String fstName);
    List <Customer> findByLstName (String lstName);
    List <Customer> findByCity (String city);


    Optional<Customer> findByCustomerId(String string);
}
