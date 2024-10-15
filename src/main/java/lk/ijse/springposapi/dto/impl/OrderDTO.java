package lk.ijse.springposapi.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lk.ijse.springposapi.customObj.OrderResponse;
import lk.ijse.springposapi.dto.SuperDTO;
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
    @NotBlank(message = "Date is mandatory")
    private String date;
    @NotBlank(message = "CustomerId is mandatory")
    private String customerId;
    @NotEmpty(message = "Item list can not be empty")
    private List<ItemDTO> items = new ArrayList<>();
    @NotBlank(message = "Total is mandatory")
    @Positive(message = "Total must be positive")
    @Size(min = 1, max = 10, message = "Total must be between 1 and 10 digits")
    private String total;
    @NotBlank(message = "Discount is mandatory")
    @Size(min = 1, max = 8, message = "Discount must be between 1 and 8 digits")
    private String discount;
    @NotBlank(message = "SubTotal is mandatory")
    @Positive(message = "SubTotal must be positive")
    @Size(min = 1, max = 8, message = "SubTotal must be between 1 and 8 digits")
    private String subTotal;
    @NotBlank(message = "Cash is mandatory")
    @Positive(message = "Cash must be positive")
    @Size(min = 1, max = 10, message = "Cash must be between 1 and 10 digits")
    private String cash;
    @NotBlank(message = "Balance is mandatory")
    @Positive(message = "Balance must be positive")
    @Size(min = 1, max = 8, message = "Balance must be between 1 and 8 digits")
    private String balance;
}
