package lk.ijse.springposapi.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springposapi.customObj.OrderResponse;
import lk.ijse.springposapi.customObj.impl.OrderErrorResponse;
import lk.ijse.springposapi.dto.impl.ItemDTO;
import lk.ijse.springposapi.dto.impl.OrderDTO;
import lk.ijse.springposapi.embedded.OrderDetailPK;
import lk.ijse.springposapi.entity.impl.Customer;
import lk.ijse.springposapi.entity.impl.Item;
import lk.ijse.springposapi.entity.impl.Order;
import lk.ijse.springposapi.entity.impl.OrderDetail;
import lk.ijse.springposapi.exception.CustomerNotFoundException;
import lk.ijse.springposapi.exception.ItemNotFoundException;
import lk.ijse.springposapi.repository.CustomerRepository;
import lk.ijse.springposapi.repository.ItemRepository;
import lk.ijse.springposapi.repository.OrderDetailRepository;
import lk.ijse.springposapi.repository.OrderRepository;
import lk.ijse.springposapi.service.OrderService;
import lk.ijse.springposapi.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final Mapping mapping;

    @Override
    @Transactional
    public void saveOrder(OrderDTO orderDTO) {
        Order order = mapping.convertToEntity(orderDTO, Order.class);

        Customer customer = customerRepository.findById(Integer.valueOf(orderDTO.getCustomerId())).orElse(null);
        if (customer == null) throw new CustomerNotFoundException("Customer not found with ID: " + orderDTO.getCustomerId());
        order.setCustomer(customer);
        // Save order
        orderRepository.save(order);

        for (ItemDTO itemDTO : orderDTO.getItems()) {
            // Save order detail
            orderDetailRepository.save(new OrderDetail(
                    new OrderDetailPK(order.getId(), Integer.parseInt(itemDTO.getId())),
                    Integer.parseInt(itemDTO.getQty()),
                    mapping.convertToEntity(itemDTO, Item.class),
                    order
            ));
            // Update item quantity
            Item item = itemRepository.findById(Integer.parseInt(itemDTO.getId())).orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + itemDTO.getId()));
            item.setQty(item.getQty() - Integer.parseInt(itemDTO.getQty()));
            itemRepository.save(item);
        }
    }

    @Override
    public OrderResponse getSelectedOrder(int id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isEmpty()) return new OrderErrorResponse(0, "Order not found");
        OrderDTO orderDTO = mapping.convertToDTO(byId.get(), OrderDTO.class);
        // Get all order details of the selected order
        List<OrderDetail> orderDetailsByOrder = orderDetailRepository.getAllByOrder(byId.get());
        List<ItemDTO> list = orderDetailsByOrder.stream().map(od -> new ItemDTO(
                String.valueOf(od.getItem().getId()),
                od.getItem().getName(),
                String.valueOf(od.getItem().getPrice()),
                String.valueOf(od.getQty())
        )).toList();
        orderDTO.setItems(list);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        return mapping.convertToDTOList(allOrders);
    }
}
