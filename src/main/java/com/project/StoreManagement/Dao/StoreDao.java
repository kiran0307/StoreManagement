package com.project.StoreManagement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.StoreManagement.Model.StoreMgt;

public interface StoreDao extends JpaRepository <StoreMgt, Integer>{

}
