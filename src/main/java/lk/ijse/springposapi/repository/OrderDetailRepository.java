package lk.ijse.springposapi.repository;

import lk.ijse.springposapi.embedded.OrderDetailPK;
import lk.ijse.springposapi.entity.impl.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
}
