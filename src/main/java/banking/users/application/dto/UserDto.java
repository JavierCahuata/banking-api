package banking.users.application.dto;

import java.util.Set;

import banking.persons.domain.entity.Person;

//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

//import banking.users.application.dto.deserializer.UserDtoDeserializer;

//@JsonDeserialize(using = UserDtoDeserializer.class)
public class UserDto {
	private long id;
	private String name;
	private String password;
	private Set<UserClaimDto> claims;
	private long person_id;
	
	public UserDto() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
/*
	public UserDto(String name, String password) {
		this.name = name;
		this.password = password;
	}
*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserClaimDto> getClaims() {
		return claims;
	}

	public void setClaims(Set<UserClaimDto> claims) {
		this.claims = claims;
	}


	public long getPerson_id() {
		return person_id;
	}

	public void setPerson_id(long person_id) {
		this.person_id = person_id;
	}
}
