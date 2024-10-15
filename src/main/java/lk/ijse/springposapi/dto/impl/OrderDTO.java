package lk.ijse.springposapi.dto.impl;

import lk.ijse.springposapi.customObj.OrderResponse;
import lk.ijse.springposapi.dto.SuperDTO;
import lk.ijse.springposapi.entity.impl.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO implements SuperDTO, OrderResponse {
    private String id;
    private String date;
    private String customerId;
    private List<ItemDTO> items = new ArrayList<>();
    private String total;
    private String discount;
    private String subTotal;
    private String cash;
    private String balance;
}
