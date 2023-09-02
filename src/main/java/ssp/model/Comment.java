package ssp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	private Long appUserId;
	
	private Long recepieId;
	
	private String text;
	
	
}
