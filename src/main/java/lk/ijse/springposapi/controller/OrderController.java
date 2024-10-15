package lk.ijse.springposapi.controller;

import lk.ijse.springposapi.customObj.OrderResponse;
import lk.ijse.springposapi.dto.impl.OrderDTO;
import lk.ijse.springposapi.exception.DataPersistFailedException;
import lk.ijse.springposapi.exception.OrderNotFoundException;
import lk.ijse.springposapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDTO orderDTO) {
        try {
            orderService.saveOrder(orderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataPersistFailedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse getSelectedOrder(@PathVariable("id") Integer id) {
        return orderService.getSelectedOrder(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
