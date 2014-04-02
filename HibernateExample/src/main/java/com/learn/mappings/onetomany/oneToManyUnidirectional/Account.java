package com.learn.mappings.onetomany.oneToManyUnidirectional;

import javax.persistence.*;

@Entity
@Table(name="ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="ACCOUNT_ID")
	private long id;

	@Column(name="ACCOUNT_TYPE")
	private String accountType;

    @Column(name="AMOUNT")
    private float amount;

    @ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="USER_ID",nullable=true)
	private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Bank bank;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
