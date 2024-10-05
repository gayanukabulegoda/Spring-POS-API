package lk.ijse.springposapi.service.impl;

import lk.ijse.springposapi.customObj.OrderResponse;
import lk.ijse.springposapi.customObj.impl.OrderErrorResponse;
import lk.ijse.springposapi.dto.impl.ItemDTO;
import lk.ijse.springposapi.dto.impl.OrderDTO;
import lk.ijse.springposapi.embedded.OrderDetailPK;
import lk.ijse.springposapi.entity.impl.Item;
import lk.ijse.springposapi.entity.impl.Order;
import lk.ijse.springposapi.entity.impl.OrderDetail;
import lk.ijse.springposapi.repository.OrderDetailRepository;
import lk.ijse.springposapi.repository.OrderRepository;
import lk.ijse.springposapi.service.OrderService;
import lk.ijse.springposapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final Mapping mapping;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, Mapping mapping) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.mapping = mapping;
    }

    @Override
    @Transactional
    public void saveOrder(OrderDTO orderDTO) {
        Order order = mapping.convertToEntity(orderDTO, Order.class);
        orderRepository.save(order);

        for (ItemDTO itemDTO : orderDTO.getItems()) {
            orderDetailRepository.save(new OrderDetail(
                    new OrderDetailPK(order.getId(), Integer.parseInt(itemDTO.getId())),
                    Integer.parseInt(itemDTO.getQty()),
                    mapping.convertToEntity(itemDTO, Item.class),
                    order
            ));
        }
    }

    @Override
    public OrderResponse getSelectedOrder(String id) {
        return (orderRepository.existsById(Integer.valueOf(id)))
                ? mapping.convertToDTO(orderRepository.getReferenceById(Integer.valueOf(id)), OrderDTO.class)
                : new OrderErrorResponse(0, "Order not found");
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return mapping.convertToDTOList(orderRepository.findAll());
    }
}
