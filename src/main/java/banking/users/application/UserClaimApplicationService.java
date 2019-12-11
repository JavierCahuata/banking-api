package banking.users.application;

//import java.util.List;

import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.modelmapper.config.Configuration.AccessLevel;
//import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.users.application.dto.UserClaimDto;
import banking.users.domain.entity.User;
import banking.users.domain.entity.UserClaim;
import banking.users.domain.repository.UserClaimRepository;
import banking.users.domain.repository.UserRepository;

@Service
public class UserClaimApplicationService {
	@Autowired
	UserClaimRepository userClaimRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
    
	public UserClaimDto create(long userId, UserClaimDto userClaimDto) throws Exception {
		/*Notification notification = this.createValidation(userClaimDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }*/
		UserClaim userClaim = mapper.map(userClaimDto, UserClaim.class);
		//userClaim.setIsLocked(false);
		User user = userRepository.getById(userId);
		userClaim.setUser(user);
		userClaim = userClaimRepository.save(userClaim);
		userClaimDto = mapper.map(userClaim, UserClaimDto.class);
        return userClaimDto;
    }
	/*
	private Notification createValidation(UserClaimDto UserClaimDto) throws Exception {
		Notification notification = new Notification();
		UserClaim UserClaim = UserClaimRepository.findByNumber(UserClaimDto.getNumber());
		if (UserClaim != null) {
			notification.addError("UserClaim number is already registered");
		}
		return notification;
	}
	*/
	/*
	public List<UserClaimDto> get(long userId) throws Exception {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
	      .setFieldMatchingEnabled(true)
	      .setFieldAccessLevel(AccessLevel.PRIVATE)
	      .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
		List<UserClaim> userClaims = this.userClaimRepository.findByUserId(userId);
		List<UserClaimDto> userClaimsDto = modelMapper.map(userClaims, new TypeToken<List<UserClaimDto>>() {}.getType());
        return userClaimsDto;
    }*/
}
