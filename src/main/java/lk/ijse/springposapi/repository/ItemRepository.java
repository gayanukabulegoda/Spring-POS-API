package lk.ijse.springposapi.repository;

import lk.ijse.springposapi.entity.impl.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
