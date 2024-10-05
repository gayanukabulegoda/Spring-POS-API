package lk.ijse.springposapi.service;

import lk.ijse.springposapi.customObj.OrderResponse;
import lk.ijse.springposapi.dto.impl.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void saveOrder(OrderDTO orderDTO);
    OrderResponse getSelectedOrder(String id);
    List<OrderDTO> getAllOrders();
}
