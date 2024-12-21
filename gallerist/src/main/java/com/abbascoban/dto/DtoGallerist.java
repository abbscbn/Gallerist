package com.abbascoban.dto;

import com.abbascoban.model.Address;
import com.abbascoban.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DtoGallerist extends BaseEntity {
	
	
	private String firstName;
	
	
	private String lastName;
	
	
	private DtoAddress address;
	

}
