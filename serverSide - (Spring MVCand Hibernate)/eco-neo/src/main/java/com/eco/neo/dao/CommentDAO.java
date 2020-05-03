package com.eco.neo.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.eco.neo.pojo.Comment;

public class CommentDAO extends DAO {

	public int delete_comment(int id) throws Exception {
		 try {
	            begin();
	            String hql = "DELETE FROM Comment r WHERE commentId=:commentId";
	            Query query = getSession().createQuery(hql);
	            query.setParameter("commentId", id);
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
