package com.eco.neo.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Likes") 
public class Likes{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeId")
	private int likeId;
	
	@Column(name = "userId")
	private String userId;
    
    @Column(name = "userName")
  	private String userName;
    

    @Column(name = "fullName")
  	private String fullName;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="storyId")
    private Stories story;

	public int getLikeId() {
		return likeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Stories getStory() {
		return story;
	}

	public void setStory(Stories story) {
		this.story = story;
	}
}
