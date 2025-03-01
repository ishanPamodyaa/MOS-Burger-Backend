package edu.ICET.repocitory;

import edu.ICET.entity.Customer;
import edu.ICET.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetsilsRepocitory extends JpaRepository<OrderDetail, Integer> {



}
