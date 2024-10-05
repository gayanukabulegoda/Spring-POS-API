package lk.ijse.springposapi.service;

import lk.ijse.springposapi.customObj.CustomerResponse;
import lk.ijse.springposapi.dto.impl.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(String id);
    CustomerResponse getSelectedCustomer(String id);
    List<CustomerDTO> getAllCustomers();
}
