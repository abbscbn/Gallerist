package com.abbascoban.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoGalleristCarUI {
	
	@NotNull
	private Long galleristId;
	
	@NotNull
	private Long caId;
	
	

}
