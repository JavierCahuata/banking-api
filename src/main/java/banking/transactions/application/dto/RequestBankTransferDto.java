package banking.transactions.application.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import banking.transactions.application.dto.deserializer.RequestBankTransferDtoDeserializer;

@JsonDeserialize(using = RequestBankTransferDtoDeserializer.class)
public class RequestBankTransferDto {
	private long id;
	private String fromAccountNumber;
	private String toAccountNumber;
	private BigDecimal amount;
	private Date date;
	
	public RequestBankTransferDto() {
	}
	
	public RequestBankTransferDto(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.amount = amount;
		this.date = new Date();
	}

	public String getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
