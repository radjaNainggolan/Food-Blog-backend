package ssp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ssp.model.AppUser;
import ssp.model.AppUserImage;
import ssp.model.Comment;
import ssp.model.Like;

public interface AppUserController {

	public ResponseEntity<?> getAllAppUsers();

	public ResponseEntity<?> getAppUserById(@PathVariable("appUserId") Long appUserId);
	
	public ResponseEntity<?> createAppUser(@RequestBody AppUser appUser);
	
	public ResponseEntity<?> updateAppUserById(@PathVariable("appUserId") Long appUserId, @RequestBody  AppUser appUser);
	
	public ResponseEntity<?> addImageToAppUser(@RequestBody AppUserImage image);
	
	public ResponseEntity<?> deleteAppUserById(@PathVariable("appUserId") Long appUserId);
	
	public ResponseEntity<?> deleteAppUserImage(@PathVariable("appUserId") Long appUserId);
	
	public ResponseEntity<?> likeRecepie(@RequestBody Like like);
	
	public ResponseEntity<?> unlikeRecepie(@RequestBody Like like);
	
	public ResponseEntity<?> addComment(@RequestBody Comment comment);
	
	public ResponseEntity<?> deleteComment(@RequestBody Comment comment);
	
}
