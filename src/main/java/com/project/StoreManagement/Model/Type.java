package com.project.StoreManagement.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data

public class Type {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int typeId;
	private String typeName;
	
}
