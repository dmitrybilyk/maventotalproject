package com.learn.mappings.onetomany.oneToManyUnidirectional;

import javax.persistence.*;

@Entity(name="com.learn.mappings.onetomany.oneToManyUnidirectional.User")
@Table(name="USER")
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="USER_ID")
	private long id;

	@Column(name="NAME")
	private String name;
	
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
}
