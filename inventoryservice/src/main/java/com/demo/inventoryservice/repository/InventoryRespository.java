package com.demo.inventoryservice.repository;

import com.demo.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRespository extends JpaRepository<Inventory,Long> {

    List<Inventory> findBySkucodeIn(List<String> skucode);
}
