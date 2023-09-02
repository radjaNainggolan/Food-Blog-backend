package ssp.repository;

import java.util.List;

import ssp.dto.AppUserDto;
import ssp.dto.ImageDto;
import ssp.dto.RecepiesShortInfo;
import ssp.model.AppUser;
import ssp.model.AppUserImage;
import ssp.model.Comment;
import ssp.model.Like;

public interface AppUserRepository {
	
	public List<AppUserDto> getAllAppUsers();
	
	public AppUserDto getAppUserById(Long id);
	
	public ImageDto getAppUserImage(Long appUserId);
	
	public List<RecepiesShortInfo> getAppUserRecepies(Long appUserId);
	
	public List<RecepiesShortInfo> getAppUserLikedRecepies(Long appUserId);
	
	public AppUser createAppUser(AppUser appUser);
	
	public int updateAppUserById(Long appUserId, AppUser appUser);
	
	public int addImageToAppUser(AppUserImage image);
	
	public int deleteAppUserById(Long appUserId);
	
	public int deleteAppUserImage(Long appUserId);
	
	public Like likeRecepie(Like like);
	
	public int unlikeRecepie(Like like);
	
	public Comment addComment(Comment comment);
	
	public int deleteComment(Comment comment);
	
	public int checkIfAppUserImageExists(Long imageId);
	
	public int numberOfAppUsersById(Long appUserId);
	
	public int numberOfLikesOfRecepieById(Long recepieId);
	
	
	
}
