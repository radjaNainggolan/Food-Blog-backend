package ssp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {

	private Long id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String password;
	
	private ImageDto image;
	
	private List<RecepiesShortInfo> likedRecepies;
	
	private List<RecepiesShortInfo> recepies;
	
}
