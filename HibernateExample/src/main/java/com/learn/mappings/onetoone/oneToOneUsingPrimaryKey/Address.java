package com.learn.mappings.onetoone.oneToOneUsingPrimaryKey;

import javax.persistence.*;

@Entity
@Table(name="ADDRESS")
public class Address {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="ADDRESS_ID")
		private long id;
		
		@Column(name="STREET_NAME")
		private String street;
		@Column(name="ZIPCODE")
		private String zipCode;
		@Column(name="CITY_NAME")
		private String city;
		
		@OneToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY) //FetchType.EAGER sets the lazy loading to false
//		@PrimaryKeyJoinColumn
		private User user;

    public Address(Long aLong) {
        this.id = aLong;
    }

    public Address() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}		
		
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getZipCode() {
			return zipCode;
		}
		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
}
