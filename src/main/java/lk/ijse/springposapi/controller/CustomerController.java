package lk.ijse.springposapi.controller;

import lk.ijse.springposapi.customObj.CustomerResponse;
import lk.ijse.springposapi.dto.impl.CustomerDTO;
import lk.ijse.springposapi.exception.CustomerNotFoundException;
import lk.ijse.springposapi.exception.DataPersistFailedException;
import lk.ijse.springposapi.service.CustomerService;
import lk.ijse.springposapi.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCustomer(
            @RequestPart("name") String name,
            @RequestPart("email") String email,
            @RequestPart("city") String city,
            @RequestPart(value = "profilePic", required = false) MultipartFile profilePic
    ) {
        try {
            String profilePicBase64 = profilePic != null ? AppUtil.toBase64(profilePic) : null;
            customerService.saveCustomer(new CustomerDTO(null, name, email, city, profilePicBase64));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataPersistFailedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse getSelectedCustomer(@PathVariable("id") Integer id) {
        return customerService.getSelectedCustomer(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCustomer(
            @PathVariable("id") String id,
            @RequestPart("name") String name,
            @RequestPart("email") String email,
            @RequestPart("city") String city,
            @RequestPart(value = "profilePic", required = false) MultipartFile profilePic    ) {
        try {
            String profilePicBase64 = profilePic != null ? AppUtil.toBase64(profilePic) : null;
            customerService.updateCustomer(new CustomerDTO(id, name, email, city, profilePicBase64));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataPersistFailedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
