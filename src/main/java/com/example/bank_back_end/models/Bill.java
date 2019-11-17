package com.example.bank_back_end.models;


import javax.persistence.*;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String payee;

    private String nickname;

    private String creation_date;

    private String payment_date;

    private String recurring_date;

    private String upcoming_payment_date;

    private Double payment_amount;

    @Column(name = "account_id")
    private Long accountId;

    public  Bill(){

    }
    enum Status {

        Pending,Cancelled,Completed,Recurring

    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public String getPayee() {
        return payee;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public String getRecurring_date() {
        return recurring_date;
    }

    public String getUpcoming_payment_date() {
        return upcoming_payment_date;
    }

    public Double getPayment_amount() {
        return payment_amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setPayment_amount(Double payment_amount) {
        this.payment_amount = payment_amount;
    }

    public void setUpcoming_payment_date(String upcoming_payment_date) {
        this.upcoming_payment_date = upcoming_payment_date;
    }

    public void setRecurring_date(String recurring_date) {
        this.recurring_date = recurring_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
