package banking.transactions.domain.hibernate.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.transactions.domain.entity.RequestBankTransfer;
import banking.transactions.domain.repository.RequestBankTransferRepository;


@Transactional
@Repository
public class RequestBankTransferHibernateRepository extends BaseHibernateRepository<RequestBankTransfer>
implements RequestBankTransferRepository{
	public RequestBankTransfer save(RequestBankTransfer requestBankTransfer) {
		return super.save(requestBankTransfer);

	}
}
