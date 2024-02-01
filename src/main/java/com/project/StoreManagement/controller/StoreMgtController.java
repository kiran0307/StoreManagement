package com.project.StoreManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.StoreManagement.Model.StoreMgt;
import com.project.StoreManagement.Services.StoreService;

@RestController
@RequestMapping("/store")
public class StoreMgtController {

    @Autowired
    private StoreService storeservice;

    @GetMapping
    public ResponseEntity<?> getAlllist() {
        try {
            List<StoreMgt> storeList = this.storeservice.getAlllist();
            return ResponseEntity.ok(storeList);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving store records: " + e.getMessage());
        }
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<?> getbyId(@PathVariable String stockId) {
        try {
            StoreMgt storeRecord = this.storeservice.getStoreRecord(Integer.parseInt(stockId));
            if (storeRecord != null) {
                return ResponseEntity.ok(storeRecord);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            // Handle the exception if the stockId is not a valid number
            return ResponseEntity.badRequest().body("Invalid stockId format: " + stockId);
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(500).body("Error retrieving store record: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStockOrStocks(@RequestBody List<StoreMgt> storerecords) {
        try {
            if (storerecords.isEmpty()) {
                return ResponseEntity.badRequest().body("Store list cannot be empty");
            }

            if (storerecords.size() == 1) {
                StoreMgt addedStorerecord = storeservice.addStoreRecord(storerecords.get(0));
                return ResponseEntity.ok(addedStorerecord);
            } else {
                List<StoreMgt> addedStorerecords = storeservice.addStoreRecords(storerecords);
                return ResponseEntity.ok(addedStorerecords);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding store record: " + e.getMessage());
        }
    }
}
