package com.learn.mappings.onetomany.oneToManyBidirectional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="com.learn.mappings.onetomany.oneToManyBidirectional.User")
@Table(name="USER")
public class AccountUser {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="USER_ID")
	private long id;

	@Column(name="NAME")
	private String name;
	
	@OneToMany(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
	@JoinColumn(name="USER_ID",nullable=false)
	private Set<Account> accounts = new HashSet<Account>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
	public void addAccounts(Account account){
		accounts.add(account);
		account.setUser(this);
	}
}
