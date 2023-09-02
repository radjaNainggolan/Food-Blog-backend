package ssp.service;

import org.springframework.http.ResponseEntity;

import ssp.model.AppUser;
import ssp.model.AppUserImage;
import ssp.model.Comment;
import ssp.model.Like;

public interface AppUserService {

	public ResponseEntity<?> getAllAppUsers();

	public ResponseEntity<?> getAppUserById(Long id);
	
	public ResponseEntity<?> createAppUser(AppUser appUser);
	
	public ResponseEntity<?> updateAppUserById(Long appUserId, AppUser appUser);
	
	public ResponseEntity<?> addImageToAppUser(AppUserImage image);
	
	public ResponseEntity<?> deleteAppUserById(Long appUserId);
	
	public ResponseEntity<?> deleteAppUserImage(Long appUserId);
	
	public ResponseEntity<?> likeRecepie(Like like);
	
	public ResponseEntity<?> unlikeRecepie(Like like);
	
	public ResponseEntity<?> addComment(Comment comment);
	
	public ResponseEntity<?> deleteComment(Comment comment);
	
}
