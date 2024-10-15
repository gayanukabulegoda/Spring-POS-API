package lk.ijse.springposapi.repository;

import lk.ijse.springposapi.entity.impl.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {}
