package lk.ijse.springposapi.repository;

import lk.ijse.springposapi.embedded.OrderDetailPK;
import lk.ijse.springposapi.entity.impl.Order;
import lk.ijse.springposapi.entity.impl.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
    @Query("SELECT od FROM OrderDetail od WHERE od.order=:order")
    List<OrderDetail> getAllByOrder(@Param("order") Order order);
    boolean existsByItemId(int itemId);
}
