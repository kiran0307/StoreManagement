package com.project.StoreManagement.Services;

import java.util.List;

import com.project.StoreManagement.Model.StoreMgt;

public interface StoreService {
	public List<StoreMgt> getAlllist();
	public StoreMgt getStoreRecord(int stockId);
	public StoreMgt addStoreRecord(StoreMgt store);
	public List<StoreMgt> addStoreRecords(List<StoreMgt> store);

}
