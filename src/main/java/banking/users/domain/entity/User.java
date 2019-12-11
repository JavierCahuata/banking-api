package banking.users.domain.entity;

import java.util.Set;

import banking.persons.domain.entity.Person;

public class User {
	private long id;
    private String name;
    private String password;
    private Set<UserClaim> claims;
    private long person_id;
    
    public long getPerson_id() {
		return person_id;
	}

	public void setPerson_id(long person_id) {
		this.person_id = person_id;
	}

	public User() {
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Set<UserClaim> getClaims() {
		return claims;
	}

	public void setClaims(Set<UserClaim> claims) {
		this.claims = claims;
	}

}
