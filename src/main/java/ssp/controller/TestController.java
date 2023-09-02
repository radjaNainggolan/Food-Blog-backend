package ssp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ssp.dto.ImageDto;
import ssp.model.AppUserImage;

public interface TestController {

	public int addAppUserImage(@RequestBody AppUserImage appUserImage);
	
	public ImageDto getAppUserImage(@PathVariable("id") Long appUserId);
	
	public int getAppUserNumber(@PathVariable("appUserId") Long appUserId);
}
