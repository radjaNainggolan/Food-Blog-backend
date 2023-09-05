package ssp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserShortInfo {

	private Long id;
	
	private String userName;
	
	private byte[] image;
	
	
}
