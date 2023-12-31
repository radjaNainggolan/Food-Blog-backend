package ssp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	private String appUserUserName;
	
	private byte[] image;
	
	private String text;
	
}
