package com.learn.mappings.onetomany.oneToManyBidirectional;

import javax.persistence.*;

@Entity(name="com.learn.mappings.onetomany.oneToManyBidirectional.Account")
@Table(name="ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="ACCOUNT_ID")
	private long id;

	@Column(name="ACCOUNT_TYPE")
	private String accountType;

	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="USER_ID",nullable=false,updatable=false,insertable=false)
	private AccountUser user;

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

	public AccountUser getUser() {
		return user;
	}

	public void setUser(AccountUser user) {
		this.user = user;
	}
}
