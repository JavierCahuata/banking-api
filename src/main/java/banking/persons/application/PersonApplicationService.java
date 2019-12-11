package banking.persons.application;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import banking.common.application.Notification;
import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerRepository;
import banking.persons.domain.entity.Person;
import banking.persons.domain.repository.PersonRepository;

@Service
public class PersonApplicationService {
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Value("${maxPageSize}")
	private int maxPageSize;
    
	public Person create(Person person) {
		//Customer customer = mapper.map(customerDto, Customer.class);
		//customer.setIsActive(true);
		person = personRepository.save(person);
		//customerDto = mapper.map(customer, CustomerDto.class);
        return person;
    }
	
	public List<Person> get(int page, int pageSize) {
		Notification notification = this.getValidation(page, pageSize);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		List<Person> persons = this.personRepository.get(page, pageSize);
		List<Person> personsDto = mapper.map(persons, new TypeToken<List<Person>>() {}.getType());
        return personsDto;
    }
	
	private Notification getValidation(int page, int pageSize) {
		Notification notification = new Notification();
		if (pageSize > maxPageSize) {
			notification.addError("Page size can not be greater than 100");
		}
		return notification;
	}
}
