package lk.ijse.springposapi.service;

import lk.ijse.springposapi.customObj.ItemResponse;
import lk.ijse.springposapi.dto.impl.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(int id, ItemDTO itemDTO);
    void deleteItem(int id);
    ItemResponse getSelectedItem(int id);
    List<ItemDTO> getAllItems();
}
