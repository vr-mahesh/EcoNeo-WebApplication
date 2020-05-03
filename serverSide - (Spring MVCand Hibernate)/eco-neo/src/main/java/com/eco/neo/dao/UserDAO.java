package com.eco.neo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eco.neo.exception.UserException;
import com.eco.neo.pojo.User;

public class UserDAO extends DAO {
	
	public User authenticate(String userName, String password) throws UserException {
        try {
            begin();
            
            Query q = getSession().createQuery("FROM User WHERE userName=:userName");
            q.setString("userName", userName);
            
            List<User> list = q.list();
            if(list.isEmpty()) {
                throw new HibernateException("Username or Password is invalid");
            }
            User user = list.get(0);
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            if(!bcrypt.matches(password, user.getPassword()))
                throw new HibernateException("Username or Password is invalid");
            commit();
            close();
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new UserException(e.getMessage());
        }
    }

	public User getUser(String userName, String password) throws UserException {
		
        Query query = getSession().createQuery("from User where userName=:userName AND password=:password");
        query.setString("userName", userName);
        query.setString("password", password);
        return (User)query.uniqueResult();
    }
	
    public User getProfileData(int userId) throws UserException {
		
        Query query = getSession().createQuery("from User where id=:userId");
        query.setInteger("userId", userId);
        return (User)query.uniqueResult();
    }
	
    
    
    
	public User createUser(User u) throws UserException {
		//try {
		
		  try {
	            begin();
	            if (!get(u.getUserName())) {
	                throw new HibernateException("User Already Exists");
	            }
	            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	            String bcryptPassword = bcrypt.encode(u.getPassword());    
		User user = new User();
		user.setUserName(u.getUserName());
		user.setPassword(bcryptPassword);
    	user.setFirstName(u.getFirstName());
    	user.setLastName(u.getLastName());
    	user.setLocation(u.getLocation());
    	user.setWalletAmount(u.getWalletAmount());
    	user.setPhone(u.getPhone());
    	user.setId(u.getId());
		user.setRole(u.getRole());
		getSession().save(user);
		commit();
		close();
		return user;
		 }
		 catch(UserException e) {
		 rollback();
		 throw new UserException(e.getMessage());
		 }
		}
	
	public int getMaxId() {
		 Query query = getSession().createQuery("select Max(id) from User");
	     //   query.setString("userName", userName);
	     // query.setString("password", password);
	        return (Integer)query.uniqueResult();
	}
	
	 public boolean get(String userName) throws UserException {
	        Query q = getSession().createQuery("from User where userName=:userName");
	        q.setString("userName", userName);
	        List<User> user = q.list();

	        if (user.size() > 0) {
	            return false;
	        } else {
	            return true;
	        }
	    }
}