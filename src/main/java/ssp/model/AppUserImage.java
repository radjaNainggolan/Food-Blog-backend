package ssp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserImage {

	private Long id;
	
	private Long appUserId;
	
	private byte[] image;
	
}
