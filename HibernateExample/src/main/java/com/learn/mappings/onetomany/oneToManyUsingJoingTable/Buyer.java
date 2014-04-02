package com.learn.mappings.onetomany.oneToManyUsingJoingTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="BUYER")
public class Buyer {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="BUYER_ID")
	private long id;
	
	@Column(name="BUYER_NAME")
	private String buyerName;
	
	@OneToMany(mappedBy="buyer",cascade= CascadeType.ALL,fetch= FetchType.EAGER)
	Set<Property> properties = new HashSet<Property>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}
	
	public void buyProperty(Property property){
		properties.add(property);
		property.setBuyer(this);
	}
}
