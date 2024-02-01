package com.project.StoreManagement.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data

public class Vendor {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int vendorId;
	private String vendorName;
	
}
