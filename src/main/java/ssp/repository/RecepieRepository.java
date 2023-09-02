package ssp.repository;

import java.util.List;

import ssp.dto.CommentDto;
import ssp.dto.ImageDto;
import ssp.dto.RecepieDto;
import ssp.model.Recepie;

public interface RecepieRepository {

	public RecepieDto createRecepie(Recepie recepie);
	
	public List<ImageDto> getRecepieImagesById(Long id);
	
	public List<CommentDto> getRecepieCommentsById(Long id);
	
	public ImageDto getRecepieAppUserImage(Long id);
	
	public String getRecepieAppUserUserName(Long id);
	
	public List<RecepieDto> getAllRecepies();
	
	public RecepieDto getRecepieById(Long id);
	
	public int updateRecepieById(Long id);
	
	public int deleteRecepieById(Long id);
	
	
}
