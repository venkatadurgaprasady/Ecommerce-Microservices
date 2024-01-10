package com.demo.inventoryservice.service;

import com.demo.inventoryservice.dto.InventoryResponse;
import com.demo.inventoryservice.model.Inventory;
import com.demo.inventoryservice.repository.InventoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRespository inventoryRespository;
    @Transactional(readOnly = true)
    public List<InventoryResponse> isStock(List<String> skucode){
        return inventoryRespository.findBySkucodeIn(skucode).stream().map(this::maptoinventoryresponse).toList();
    }

    private InventoryResponse maptoinventoryresponse(Inventory inventory) {
         return InventoryResponse.builder().skucode(inventory.getSkucode())
                 .inStock(inventory.getQuantity()>0).build();
    }
}
