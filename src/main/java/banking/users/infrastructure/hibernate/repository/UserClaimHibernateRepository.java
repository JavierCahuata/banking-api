package banking.users.infrastructure.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
//import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.users.domain.entity.UserClaim;
import banking.users.domain.repository.UserClaimRepository;

@Transactional
@Repository
public class UserClaimHibernateRepository extends BaseHibernateRepository<UserClaim> implements UserClaimRepository {
	@SuppressWarnings("unchecked")
	public List<UserClaim> findByUserId(long userId) throws Exception {
		List<UserClaim> userClaims = null;
		Criteria criteria = getSession().createCriteria(UserClaim.class, "uc");
		criteria.createAlias("uc.user", "u");
		//criteria.setFetchMode("user", FetchMode.SELECT); 
		criteria.add(Restrictions.eq("u.id", userId));
		userClaims = criteria.list();
		return userClaims;
	}
	
	public UserClaim save(UserClaim userClaim) {
		return super.save(userClaim);
	}
	/*
	@SuppressWarnings("unchecked")
	@Override
	public List<UserClaim> get(long userId) {
		List<UserClaim> userClaims = null;
		Criteria criteria = getSession().createCriteria(UserClaim.class, "a");
		criteria.createAlias("a.user", "c");
		//criteria.setFetchMode("user", FetchMode.SELECT);
		criteria.add(Restrictions.eq("c.id", userId));
		userClaims = criteria.list();
		return userClaims;
	}*/
}
