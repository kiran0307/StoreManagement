package com.project.StoreManagement.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.project.StoreManagement.Dao.StoreDao;
import com.project.StoreManagement.Model.StoreMgt;

@Service
public class StoreServiceImplement implements StoreService {

    @Autowired
    private StoreDao storedao;

    @Override
    public List<StoreMgt> getAlllist() {
        try {
            return storedao.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving store records: " + e.getMessage(), e);
        }
    }

    @Override
    public StoreMgt getStoreRecord(int stockId) {
        try {
            return storedao.findById(stockId).orElse(null);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving store record: " + e.getMessage(), e);
        }
    }

    @Override
    public StoreMgt addStoreRecord(StoreMgt store) {
        try {
            storedao.save(store);
            return store;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error adding store record: " + e.getMessage(), e);
        }
    }

    @Override
    public List<StoreMgt> addStoreRecords(List<StoreMgt> store) {
        try {
            List<StoreMgt> savedstocks = storedao.saveAll(store);
            return savedstocks;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error adding store records: " + e.getMessage(), e);
        }
    }
}
