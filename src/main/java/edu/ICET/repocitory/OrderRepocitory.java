package edu.ICET.repocitory;

import edu.ICET.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepocitory extends JpaRepository <Order , Integer> {


}
