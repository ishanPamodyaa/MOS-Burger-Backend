package edu.ICET.repocitory;

import edu.ICET.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepocitory extends JpaRepository<Customer , Integer> {
}
