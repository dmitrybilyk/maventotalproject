package com.learn.mappings.onetoone.oneToOneUsingPrimaryKey;

import javax.persistence.*;

@Entity
@Table(name="USER")
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="USER_ID")
	private long id;
	
	@Column(name="NAME")
	private String name;
	
	@OneToOne(fetch= FetchType.LAZY)
//	@PrimaryKeyJoinColumn
	private Address address;
	
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}	
}
