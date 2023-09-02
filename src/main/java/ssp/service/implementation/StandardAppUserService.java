package ssp.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ssp.dto.AppUserDto;
import ssp.model.AppUser;
import ssp.model.AppUserImage;
import ssp.model.Comment;
import ssp.model.Like;
import ssp.repository.AppUserRepository;
import ssp.service.AppUserService;

@Service
public class StandardAppUserService implements AppUserService {

	@Autowired
	AppUserRepository appUserRepository;

	@Override
	public ResponseEntity<?> getAllAppUsers() {
		
		List<AppUserDto> appUsers = this.appUserRepository.getAllAppUsers();
		
		if(appUsers != null) {
			return new ResponseEntity<List<AppUserDto>>(appUsers, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}

	@Override
	public ResponseEntity<?> getAppUserById(Long id) {
		
		AppUserDto appUserDto = this.appUserRepository.getAppUserById(id);
		
		if(appUserDto != null) {
			return new ResponseEntity<AppUserDto>(appUserDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("AppUser with given ID does not exists.", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> createAppUser(AppUser appUser) {
		
		AppUser appUser2 = this.appUserRepository.createAppUser(appUser);
		return new ResponseEntity<AppUser>(appUser2, HttpStatus.CREATED);
	
	}

	@Override
	public ResponseEntity<?> updateAppUserById(Long appUserId, AppUser appUser) {
		
		int res = this.appUserRepository.updateAppUserById(appUserId, appUser);
		
		if(res != 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("AppUser with given id does not exists", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> addImageToAppUser(AppUserImage image) {
		
		int res = this.appUserRepository.checkIfAppUserImageExists(image.getAppUserId());
		
		if(res == 1) {
			int res1 = this.appUserRepository.deleteAppUserImage(image.getAppUserId());
			
			int res2 = this.appUserRepository.addImageToAppUser(image);
			
			if(res2 != 0) {
				return new ResponseEntity<AppUserImage>(image, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}else {
			
			int res2 = this.appUserRepository.addImageToAppUser(image);
			
			if(res2 != 0) {
				return new ResponseEntity<AppUserImage>(image, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
	}

	@Override
	public ResponseEntity<?> deleteAppUserById(Long appUserId) {
		int res = this.appUserRepository.deleteAppUserById(appUserId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<?> deleteAppUserImage(Long appUserId) {
		int res = this.appUserRepository.deleteAppUserImage(appUserId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<?> likeRecepie(Like like) {
		Like like1 = this.appUserRepository.likeRecepie(like);
		
		if(like1 != null) {
			return new ResponseEntity<Like>(like1, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> unlikeRecepie(Like like) {
		int res = this.appUserRepository.unlikeRecepie(like);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<?> addComment(Comment comment) {
		Comment comment2 = this.appUserRepository.addComment(comment);
		if(comment2 != null) {
			return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> deleteComment(Comment comment) {
		int res = this.appUserRepository.deleteComment(comment);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
