package edu.ICET.repocitory;

import edu.ICET.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepocitory extends JpaRepository<Product , Integer> {

}
