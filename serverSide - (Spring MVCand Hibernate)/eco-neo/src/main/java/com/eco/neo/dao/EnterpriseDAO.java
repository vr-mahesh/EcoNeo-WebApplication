package com.eco.neo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eco.neo.exception.UserException;
import com.eco.neo.pojo.Comment;
import com.eco.neo.pojo.Enterprise;
import com.eco.neo.pojo.Stories;
import com.eco.neo.pojo.Ticket;
import com.eco.neo.pojo.User;

public class EnterpriseDAO extends DAO {

	
	

	public Enterprise addEnterprise(Enterprise e) throws Exception {

		  try {
	            begin();
	            if (!get(e.getUserName())) {
	                throw new HibernateException("Enterprise Already Exists");
	            }
	         
		getSession().save(e);
		commit();
		close();
		return e;
		 }
		 catch(Exception ex) {
		 rollback();
		 throw new UserException(ex.getMessage());
		 }
		}
	

	public Enterprise  getEnterprisebyId(int enterpriseId) {
	
	Query query = getSession().createQuery("from Enterprise where enterpriseId=:enterpriseId");
	query.setInteger("enterpriseId", enterpriseId);
	return (Enterprise)query.uniqueResult();
        
	}

	public Ticket add_ticket(Ticket c) {
		
		begin();
		getSession().save(c);
		commit();
		close();
		return (Ticket)c;
		
		}
	
	
	
	
	 public boolean get(String userName) throws UserException {
	        Query q = getSession().createQuery("from Enterprise where userName=:userName");
	        q.setString("userName", userName);
	        List<User> user = q.list();

	        if (user.size() > 0) {
	            return false;
	        } else {
	            return true;
	        }
	    }
	
	 

		public Enterprise authenticate(String userName, String password) throws UserException {
	        try {
	            begin();
	            
	            Query q = getSession().createQuery("FROM Enterprise WHERE userName=:userName");
	            q.setString("userName", userName);
	            
	            List<Enterprise> list = q.list();
	            if(list.isEmpty()) {
	                throw new HibernateException("Username or Password is invalid");
	            }
	            Enterprise e  = list.get(0);
	            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	            if(!bcrypt.matches(password, e.getEnterprisePassword()))
	                throw new HibernateException("Username or Password is invalid");
	            commit();
	            close();
	            return e;
	        } catch (HibernateException e) {
	            rollback();
	            throw new UserException(e.getMessage());
	        }
	    }
	
		

	    public Enterprise getEnterpriseData(int enterpriseId) throws UserException {
			
	        Query query = getSession().createQuery("from Enterprise where enterpriseId=:enterpriseId");
	        query.setInteger("enterpriseId", enterpriseId);
	        return (Enterprise)query.uniqueResult();
	    }


		public List<Enterprise> get_allenterprise() {
			
			Query query = getSession().createQuery("from Enterprise");
			return (List<Enterprise>)query.getResultList();
		}
		
}
