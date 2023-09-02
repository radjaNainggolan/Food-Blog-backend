package ssp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

	private Long id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String password;

	

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + "]";
	}

	
	
	
	
}
