package ssp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recepie {

	private Long id;
	
	private Long categoryId;
	
	private Long countryId;
	
	private Long appUserId;
	
	private String name;
	
	private Integer prepTime;
	
	private String ingredients;
	
	private String text;

	
	
}
