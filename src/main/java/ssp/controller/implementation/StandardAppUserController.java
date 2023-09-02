package ssp.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssp.controller.AppUserController;
import ssp.model.AppUser;
import ssp.model.AppUserImage;
import ssp.model.Comment;
import ssp.model.Like;
import ssp.service.AppUserService;

@RestController
@RequestMapping("/api")
public class StandardAppUserController implements AppUserController {

	@Autowired
	AppUserService appUserService;
	
	@GetMapping("/appuser/all")
	@Override
	public ResponseEntity<?> getAllAppUsers() {
		return this.appUserService.getAllAppUsers();
	}

	@GetMapping("/get/appuser/{appUserId}")
	@Override
	public ResponseEntity<?> getAppUserById(@PathVariable("appUserId") Long appUserId) {
		return this.appUserService.getAppUserById(appUserId);
	}

	@PostMapping("/create/appuser")
	@Override
	public ResponseEntity<?> createAppUser(@RequestBody AppUser appUser) {
		return this.appUserService.createAppUser(appUser);
	}

	@PatchMapping("/update/appuser/{appUserId}")
	@Override
	public ResponseEntity<?> updateAppUserById(@PathVariable("appUserId") Long appUserId, @RequestBody AppUser appUser) {
		return this.appUserService.updateAppUserById(appUserId, appUser);
	}

	@PostMapping("/create/image/appuser")
	@Override
	public ResponseEntity<?> addImageToAppUser(@RequestBody AppUserImage image) {
		return this.appUserService.addImageToAppUser(image);
	}

	@DeleteMapping("/delete/appuser/{appUserId}")
	@Override
	public ResponseEntity<?> deleteAppUserById(@PathVariable("appUserId") Long appUserId) {
		return this.appUserService.deleteAppUserById(appUserId);
	}

	@DeleteMapping("/delete/image/appuser/{appUserId}")
	@Override
	public ResponseEntity<?> deleteAppUserImage(@PathVariable("appUserId") Long appUserId) {
		return this.appUserService.deleteAppUserImage(appUserId);
	}

	@PostMapping("/like/appuser")
	@Override
	public ResponseEntity<?> likeRecepie(@RequestBody Like like) {
		return this.appUserService.likeRecepie(like);
	}

	@DeleteMapping("/delete/like/appuser")
	@Override
	public ResponseEntity<?> unlikeRecepie(@RequestBody Like like) {
		return this.appUserService.unlikeRecepie(like);
	}

	@PostMapping("/comment/appuser")
	@Override
	public ResponseEntity<?> addComment(@RequestBody Comment comment) {
		return this.appUserService.addComment(comment);
	}

	@DeleteMapping("/delete/commment/appuser")
	@Override
	public ResponseEntity<?> deleteComment(@RequestBody Comment comment) {
		return this.appUserService.deleteComment(comment);
	}

}
