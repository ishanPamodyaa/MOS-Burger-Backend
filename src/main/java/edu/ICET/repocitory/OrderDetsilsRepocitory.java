package edu.ICET.repocitory;

import edu.ICET.entity.Customer;
import edu.ICET.entity.OrderDetail;
import edu.ICET.entity.OrderDetails_pk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetsilsRepocitory extends JpaRepository<OrderDetail, OrderDetails_pk> {

List<OrderDetail> finfByOrder_OrderID (String orderId);

}
