package lk.ijse.springposapi.customObj.impl;

import lk.ijse.springposapi.customObj.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderErrorResponse implements OrderResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
