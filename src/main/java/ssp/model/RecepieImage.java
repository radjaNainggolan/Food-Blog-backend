package ssp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecepieImage {

	private Long id;
	
	private Long recepieId;
	
	private byte[] image;


	
}
