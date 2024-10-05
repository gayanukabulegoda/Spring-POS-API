package lk.ijse.springposapi.customObj.impl;

import lk.ijse.springposapi.customObj.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemErrorResponse implements ItemResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
