package lk.ijse.springposapi.dto.impl;

import lk.ijse.springposapi.customObj.CustomerResponse;
import lk.ijse.springposapi.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO implements SuperDTO, CustomerResponse {
    private String id;
    private String name;
    private String email;
    private String city;
    private String profilePic;
}
