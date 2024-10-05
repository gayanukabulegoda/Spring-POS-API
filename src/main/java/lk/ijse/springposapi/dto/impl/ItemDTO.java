package lk.ijse.springposapi.dto.impl;

import lk.ijse.springposapi.customObj.ItemResponse;
import lk.ijse.springposapi.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO implements SuperDTO, ItemResponse {
    private String id;
    private String name;
    private String qty;
    private String price;
}
