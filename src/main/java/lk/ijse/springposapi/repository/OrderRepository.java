package lk.ijse.springposapi.repository;

import lk.ijse.springposapi.entity.impl.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
