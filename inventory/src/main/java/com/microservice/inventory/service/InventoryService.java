package com.microservice.inventory.service;

import com.microservice.inventory.dto.InventoryDTO;
import com.microservice.inventory.model.Inventory;
import com.microservice.inventory.repo.InventoryRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    public InventoryDTO saveItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public List<InventoryDTO> getAllItems() {
        List<Inventory>itemList = inventoryRepo.findAll();
        return modelMapper.map(itemList, new TypeToken<List<InventoryDTO>>(){}.getType());
    }

    public InventoryDTO getItemById(int itemId) {
        Inventory inventory = inventoryRepo.getItemById(itemId);
        return modelMapper.map(inventory, InventoryDTO.class);
    }

    public InventoryDTO updateItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public String deleteItem(Integer itemId) {
        inventoryRepo.deleteById(itemId);
        return "Item deleted";
    }




}
