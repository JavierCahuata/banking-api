package banking.users.application;

import java.util.List;
//import java.util.Set;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import banking.users.application.dto.UserDto;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.common.infrastructure.security.Hashing;
import banking.common.infrastructure.security.JwtTokenProvider;
import banking.users.application.dto.UserAuthDto;
import banking.users.application.dto.UserClaimDto;
import banking.users.domain.entity.User;
import banking.users.domain.entity.UserClaim;
import banking.users.domain.repository.UserClaimRepository;
import banking.users.domain.repository.UserRepository;

@Service
public class UserApplicationService {
	
	//@Autowired
	//private PersonRepository personRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserClaimRepository userClaimRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Value("${maxPageSize}")
	private int maxPageSize;
    
	public UserDto create(UserDto userDto) {
		Notification notification = this.createValidation(userDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        //person
        /*
        Person person = new Person();
        person.setFirstName(userDto.getPerson().getFirstName());
        person.setLastName(userDto.getPerson().getLastName());
        person.setDni(userDto.getPerson().getDni());
        person.setAddress(userDto.getPerson().getAddress());
        person.setPhone(userDto.getPerson().getPhone());
        
		person = personRepository.save(person);*/
        //user
		String hashPassword = Hashing.hash(userDto.getPassword());
		userDto.setPassword(hashPassword);
		
		User user = mapper.map(userDto, User.class);
		user = userRepository.save(user);
		/*User user = new User();
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setPerson_id(person.getId());
		
		Set<UserClaim> userClaims = user.getClaims();		
		user = userRepository.save(user);
		//userclaim
		if(!userClaims.isEmpty()) {
			UserClaim userClaim = new UserClaim();
			System.out.println("userClaims.iterator().next().getType()" + userClaims.iterator().next().getType());
			userClaim.setType(userClaims.iterator().next().getType());;
			userClaim.setValue(userClaims.iterator().next().getValue());
			userClaim.setUser(user);
			
			userClaim = userClaimRepository.save(userClaim);
		}*/
		userDto = mapper.map(user, UserDto.class);		
        return userDto;
    }
	
	private Notification createValidation(UserDto userDto) {
		Notification notification = new Notification();
		if (userDto == null || userDto.getName().equals(RequestBodyType.INVALID.toString())) {
			notification.addError("Invalid JSON data in request body.");
		}
		User user = userRepository.getByName(userDto.getName().trim());
		if (user != null) {
			notification.addError("User name is already registered");
		}
		return notification;
	}

	
	public UserAuthDto validateUser(UserDto userDto) throws Exception {
		UserAuthDto userAuthDto = new UserAuthDto();
		User authUser = null;
		authUser = this.userRepository.getByName(userDto.getName());
		if (authUser == null) {
			return userAuthDto;
		}		
		if (!Hashing.verifyHash(userDto.getPassword(), authUser.getPassword())) {
			return userAuthDto;
		}
		userAuthDto = this.buildUserAuthDto(authUser);
		return userAuthDto;
	}
	
	private List<UserClaimDto> getUserClaims(User authUser) throws Exception {
		List<UserClaim> claims = this.userClaimRepository.findByUserId(authUser.getId());
		return mapper.map(claims, new TypeToken<List<UserClaimDto>>() {}.getType());
	}
	
	private UserAuthDto buildUserAuthDto(User authUser) throws Exception {
		UserAuthDto userAuthDto = new UserAuthDto();
		userAuthDto.setId(authUser.getId());
		userAuthDto.setName(authUser.getName());
		userAuthDto.setAuthenticated(true);
		userAuthDto.setBearerToken(new UUID(0L, 0L).toString());
		List<UserClaimDto> claims = this.getUserClaims(authUser);
		userAuthDto.setClaims(claims);
		userAuthDto.setBearerToken(jwtTokenProvider.buildJwtToken(userAuthDto));
		return userAuthDto;
	}
	
	public UserDto get(long userId) {
		ModelMapper modelMapper = new ModelMapper();
		User user = this.userRepository.getById(userId);
		UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
    
    public List<UserDto> getPaginated(int page, int pageSize) {
		Notification notification = this.getPaginatedValidation(page, pageSize);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		List<User> users = this.userRepository.getPaginated(page, pageSize);
		List<UserDto> usersDto = mapper.map(users, new TypeToken<List<UserDto>>() {}.getType());
        return usersDto;
    }
    
    private Notification getPaginatedValidation(int page, int pageSize) {
		Notification notification = new Notification();
		if (pageSize > maxPageSize) {
			notification.addError("Page size can not be greater than 100");
		}
		return notification;
	}
}
