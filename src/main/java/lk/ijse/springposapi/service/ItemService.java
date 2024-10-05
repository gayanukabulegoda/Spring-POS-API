package lk.ijse.springposapi.service;

import lk.ijse.springposapi.customObj.ItemResponse;
import lk.ijse.springposapi.dto.impl.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(ItemDTO itemDTO);
    void deleteItem(String id);
    ItemResponse getSelectedItem(String id);
    List<ItemDTO> getAllItems();
}
