package banking.transactions.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import banking.accounts.domain.entity.BankAccount;

public class Transaction {
	private long id;
    private BankAccount bank_account_1;
    private BankAccount bank_account_2;
    private BigDecimal movimiento_1;
    private Date date;
   
    public Transaction(BankAccount bank_account_1, BankAccount bank_account_2, BigDecimal movimiento_1, Date date) {
		super();
		this.bank_account_1 = bank_account_1;
		this.bank_account_2 = bank_account_2;
		this.movimiento_1 = movimiento_1;
		this.date = date;
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
	public BankAccount getBank_account_1() {
		return bank_account_1;
	}
	public void setBank_account_1(BankAccount bank_account_1) {
		this.bank_account_1 = bank_account_1;
	}
	public BankAccount getBank_account_2() {
		return bank_account_2;
	}
	public void setBank_account_2(BankAccount bank_account_2) {
		this.bank_account_2 = bank_account_2;
	}
	public BigDecimal getMovimiento_1() {
		return movimiento_1;
	}
	public void setMovimiento_1(BigDecimal movimiento_1) {
		this.movimiento_1 = movimiento_1;
	}
    
}
