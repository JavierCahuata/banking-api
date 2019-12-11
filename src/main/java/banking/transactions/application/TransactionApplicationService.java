package banking.transactions.application;

import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.common.infrastructure.security.Hashing;
import banking.transactions.application.dto.RequestBankTransferDto;
import banking.transactions.domain.entity.RequestBankTransfer;
import banking.transactions.domain.entity.Transaction;
import banking.transactions.domain.repository.RequestBankTransferRepository;
import banking.transactions.domain.service.TransferDomainService;
import banking.users.application.dto.UserDto;
import banking.users.domain.entity.User;

@Service
public class TransactionApplicationService {

	@Autowired
	private TransferDomainService transferDomainService;

	@Value("${mensaje.invalidJSON}")
	private String invalidJSON;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RequestBankTransferRepository requestBankTransferRepository;
	
	@Transactional
	public RequestBankTransferDto performTransfer(RequestBankTransferDto requestBankTransferDto) throws Exception {
		System.out.println("primer print ************RequestBankTransferDto*******************");
		Notification notification = this.validation(requestBankTransferDto);
        if (notification.hasErrors()) {
        	System.out.println("primer print ************IllegalArgumentException*******************");
            throw new IllegalArgumentException(notification.errorMessage());
            
        }
       
		RequestBankTransfer requestBankTransfer = mapper.map(requestBankTransferDto, RequestBankTransfer.class);
		requestBankTransfer = requestBankTransferRepository.save(requestBankTransfer);
		this.transferDomainService.performTransfer(requestBankTransfer);
		requestBankTransferDto = mapper.map(requestBankTransfer, RequestBankTransferDto.class);
        return requestBankTransferDto;
	}
	
	private Notification validation(RequestBankTransferDto requestBankTransferDto) {
		Notification notification = new Notification();
		if (requestBankTransferDto == null || requestBankTransferDto.getFromAccountNumber().equals(RequestBodyType.INVALID.toString())) {
			//notification.addError("Invalid JSON data in request body.");
			notification.addError(invalidJSON);
		}
		return notification;
	}
}
