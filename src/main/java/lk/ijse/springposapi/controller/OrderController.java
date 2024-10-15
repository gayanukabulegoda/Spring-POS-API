package lk.ijse.springposapi.controller;

import jakarta.validation.Valid;
import lk.ijse.springposapi.customObj.OrderResponse;
import lk.ijse.springposapi.dto.impl.OrderDTO;
import lk.ijse.springposapi.exception.DataPersistFailedException;
import lk.ijse.springposapi.exception.OrderNotFoundException;
import lk.ijse.springposapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@Valid @RequestBody OrderDTO orderDTO) {
        logger.info("Received request to save order: {}", orderDTO);
        try {
            orderService.saveOrder(orderDTO);
            logger.info("Order saved successfully: {}", orderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataPersistFailedException e) {
            logger.error("Data persist failed for order: {}", orderDTO, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Error saving order: {}", orderDTO, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse getSelectedOrder(@PathVariable("id") int id) {
        logger.info("Received request to get order with ID: {}", id);
        try {
            OrderResponse orderResponse = orderService.getSelectedOrder(id);
            logger.info("Successfully retrieved order with ID: {}", id);
            return ResponseEntity.status(HttpStatus.OK).body(orderResponse).getBody();
        } catch (OrderNotFoundException e) {
            logger.error("Order not found with ID: {}", id, e);
            return (OrderResponse) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving order with ID: {}", id, e);
            return (OrderResponse) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        logger.info("Received request to get all orders");
        try {
            List<OrderDTO> orders = orderService.getAllOrders();
            logger.info("Successfully retrieved all orders");
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        } catch (OrderNotFoundException e) {
            logger.error("No orders found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving all orders", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
