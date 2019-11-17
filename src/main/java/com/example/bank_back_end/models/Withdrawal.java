package com.example.bank_back_end.models;


import javax.persistence.*;

@Entity
public class Withdrawal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long id;
    private String type;
    private String transaction_date;
    private String status;

    @Column(name = "payer_id")

    private Long payerId;
    private String medium;
    private Double amount;
    private String description;


    public Withdrawal(Long id, String type, String transaction_date, String status, Long payerId, String medium, Double amount, String description) {


        this.id = id;
        this.type = type;
        this.transaction_date = transaction_date;
        this.status = status;
        this.payerId = payerId;
        this.medium = medium;
        this.amount = amount;
        this.description = description;
    }

    public Withdrawal(){


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;

    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
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
