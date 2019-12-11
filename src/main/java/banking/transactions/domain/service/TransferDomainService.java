package banking.transactions.domain.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.application.Notification;
import banking.transactions.application.dto.RequestBankTransferDto;
import banking.transactions.domain.entity.RequestBankTransfer;
import banking.transactions.domain.entity.Transaction;
import banking.transactions.domain.repository.RequestBankTransferRepository;

@Service
public class TransferDomainService {
	@Autowired
	private BankAccountRepository bankAccountRepository;
		
	@Autowired
	RequestBankTransferRepository requestBankTransferRepository;
	
	//public Transaction performTransfer(BankAccount originAccount, BankAccount destinationAccount, BigDecimal amount)
	public RequestBankTransfer performTransfer(RequestBankTransfer requestBankTransfer)
			throws Exception {
				
		        BankAccount originAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransfer.getFromAccountNumber());
				BankAccount destinationAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransfer.getToAccountNumber());
				
				//this.transferDomainService.performTransfer(requestBankTransfer);
				this.bankAccountRepository.save(originAccount);
				this.bankAccountRepository.save(destinationAccount);
		
		Notification notification = this.validation(originAccount, destinationAccount, requestBankTransfer.getAmount());
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		originAccount.withdrawMoney(requestBankTransfer.getAmount());
		destinationAccount.depositMoney(requestBankTransfer.getAmount());
		this.requestBankTransferRepository.save(requestBankTransfer);
		/*
		Transaction RequestBankTransfer = new requestBankTransfer( originAccount,  destinationAccount, amount, new Date());
		Transaction transaction_2 = requestBankTransferRepository.save(transaction);*/
		return requestBankTransfer;

	}
	
	private Notification validation(BankAccount originAccount, BankAccount destinationAccount, BigDecimal amount) {
        Notification notification = new Notification();
        this.validateAmount(notification, amount);
        this.validateBankAccounts(notification, originAccount, destinationAccount);
        return notification;
    }
    
    private void validateAmount(Notification notification, BigDecimal amount) {
        if (amount == null) {
            notification.addError("amount is missing");
            return;
        }
        if (amount.signum() <= 0) {
            notification.addError("The amount must be greater than zero");
        }
    }
    
    private void validateBankAccounts(Notification notification, BankAccount originAccount, BankAccount destinationAccount) {
        if (originAccount == null || destinationAccount == null) {
            notification.addError("Cannot perform the transfer. Invalid data in bank accounts specifications");
            return;
        }
        if (originAccount.getNumber().equals(destinationAccount.getNumber())) {
            notification.addError("Cannot transfer money to the same bank account");
        }
    }
}
