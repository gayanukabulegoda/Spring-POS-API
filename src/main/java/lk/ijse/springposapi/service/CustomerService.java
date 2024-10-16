package lk.ijse.springposapi.service;

import lk.ijse.springposapi.customObj.CustomerResponse;
import lk.ijse.springposapi.dto.impl.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(int id);
    CustomerResponse getSelectedCustomer(int id);
    List<CustomerDTO> getAllCustomers();
}
