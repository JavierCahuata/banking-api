package banking.users.domain.repository;

import java.util.List;

import banking.users.domain.entity.UserClaim;

public interface UserClaimRepository {
	public List<UserClaim> findByUserId(long userId) throws Exception;
	//delete comment JCP
	//List<UserClaim> get(long userId);
	UserClaim persist(UserClaim userClaim);
	UserClaim save(UserClaim userClaim);
}
