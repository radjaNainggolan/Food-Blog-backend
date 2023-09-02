package ssp.repository.implementation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ssp.dto.AppUserDto;
import ssp.dto.ImageDto;
import ssp.dto.RecepiesShortInfo;
import ssp.dto.row_mapper.AppUserDtoRowMapper;
import ssp.dto.row_mapper.ImageDtoRowMapper;
import ssp.dto.row_mapper.RecepieShortInfoRowMapper;
import ssp.model.AppUser;
import ssp.model.AppUserImage;
import ssp.model.Comment;
import ssp.model.Like;
import ssp.repository.AppUserRepository;

@Repository
public class StandardAppUserRepository implements AppUserRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	ImageDtoRowMapper imageDtoRowMapper;
	
	@Autowired
	RecepieShortInfoRowMapper infoRowMapper;
	
	@Autowired
	AppUserDtoRowMapper appUserDtoRowMapper;
	
	private final String createUser = 	"""
										INSERT INTO app_user(
										email, first_name, last_name, password)
										VALUES (?, ?, ?, ?, ?);
										""";
	
	private final String getAppUserImage = """
			
											select i.app_user_id, i.image
											from
												app_user as u
												inner join
												app_user_image as i
												on u.id = i.app_user_id
											where u.id = ?;
														""";
	
	private final String checkIfImageExists = """
			SELECT COUNT(*) FROM app_user_image WHERE app_user_id = ?
			""";
	
	private final String deleteAppUserImage = """
			delete from app_user_image where app_user_id = ?;
			""";
	
	private final String numberOfAppUsersById = """
			SELECT COUNT(*) FROM app_user WHERE id = ?
			""";	
	
	private final String deleteComment = """
			DELETE FROM recepie_comment WHERE app_user_id = ? AND recepie_id = ?
			""";
	
	private final String insertComment = """
			INSERT INTO recepie_comment(app_user_id, recepie_id, text) VALUES (?,?,?)
			""";
	
	private final String unlikeRecepie = """
			DELETE FROM liked_recepies WHERE app_user_id = ? and recepie_id = ?
			""";
	
	private final String likeRecepie = """
			INSERT INTO liked_recepies(app_user_id, recepie_id) VALUES (?,?)
			""";
	
	private final String deleteAppUserById = """
			DELETE FROM app_user_id WHERE id = ?
			""";	
	
	private final String updateUserById = """
			UPDATE app_user_id set first_name = ?, last_name = ?,  
			""";
	
	private final String numberOfLikesOfRecepie = """
			
			SELECT COUNT(*) from liked_recepies WHERE recepie_id = ?
			""";
	
	private final String getAppUserLikedRecepies = """
			SELECT DISTINCT r.id,r.name,ri.image
			FROM
				recepie as r
				inner join
				recepie_image as ri
				on r.id = ri.recepie_id
				inner join
				liked_recepies as lr
				on lr.recepie_id = r.id
			WHERE lr.app_user_id = ?
			""";
	
	private final String getAppUserRecepies = """
			SELECT distinct r.id,r.name,ri.image
			FROM
				app_user as au
				inner join
				recepie as r
				on r.app_user_id = au.id
				inner join
				recepie_image as ri
				on ri.recepie_id = r.id
			WHERE au.id = ?
			""";
	
	private final String selectAppUser = """
			SELECT * FROM app_user WHERE id = ?
			""";
	
	@Override
	public List<AppUserDto> getAllAppUsers() {
		
		List<AppUserDto> list = jdbcTemplate.query(selectAppUser, appUserDtoRowMapper);
		
		for(AppUserDto appUserDto:list) {
			ImageDto imageDto = getAppUserImage(appUserDto.getId());
			List<RecepiesShortInfo> likedRecepies = getAppUserLikedRecepies(appUserDto.getId());
			List<RecepiesShortInfo> appUserRecepies = getAppUserRecepies(appUserDto.getId());
			
			appUserDto.setImage(imageDto);
			appUserDto.setLikedRecepies(likedRecepies);
			appUserDto.setRecepies(appUserRecepies);
		}
		
		return list;
	}

	@Override
	public AppUserDto getAppUserById(Long id) {
		
		int res = numberOfAppUsersById(id);
		
		if(res != 0) {
			ImageDto imageDto = getAppUserImage(id);
			List<RecepiesShortInfo> likedRecepies = getAppUserLikedRecepies(id);
			List<RecepiesShortInfo> appUserRecepies = getAppUserRecepies(id);
			
			AppUserDto appUserDto = jdbcTemplate.queryForObject(selectAppUser, appUserDtoRowMapper, id);
			appUserDto.setImage(imageDto);
			appUserDto.setLikedRecepies(likedRecepies);
			appUserDto.setRecepies(appUserRecepies);
			return appUserDto;
		}else {
			return null;
		}
		
	}

	@Override
	public ImageDto getAppUserImage(Long appUserId) {			
		ImageDto imageDto = jdbcTemplate.queryForObject(getAppUserImage, imageDtoRowMapper, appUserId);
		return imageDto;
	}

	@Override
	public List<RecepiesShortInfo> getAppUserRecepies(Long appUserId) {
		List<RecepiesShortInfo> shortInfos = jdbcTemplate.query(getAppUserRecepies,infoRowMapper, appUserId);
		
		for(RecepiesShortInfo info: shortInfos) {
			int res = numberOfLikesOfRecepieById(info.getId());
			info.setNumberOfLikes(res);
		}
	
		return shortInfos;
	}

	@Override
	public List<RecepiesShortInfo> getAppUserLikedRecepies(Long appUserId) {
		
		List<RecepiesShortInfo> shortInfos = jdbcTemplate.query(getAppUserLikedRecepies,infoRowMapper, appUserId);
		
		for(RecepiesShortInfo info: shortInfos) {
			int res = numberOfLikesOfRecepieById(info.getId());
			info.setNumberOfLikes(res);
		}
	
		return shortInfos;
		
	}

	@Override
	public AppUser createAppUser(AppUser appUser) {
		
		int res = jdbcTemplate.update(createUser, 
				appUser.getEmail(), 
				appUser.getFirstName(),
				appUser.getLastName(),
				appUser.getPassword(),
				appUser.getUserName()
				);
		
		
		final String query = "SELECT * FROM app_user WHERE email = ? ";
		 
		AppUserDto appUserDto = jdbcTemplate.queryForObject(query, appUserDtoRowMapper, appUser.getEmail());
		appUser.setId(appUserDto.getId());
		
		return appUser;
	
	}

	@Override
	public int updateAppUserById(Long appUserId, AppUser appUser) {
		int res = numberOfAppUsersById(appUserId);
		
		if(res != 0) {
			int res1 = jdbcTemplate.update(updateUserById,appUser.getFirstName());
			return res1;
		}else {
			return 0;
		}
	}

	@Override
	public int addImageToAppUser(AppUserImage image) {
		
		//byte[] image2 = image();
		int res = jdbcTemplate.update("INSERT INTO APP_USER_IMAGE(image,app_user_id) VALUES (?,?)",
				image.getImage(), image.getAppUserId());
		return res;
	}

	@Override
	public int deleteAppUserById(Long appUserId) {
		int res = jdbcTemplate.update(deleteAppUserById, appUserId);
		return res;
	}

	@Override
	public int deleteAppUserImage(Long appUserId) {
		int res = jdbcTemplate.update(deleteAppUserImage, appUserId);
		return res;
	}

	@Override
	public Like likeRecepie(Like like) {
		int res = jdbcTemplate.update(likeRecepie, like.getAppUserId(), like.getRecepieId());
		if(res != 0) {
			return like;
		}else {
			return null;
		}
	}

	@Override
	public int unlikeRecepie(Like like) {
		int res = jdbcTemplate.update(unlikeRecepie, like.getAppUserId(), like.getRecepieId());
		return res;
	}

	@Override
	public Comment addComment(Comment comment) {
		int res = jdbcTemplate.update(insertComment,comment.getAppUserId(), comment.getRecepieId(), comment.getText());
		
		if(res != 0) {			
			return comment;
		}else {
			return null;
		}
	}

	@Override
	public int deleteComment(Comment comment) {
		int res = jdbcTemplate.update(deleteComment, comment.getAppUserId(), comment.getRecepieId());
		return res;
	}

	private byte[] image() {
		FileInputStream fileInputStream = null;
		try {
			 fileInputStream = new FileInputStream("C:\\Users\\Legion\\klin.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte [] image = null;
		try {
			image = fileInputStream.readAllBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}
	
	public int checkIfAppUserImageExists(Long id) {
		return jdbcTemplate.queryForObject(checkIfImageExists, Integer.class, id);
	}

	@Override
	public int numberOfAppUsersById(Long appUserId) {
		return jdbcTemplate.queryForObject(numberOfAppUsersById, Integer.class, appUserId);
	}

	@Override
	public int numberOfLikesOfRecepieById(Long recepieId) {
		return jdbcTemplate.queryForObject(numberOfLikesOfRecepie, Integer.class, recepieId);
	}
}
