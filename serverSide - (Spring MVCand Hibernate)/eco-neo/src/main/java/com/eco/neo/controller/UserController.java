package com.eco.neo.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Random;
import java.util.TimeZone;

import com.eco.neo.dao.AuthDAO;
import com.eco.neo.dao.UserDAO;
import com.eco.neo.pojo.User;
import com.eco.neo.response.Message;
import com.eco.neo.validator.UserValidator;



@Controller
@RequestMapping("/api")
public class UserController {
	
	@Autowired
    UserDAO userDao;
	
	 @Autowired
	    UserValidator validator;


	    @InitBinder("user")
	    private void initBinder(WebDataBinder binder) {
	        binder.setValidator(validator);
	    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/adduser/user/{userId}/password/{password}",
            method = RequestMethod.GET,
            produces = "application/json")
	@ResponseBody
    public ResponseEntity<Object> getUser(@PathVariable String userId, @PathVariable String password, @RequestHeader HttpHeaders headers) throws Exception {
		 
		User user = userDao.authenticate(userId, password);
		JSONObject userJson = new JSONObject();
		userJson.put("userName", user.getUserName());
        userJson.put("fullName", user.getFirstName()+ " "+user.getLastName());
        userJson.put("type", "user");
        userJson.put("userId", user.getId());
        
        userJson.put("walletAmount", user.getWalletAmount());
        userJson.put("password", user.getPassword());
        userJson.put("role", user.getRole());
        userJson.put("location", user.getLocation());
        userJson.put("phone", user.getPhone());
        
    	JSONObject jsonToken = new JSONObject ();
        jsonToken.put("user", "admin");
        jsonToken.put("issuer", "mycompany");
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 20);
        Date date = calendar.getTime();
        jsonToken.put("expiration", df.format(date));
        String token = jsonToken.toString();
        userJson.put("authToken", token);
        
        return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/user/{userId}",
            method = RequestMethod.GET,
            produces = "application/json")
	@ResponseBody
    public ResponseEntity<Object> getUser(@PathVariable int userId, @RequestHeader HttpHeaders headers) throws Exception {
		
		User user = userDao.getProfileData(userId);
		JSONObject userJson = new JSONObject();
		userJson.put("userName", user.getUserName());
        userJson.put("fullName", user.getFirstName() +" "+ user.getLastName());
        userJson.put("role", user.getRole());
        userJson.put("location", user.getLocation());
        userJson.put("phone", user.getPhone());
        userJson.put("walletAmount",user.getWalletAmount());
        
        return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
    }
	
	@RequestMapping(value="/user/add", 
			method = RequestMethod.POST, 
			produces = "application/json")
			public ResponseEntity<Object> addUser(  @RequestBody String body, HttpServletRequest request) throws Exception{

		JSONObject json = new JSONObject(body);
			User u = new User();
			u.setUserName(json.getString("userName"));
			u.setPassword(json.getString("password"));
			u.setRole(json.getString("role"));
			u.setFirstName(json.getString("firstName"));
			u.setLastName(json.getString("lastName"));
			u.setPhone(json.getString("phone"));
			u.setLocation(json.getString("location"));
        	u.setWalletAmount(json.getInt("walletAmount"));
			
        	
        	
        	
        	
			int max_id =userDao.getMaxId(); 
			 Random rand = new Random();
		    int user_id= max_id +1;
		    u.setId(user_id);
			User resp = userDao.createUser(u);
			JSONObject userJson = new JSONObject();
		    	userJson.put("userName", resp.getUserName());
        	    userJson.put("id", resp.getId());
			    userJson.put("firstName", resp.getFirstName());
			    

Email email = new SimpleEmail();
email.setHostName("smtp.googlemail.com");
email.setSmtpPort(465);
email.setAuthenticator(new DefaultAuthenticator("team.econeo@gmail.com", "mahi1234$$"));
email.setSSLOnConnect(true);
email.setFrom("team.econeo@gmail.com");
email.setSubject("Registration Successful");
email.setMsg("Thank you for signing up with ECO NEO");
email.addTo("maheshprasad4498@gmail.com");
email.send();
			    
			    
			    
			    
			return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
			}
	
	

	@RequestMapping(value="add/user/add", 
			method = RequestMethod.POST, 
			produces = "application/json")
			public ResponseEntity<Object> addUseradd(  @RequestBody String body, BindingResult result, HttpServletRequest request) throws Exception{

		JSONObject json = new JSONObject(body);
			User u = new User();
			u.setUserName(json.getString("userName"));
			u.setPassword(json.getString("password"));
			u.setRole(json.getString("role"));
			u.setFirstName(json.getString("firstName"));
			u.setLastName(json.getString("lastName"));
			u.setPhone(json.getString("phone"));
			u.setLocation(json.getString("location"));
        	u.setWalletAmount(json.getInt("walletAmount"));
			
        	
        	validator.validate(u, result);
            if (result.hasErrors()) {
//                List<Message> errors = new ArrayList<Message>();
//                result.getFieldErrors().stream()
//                        .forEach(action -> errors.add(new Message(action.getDefaultMessage())));
                return new ResponseEntity<Object>(" Please enter the correct values", HttpStatus.BAD_REQUEST);
               
            }	
        	
        	
        	
			int max_id =userDao.getMaxId(); 
			 Random rand = new Random();
		    int user_id= max_id +1;
		    u.setId(user_id);
			User resp = userDao.createUser(u);
			JSONObject userJson = new JSONObject();
		    	userJson.put("userName", resp.getUserName());
        	    userJson.put("id", resp.getId());
			    userJson.put("firstName", resp.getFirstName());
			    

Email email = new SimpleEmail();
email.setHostName("smtp.googlemail.com");
email.setSmtpPort(465);
email.setAuthenticator(new DefaultAuthenticator("team.econeo@gmail.com", "mahi1234$$"));
email.setSSLOnConnect(true);
email.setFrom("team.econeo@gmail.com");
email.setSubject("TestMail");
email.setMsg("This is a test mail ... :-)");
email.addTo("maheshprasad4498@gmail.com");
email.send();
			    
			    
			    
			    
			return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
			}
	

}