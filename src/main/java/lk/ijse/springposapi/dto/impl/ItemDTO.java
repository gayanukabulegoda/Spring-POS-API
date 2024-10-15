package lk.ijse.springposapi.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 40 characters")
    private String name;
    @NotBlank(message = "Qty is mandatory")
    @Positive(message = "Qty must be a positive number")
    @Size(min = 1, max = 20, message = "Qty must be between 1 and 20 digits")
    private String qty;
    @NotBlank(message = "Price is mandatory")
    @Positive(message = "Price must be a positive number")
    @Size(min = 1, max = 10, message = "Price must be between 1 and 10 digits")
    private String price;
}
