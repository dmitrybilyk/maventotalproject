package com.learn.mappings.onetoone.oneToOneUsingForeignKey;

import javax.persistence.*;

@Entity
@Table(name="PHOTO")
public class Photo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="PHOTO_ID")
	private long photoId;		

	@Column(name="FILE_NAME")
	private String fileName;

	@OneToOne(cascade= CascadeType.ALL,fetch= FetchType.EAGER)
	@JoinColumn(name="USER_ID",unique=true)
	private PhotoUser user;

	public long getPhotoId() {
		return photoId;
	}
	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public PhotoUser getUser() {
		return user;
	}
	public void setUser(PhotoUser user) {
		this.user = user;
	}	
}
