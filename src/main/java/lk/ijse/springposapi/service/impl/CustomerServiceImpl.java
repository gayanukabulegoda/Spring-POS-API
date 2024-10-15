package lk.ijse.springposapi.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springposapi.customObj.CustomerResponse;
import lk.ijse.springposapi.customObj.impl.CustomerErrorResponse;
import lk.ijse.springposapi.dto.impl.CustomerDTO;
import lk.ijse.springposapi.entity.impl.Customer;
import lk.ijse.springposapi.exception.CustomerNotFoundException;
import lk.ijse.springposapi.repository.CustomerRepository;
import lk.ijse.springposapi.repository.OrderRepository;
import lk.ijse.springposapi.service.CustomerService;
import lk.ijse.springposapi.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final Mapping mapping;

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
    public void deleteCustomer(int id) {
        if (orderRepository.existsByCustomerId(id)) {
            throw new IllegalStateException("Cannot delete customer as it is part of an existing order.");
        } else if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerResponse getSelectedCustomer(int id) {
        Optional<Customer> byId = customerRepository.findById(id);
        return (byId.isPresent())
                ? mapping.convertToDTO(byId.get(), CustomerDTO.class)
                : new CustomerErrorResponse(0, "Customer not found");
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.convertToDTOList(customerRepository.findAll(), CustomerDTO.class);
    }
}
