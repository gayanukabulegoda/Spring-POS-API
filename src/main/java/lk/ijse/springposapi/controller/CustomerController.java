package lk.ijse.springposapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lk.ijse.springposapi.customObj.CustomerResponse;
import lk.ijse.springposapi.dto.impl.CustomerDTO;
import lk.ijse.springposapi.exception.CustomerNotFoundException;
import lk.ijse.springposapi.exception.DataPersistFailedException;
import lk.ijse.springposapi.service.CustomerService;
import lk.ijse.springposapi.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {
    private final CustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCustomer(
            @RequestPart("name") @Valid @NotBlank(message = "Name is mandatory")
            @Size(min=3, max = 40, message = "Name must be less than 40 characters") String name,
            @RequestPart("email") @Valid @NotBlank(message = "Email is mandatory") @Email(message = "Please provide a valid email address")
            @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters") String email,
            @RequestPart("city") @Valid @NotBlank(message = "City is mandatory")
            @Size(min = 3, max = 30, message = "City must be between 3 and 30 characters") String city,
            @RequestPart(value = "profilePic", required = false) MultipartFile profilePic
    ) {
        logger.info("Received request to save customer: name={}, email={}, city={}", name, email, city);
        try {
            String profilePicBase64 = profilePic != null ? AppUtil.toBase64(profilePic) : null;
            customerService.saveCustomer(new CustomerDTO(null, name, email, city, profilePicBase64));
            logger.info("Customer saved successfully: name={}, email={}, city={}", name, email, city);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataPersistFailedException e) {
            logger.error("Data persist failed for customer: name={}, email={}, city={}", name, email, city, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Error saving customer: name={}, email={}, city={}", name, email, city, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse getSelectedCustomer(@PathVariable("id") int id) {
        logger.info("Received request to get customer with ID: {}", id);
        try {
            CustomerResponse customerResponse = customerService.getSelectedCustomer(id);
            logger.info("Successfully retrieved customer response with ID: {}", id);
            return ResponseEntity.status(HttpStatus.OK).body(customerResponse).getBody();
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found with ID: {}", id, e);
            return (CustomerResponse) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving customer with ID: {}", id, e);
            return (CustomerResponse) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        logger.info("Received request to get all customers");
        try {
            List<CustomerDTO> customers = customerService.getAllCustomers();
            logger.info("Successfully retrieved all customers");
            return ResponseEntity.status(HttpStatus.OK).body(customers);
        } catch (CustomerNotFoundException e) {
            logger.error("No customers found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving all customers", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCustomer(
            @PathVariable("id") String id,
            @RequestPart("name") @Valid @NotBlank(message = "Name is mandatory")
            @Size(min=3, max = 40, message = "Name must be less than 40 characters") String name,
            @RequestPart("email") @Valid @NotBlank(message = "Email is mandatory") @Email(message = "Please provide a valid email address")
            @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters") String email,
            @RequestPart("city") @Valid @NotBlank(message = "City is mandatory")
            @Size(min = 3, max = 30, message = "City must be between 3 and 30 characters") String city,
            @RequestPart(value = "profilePic", required = false) MultipartFile profilePic
    ) {
        logger.info("Received request to update customer with ID: {}", id);
        try {
            String profilePicBase64 = profilePic != null ? AppUtil.toBase64(profilePic) : null;
            customerService.updateCustomer(new CustomerDTO(id, name, email, city, profilePicBase64));
            logger.info("Customer updated successfully with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataPersistFailedException e) {
            logger.error("Data persist failed for customer with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Error updating customer with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int id) {
        logger.info("Received request to delete customer with ID: {}", id);
        try {
            customerService.deleteCustomer(id);
            logger.info("Customer deleted successfully with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalStateException e) {
            logger.error("Cannot delete customer with ID: {} as it is part of an existing order", id, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error deleting customer with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
