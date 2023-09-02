package ssp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecepiesShortInfo {

	private Long id;
	
	private String name;
	
	private byte[] image;
	
	private Integer numberOfLikes;
}
