package lk.ijse.springposapi.controller;

import jakarta.validation.Valid;
import lk.ijse.springposapi.customObj.ItemResponse;
import lk.ijse.springposapi.dto.impl.ItemDTO;
import lk.ijse.springposapi.exception.DataPersistFailedException;
import lk.ijse.springposapi.exception.ItemNotFoundException;
import lk.ijse.springposapi.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ItemController {
    private final ItemService itemService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@Valid @RequestBody ItemDTO itemDTO) {
        logger.info("Received request to save item: {}", itemDTO);
        try {
            itemService.saveItem(itemDTO);
            logger.info("Item saved successfully: {}", itemDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataPersistFailedException e) {
            logger.error("Data persist failed for item: {}", itemDTO, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Error saving item: {}", itemDTO, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemResponse getSelectedItem(@PathVariable("id") int id) {
        logger.info("Received request to get item with ID: {}", id);
        try {
            ItemResponse itemResponse = itemService.getSelectedItem(id);
            logger.info("Successfully retrieved item response with ID: {}", id);
            return ResponseEntity.status(HttpStatus.OK).body(itemResponse).getBody();
        } catch (ItemNotFoundException e) {
            logger.error("Item not found with ID: {}", id, e);
            return (ItemResponse) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving item with ID: {}", id, e);
            return (ItemResponse) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        logger.info("Received request to get all items");
        try {
            List<ItemDTO> items = itemService.getAllItems();
            logger.info("Successfully retrieved all items");
            return ResponseEntity.status(HttpStatus.OK).body(items);
        } catch (ItemNotFoundException e) {
            logger.error("No items found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error retrieving all items", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@PathVariable("id") int id, @Valid @RequestBody ItemDTO itemDTO) {
        logger.info("Received request to update item with ID: {}", id);
        try {
            itemService.updateItem(id, itemDTO);
            logger.info("Item updated successfully with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ItemNotFoundException e) {
            logger.error("Item not found with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataPersistFailedException e) {
            logger.error("Data persist failed for item with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Error updating item with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") int id) {
        logger.info("Received request to delete item with ID: {}", id);
        try {
            itemService.deleteItem(id);
            logger.info("Item deleted successfully with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalStateException e) {
            logger.error("Cannot delete item with ID: {} as it is part of an existing order", id, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (ItemNotFoundException e) {
            logger.error("Item not found with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error deleting item with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
