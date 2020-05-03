package com.eco.neo.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.query.Query;

import com.eco.neo.pojo.Comment;
import com.eco.neo.pojo.Likes;
import com.eco.neo.pojo.Stories;
import com.eco.neo.pojo.User;


public class StoryDAO extends DAO {

	public Stories createStory(Stories s) {
		//try {
		begin();
		Stories story = new Stories();
		story.setCommentId(s.getCommentId());
		story.setImageNames(s.getImageNames());
		story.setLikes(s.getLikes());
		story.setOwnerId(s.getOwnerId());
		story.setStoryOwner(s.getStoryOwner());
		story.setStoryText(s.getStoryText());
		story.setStoryTime(s.getStoryTime());
		story.setOwnerType(s.getOwnerType());
		getSession().save(story);
		commit();
		close();
		return story;
		// }
		// catch(UserException e) {
		// rollback();
		// throw new UserException(e.getMessage());
		// }
		}
	
	public Comment add_Comment(Comment c) {
		
		begin();
		getSession().save(c);
		commit();
		close();
		return (Comment)c;
		
		}
	
	
	
	public List<Object[]> getAllStories() {
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		javax.persistence.criteria.CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<Stories> stories = query.from(Stories.class);
		query.multiselect(stories.get("storyId"), stories.get("storyOwner"), stories.get("ownerId"), stories.get("storyId"), stories.get("storyText"), stories.get("imageNames"), stories.get("storyTime"), stories.get("likes"), stories.get("commentId"), stories.get("ownerType"));
		List<Object[]> resultList = getSession().createQuery(query).getResultList();
		return resultList;
		
		
//		Query query = getSession().createQuery("select storyId,storyOwner,ownerId,storyId,storyText,imageNames,storyTime,likes,commentId,ownerType from Stories");
//	       
//		return (List<Object[]>)query.list();
	        
		}
public List<Object[]> getProfileStories(int userId) {
		
	
//	CriteriaBuilder builder = getSession().getCriteriaBuilder();
//	javax.persistence.criteria.CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
//	Root<Stories> stories = query.from(Stories.class);
//	query.multiselect(stories.get("storyId"), stories.get("storyOwner"), stories.get("ownerId"), stories.get("storyId"), stories.get("storyText"), stories.get("imageNames"), stories.get("storyTime"), stories.get("likes"), stories.get("commentId"), stories.get("ownerType"));
//	List<Object[]> resultList = getSession().createQuery(query).getResultList();
//	return resultList;
		Query query = getSession().createQuery("select storyId,storyOwner,ownerId,storyId,storyText,imageNames,storyTime,likes,commentId,ownerType from Stories where ownerId=:userId ");
		query.setInteger("userId", userId);
		return (List<Object[]>)query.list();
	        
		}


public Stories  getStorybyId(int storyId) {
	
	Query query = getSession().createQuery("from Stories where storyId=:storyId");
	query.setInteger("storyId", storyId);
	return (Stories)query.uniqueResult();
        
	}

public Likes add_Likes(Likes l) {
	begin();
	getSession().save(l);
	commit();
	close();
	return (Likes)l;
	
}

public String updateStory(String storyText, String uploadedFileName, int storyid) {
	// TODO Auto-generated method stub
	begin();
	String hql = "UPDATE Stories set storyText=:storyText , imageNames=:imageNames  WHERE storyid=:storyid";
    Query query = getSession().createQuery(hql);
    query.setParameter("storyText", storyText);
    query.setParameter("imageNames", uploadedFileName);
    query.setParameter("storyid", storyid);
    int result = query.executeUpdate();
	commit();
	close();
	return "updated";
}

public int delete_story(int storyId) throws Exception {
	 try {
         begin();
         String hql = "DELETE FROM Stories r WHERE storyId=:storyId";
         Query query = getSession().createQuery(hql);
         query.setParameter("storyId", storyId);
         int result = query.executeUpdate();
         commit();
         close();
         return result;
     } catch(HibernateException e) {
         rollback();
         throw new Exception(e.getMessage());
     } 
	
}
	
}
