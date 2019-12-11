package banking.persons.infrastructure.hibernate.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.persons.domain.entity.Person;
import banking.persons.domain.repository.PersonRepository;

@Transactional
@Repository
public class PersonHibernateRepository extends BaseHibernateRepository<Person> implements PersonRepository {
	public Person get(long personId) {
		Person customer = null;
		customer = getSession().get(Person.class, personId);
		return customer;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> get(int page, int pageSize) {
		List<Person> persons = null;
		Criteria criteria = getSession().createCriteria(Person.class);
		criteria.setFirstResult(page);
		criteria.setMaxResults(pageSize);
		persons = criteria.list();
		return persons;
	}
	
	public Person save(Person person) {
		return super.save(person);
	}
}
