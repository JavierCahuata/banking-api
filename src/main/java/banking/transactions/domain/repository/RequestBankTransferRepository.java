package banking.transactions.domain.repository;

import banking.transactions.domain.entity.RequestBankTransfer;

public interface RequestBankTransferRepository {
	RequestBankTransfer save(RequestBankTransfer requestBankTransfer);
}
