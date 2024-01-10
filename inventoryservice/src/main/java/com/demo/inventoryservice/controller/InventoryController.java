package com.demo.inventoryservice.controller;

import com.demo.inventoryservice.dto.InventoryResponse;
import com.demo.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isStock(@RequestParam List<String> skucode){
        return inventoryService.isStock(skucode);
    }

}
