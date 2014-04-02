package com.learn.mappings.onetomany.oneToManyUsingJoingTable;

import javax.persistence.*;

@Entity
@Table(name="PROPERTY")
public class Property {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="PROPERTY_ID")
	private long id;
	
	@Column(name="PROPERTY_NAME")
	private String propertyName;
	
	@Column(name="PROPERTY_VALUE")
	private String propertyValue;

	@ManyToOne(fetch= FetchType.EAGER)
	@JoinTable(name="PROPERTY_BUYER",joinColumns=@JoinColumn(name="PROPERTY_ID"),inverseJoinColumns=@JoinColumn(name="BUYER_ID"))
	private Buyer buyer;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
}
