package ssp.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssp.controller.TestController;
import ssp.dto.ImageDto;
import ssp.model.AppUserImage;
import ssp.repository.AppUserRepository;

@RestController
@RequestMapping("/test")
public class StandardTestController implements TestController {

	@Autowired
	AppUserRepository appUserRepository;
	
	@PostMapping("/user/add/image")
	@Override
	public int addAppUserImage(@RequestBody AppUserImage image) {
		return appUserRepository.addImageToAppUser(image);
	}

	@GetMapping("/user/image/{appUserId}")
	@Override
	public ImageDto getAppUserImage(@PathVariable("appUserId") Long appUserId) {
		return appUserRepository.getAppUserImage(appUserId);
	}

	@DeleteMapping("/user/image/{appUserId}")
	public int deleteAppUserImage(@PathVariable("appUserId") Long appUserId) {
		return appUserRepository.deleteAppUserImage(appUserId);
	}
	
	@GetMapping("/user/number/{appUserId}")
	@Override
	public int getAppUserNumber(@PathVariable("appUserId") Long appUserId) {
		return appUserRepository.numberOfAppUsersById(appUserId);
	}

}
