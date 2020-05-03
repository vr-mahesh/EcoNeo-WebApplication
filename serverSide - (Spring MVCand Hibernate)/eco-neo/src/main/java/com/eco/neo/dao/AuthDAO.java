package com.eco.neo.dao;



import static com.eco.neo.dao.DAO.getSession;
import com.eco.neo.exception.UserException;
import com.eco.neo.pojo.User;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class AuthDAO extends DAO{
    
    // User authentication
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
    
    // Getting user details from userId
    public User getUser(String userId) throws UserException{
        try {
            begin();
            User user = getSession().get(User.class, Integer.parseInt(userId));
            commit();
            close();
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("User does not exist");
        }
    }
}
