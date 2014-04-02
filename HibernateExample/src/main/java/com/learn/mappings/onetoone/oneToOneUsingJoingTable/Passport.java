package com.learn.mappings.onetoone.oneToOneUsingJoingTable;

import javax.persistence.*;

@Entity(name="annotation.com.demo.oneToOneUsingJoingTable.Passport")
@Table(name="PASSPORT")
public class Passport {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="PASSPORT_ID")
	private long id;

	@Column(name="PASSPORT_NUMBER")
	private String passportNumber;

	@OneToOne
	@JoinTable(name="PASSPORTS",joinColumns=@JoinColumn(name="PASSPORT_ID",unique=true),
			inverseJoinColumns=@JoinColumn(name="PERSON_ID",unique=true))
	private Person person;

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
