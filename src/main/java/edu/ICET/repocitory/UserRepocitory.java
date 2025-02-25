package edu.ICET.repocitory;

import edu.ICET.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepocitory extends JpaRepository<User ,Integer> {
}
