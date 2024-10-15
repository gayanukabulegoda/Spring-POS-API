package lk.ijse.springposapi.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.springposapi.customObj.ItemResponse;
import lk.ijse.springposapi.customObj.impl.ItemErrorResponse;
import lk.ijse.springposapi.dto.impl.ItemDTO;
import lk.ijse.springposapi.entity.impl.Item;
import lk.ijse.springposapi.exception.ItemNotFoundException;
import lk.ijse.springposapi.repository.ItemRepository;
import lk.ijse.springposapi.repository.OrderDetailRepository;
import lk.ijse.springposapi.service.ItemService;
import lk.ijse.springposapi.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final Mapping mapping;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemRepository.save(mapping.convertToEntity(itemDTO, Item.class));
    }

    @Override
    @Transactional
    public void updateItem(int id, ItemDTO itemDTO) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        item.setName(itemDTO.getName());
        item.setQty(Integer.parseInt(itemDTO.getQty()));
        item.setPrice(Double.parseDouble(itemDTO.getPrice()));
    }

    @Override
    public void deleteItem(int id) {
        if (orderDetailRepository.existsByItemId(id)) {
            throw new IllegalStateException("Cannot delete item as it is part of an existing order.");
        }
        itemRepository.deleteById(id);
    }

    @Override
    public ItemResponse getSelectedItem(int id) {
        Optional<Item> byId = itemRepository.findById(id);
        return (byId.isPresent())
                ? mapping.convertToDTO(byId.get(), ItemDTO.class)
                : new ItemErrorResponse(0, "Item not found");
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return mapping.convertToDTOList(itemRepository.findAll());
    }
}
