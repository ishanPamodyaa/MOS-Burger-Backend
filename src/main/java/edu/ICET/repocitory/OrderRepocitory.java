package edu.ICET.repocitory;

import edu.ICET.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepocitory extends JpaRepository <Order , String> {

    Optional<Order> findByOrderId (String orderId);
}
