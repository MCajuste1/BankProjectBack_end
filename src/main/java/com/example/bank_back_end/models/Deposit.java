package com.example.bank_back_end.models;

import com.example.bank_back_end.models.enums.Medium;
import com.example.bank_back_end.models.enums.TransactionStatus;
import com.example.bank_back_end.models.enums.TransactionType;

import javax.persistence.*;

@Entity
public class Deposit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TransactionType type;
	private String transaction_date;

	@Enumerated(EnumType.STRING)
	private TransactionStatus status;

	@Column(name = "payee_id")
	private Long payeeId;// TODO: refactor this

	@Enumerated(EnumType.STRING)
	private Medium medium;

	private Double amount;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public Long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Long payerId) {
		this.payeeId = payerId;
	}

	public Medium getMedium() {
		return medium;
	}

	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
