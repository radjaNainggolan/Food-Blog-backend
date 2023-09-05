package ssp.repository;

import java.util.List;

import ssp.dto.AppUserShortInfo;
import ssp.dto.CommentDto;
import ssp.dto.ImageDto;
import ssp.dto.RecepieDto;
import ssp.model.Recepie;

public interface RecepieRepository {

	public Recepie createRecepie(Recepie recepie);
	
	public List<ImageDto> getRecepieImagesById(Long recepieId);
	
	public List<CommentDto> getRecepieCommentsById(Long recepieId);
	
	public AppUserShortInfo getAppUserShortInfo(Long recepieId);
	
	public List<RecepieDto> getAllRecepies();
	
	public RecepieDto getRecepieById(Long recepieId);
	
	public int updateRecepieById(Long recepieId, Recepie recepie);
	
	public int deleteRecepieById(Long recepieId);
	
	
}
