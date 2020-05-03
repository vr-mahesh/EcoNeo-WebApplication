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
@Table(name = "Comment") 
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
	private int commentId;
	
    public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentName() {
		return commentName;
	}

	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Stories getStory() {
		return story;
	}

	public void setStory(Stories story) {
		this.story = story;
	}

	@Column(name = "commentName")
	private String commentName;
    
    @Column(name = "commentText")
  	private String commentText;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="storyId")
    private Stories story;
    
}
