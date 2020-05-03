package com.eco.neo.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Stories") 
public class Stories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storyId")
	private int storyId;
	
    @Column(name = "storyOwner")
	private String storyOwner;
    
    @Column(name = "ownerId")
  	private String ownerId;
    
    public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	public String getStoryOwner() {
		return storyOwner;
	}

	public void setStoryOwner(String storyOwner) {
		this.storyOwner = storyOwner;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getStoryText() {
		return storyText;
	}

	public void setStoryText(String storyText) {
		this.storyText = storyText;
	}

	public String getImageNames() {
		return imageNames;
	}

	public void setImageNames(String imageNames) {
		this.imageNames = imageNames;
	}

	public String getStoryTime() {
		return storyTime;
	}

	public void setStoryTime(String storyTime) {
		this.storyTime = storyTime;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	@Column(name = "storyText")
	private String storyText;
    
    @Column(name = "imageNames")
	private String imageNames;
    
    @Column(name = "storyTime")
	private String storyTime;
    
    @Column(name = "likes")
	private int likes;
    
	@Column(name = "ownerType")
	private String ownerType;
    
    public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	@Column(name = "commentId")
	private int commentId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "story")
    private List<Comment> comments = new ArrayList<Comment>();
    
    public List<Likes> getLike_list() {
		return like_list;
	}

	public void setLike_list(List<Likes> like_list) {
		this.like_list = like_list;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "story")
    private List<Likes> like_list = new ArrayList<Likes>();
    

    public void addLikes(Likes likes) {
    	like_list.add( likes );
    	likes.setStory(this);
	}
	
    public void addComment(Comment comment) {
    	comments.add( comment );
    	comment.setStory(this);
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
    
}
