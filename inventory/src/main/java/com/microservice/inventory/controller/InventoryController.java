package com.microservice.inventory.controller;

import com.microservice.inventory.dto.InventoryDTO;
import com.microservice.inventory.model.Inventory;
import com.microservice.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/additem")
    public InventoryDTO saveItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.saveItem(inventoryDTO);
    }

    @GetMapping("/getitems")
    public List<InventoryDTO> getItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/item/{itemId}")
    public InventoryDTO getItemById(@PathVariable int itemId) {
        return inventoryService.getItemById(itemId);
    }

    @PutMapping("/updateitem")
    public InventoryDTO updateItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateItem(inventoryDTO);
    }
    @DeleteMapping("/deleteitem/{itemId}")
    public String deleteItem(@PathVariable Integer itemId) {
        return inventoryService.deleteItem(itemId);
    }


}
