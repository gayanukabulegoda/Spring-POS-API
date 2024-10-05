package lk.ijse.springposapi.service.impl;

import lk.ijse.springposapi.customObj.CustomerResponse;
import lk.ijse.springposapi.customObj.impl.CustomerErrorResponse;
import lk.ijse.springposapi.dto.impl.CustomerDTO;
import lk.ijse.springposapi.entity.impl.Customer;
import lk.ijse.springposapi.exception.CustomerNotFoundException;
import lk.ijse.springposapi.repository.CustomerRepository;
import lk.ijse.springposapi.service.CustomerService;
import lk.ijse.springposapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Mapping mapping;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, Mapping mapping) {
        this.customerRepository = customerRepository;
        this.mapping = mapping;
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerRepository.save(mapping.convertToEntity(customerDTO, Customer.class));
    }

    @Override
    @Transactional
    public void updateCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(Integer.valueOf(customerDTO.getId()))
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setCity(customerDTO.getCity());
        customer.setProfilePic(customerDTO.getProfilePic());
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(Integer.valueOf(id));
    }

    @Override
    public CustomerResponse getSelectedCustomer(String id) {
        return (customerRepository.existsById(Integer.valueOf(id)))
                ? mapping.convertToDTO(customerRepository.getReferenceById(Integer.valueOf(id)), CustomerDTO.class)
                : new CustomerErrorResponse(0, "Customer not found");
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.convertToDTOList(customerRepository.findAll());
    }
}
