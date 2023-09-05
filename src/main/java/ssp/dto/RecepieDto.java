package ssp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecepieDto {

	private Long id;
	
	private String name;
	
	private Integer prepTime;
	
	private String ingredients;
	
	private String text;
	
	private Long countryId;
	
	private String countryName;
	
	private Long categoryId;
	
	private String categoryName;
	
	private List<ImageDto> images;
	
	private AppUserShortInfo appUser;
	
	private ImageDto appUserImage;
	
	private List<CommentDto> comments;
	
	
	
	
}
