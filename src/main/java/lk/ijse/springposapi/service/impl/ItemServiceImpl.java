package lk.ijse.springposapi.service.impl;

import lk.ijse.springposapi.customObj.ItemResponse;
import lk.ijse.springposapi.customObj.impl.ItemErrorResponse;
import lk.ijse.springposapi.dto.impl.ItemDTO;
import lk.ijse.springposapi.entity.impl.Item;
import lk.ijse.springposapi.exception.ItemNotFoundException;
import lk.ijse.springposapi.repository.ItemRepository;
import lk.ijse.springposapi.service.ItemService;
import lk.ijse.springposapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final Mapping mapping;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, Mapping mapping) {
        this.itemRepository = itemRepository;
        this.mapping = mapping;
    }

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemRepository.save(mapping.convertToEntity(itemDTO, Item.class));
    }

    @Override
    @Transactional
    public void updateItem(ItemDTO itemDTO) {
        Item item = itemRepository.findById(Integer.valueOf(itemDTO.getId()))
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        item.setName(itemDTO.getName());
        item.setQty(Integer.parseInt(itemDTO.getQty()));
        item.setPrice(Double.parseDouble(itemDTO.getPrice()));
    }

    @Override
    public void deleteItem(String id) {
        itemRepository.deleteById(Integer.valueOf(id));
    }

    @Override
    public ItemResponse getSelectedItem(String id) {
        return (itemRepository.existsById(Integer.valueOf(id)))
                ? mapping.convertToDTO(itemRepository.getReferenceById(Integer.valueOf(id)), ItemDTO.class)
                : new ItemErrorResponse(0, "Item not found");
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return mapping.convertToDTOList(itemRepository.findAll());
    }
}
