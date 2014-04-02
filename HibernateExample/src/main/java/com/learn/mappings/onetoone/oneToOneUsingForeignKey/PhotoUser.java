package com.learn.mappings.onetoone.oneToOneUsingForeignKey;

import javax.persistence.*;

@Entity(name="com.learn.mappings.onetoone.oneToOneUsingForeignKey.User")
@Table(name="USER")
public class PhotoUser {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="USER_ID")
	private long id;

	@Column(name="NAME")
	private String name;
	
	@OneToOne(mappedBy="user", cascade = CascadeType.ALL)
	private Photo photo;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
