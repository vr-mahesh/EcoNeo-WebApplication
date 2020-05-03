package com.eco.neo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eco.neo.dao.EnterpriseDAO;
import com.eco.neo.dao.UserDAO;
import com.eco.neo.pojo.Enterprise;
import com.eco.neo.pojo.Ticket;
import com.eco.neo.pojo.User;

@Controller
@RequestMapping("/enterprise")
public class EnterpriseController {
	
	@Autowired
    EnterpriseDAO enterpriseDao;

	@RequestMapping(value="/addenterprise", 
			method = RequestMethod.POST, 
			produces = "application/json")
			public ResponseEntity<Object> addEnterprise(@RequestBody String body, HttpServletRequest request) throws Exception{
			JSONObject json = new JSONObject(body);
			Enterprise e= new Enterprise();
		
			e.setEnterpriseCheck(json.getString("enterpriseCheck"));
			e.setEnterpriseCountry(json.getString("enterpriseCountry"));
			e.setEnterpriseLocation(json.getString("enterpriseLocation"));
			e.setEnterpriseName(json.getString("enterpriseName"));
			
			  BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	          String bcryptPassword = bcrypt.encode(json.getString("password"));    
			
			e.setEnterprisePassword(bcryptPassword);
			
			e.setEnterpriseType(json.getString("enterpriseType"));
			e.setIndustrySector(json.getString("industrySector"));
			e.setUserName(json.getString("userName"));
			e.setWalletAmount(json.getString("walletAmount"));
			
		
			Enterprise ee = enterpriseDao.addEnterprise(e);
			
			
			JSONObject userJson = new JSONObject();
//		    	userJson.put("userName", resp.getUserName());
//        	    userJson.put("id", resp.getId());
//			    userJson.put("firstName", resp.getFirstName());
			return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
			}
	

	@RequestMapping(value="/addticket", 
			method = RequestMethod.POST, 
			produces = "application/json")
			public ResponseEntity<Object> addTicket(@RequestBody String body, HttpServletRequest request) throws Exception{
			JSONObject json = new JSONObject(body);
			
			int enterpriseId = Integer.parseInt(json.getString("enterpriseId"));
			Enterprise ee = enterpriseDao.getEnterprisebyId(enterpriseId);
			Ticket t = new Ticket();
			t.setEcoRiskCategory(json.getString("ecoRiskCategory"));
			t.setIndustrySector(json.getString("industrySector"));
			t.setTicketBy(json.getString("ticketBy"));
			t.setTicketFor(json.getString("ticketFor"));
			t.setTicketTo(json.getString("ticketTo"));
			int regId= Integer.parseInt(json.getString("regId"));
			t.setRegId(regId);
			t.setTicketDescription(json.getString("ticketDescription"));
			t.setTicketSubject(json.getString("ticketSubject"));
			t.setTicketTime(json.getString("ticketTime"));
			
			t.setTicketStatus("open");
			
			ee.addTicket(t);
			enterpriseDao.add_ticket(t);
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("team.econeo@gmail.com", "mahi1234$$"));
			email.setSSLOnConnect(true);
			email.setFrom("team.econeo@gmail.com");
			email.setSubject("ticket generated : " + json.getString("ticketSubject"));
			email.setMsg("A ticket with subject : "+ json.getString("ticketSubject") +" with description of "+json.getString("ticketDescription")+" is generated by "+ json.getString("ticketBy")  );
			email.addTo("maheshprasad4498@gmail.com");
			email.send();
					
			JSONObject userJson = new JSONObject();
			return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
			}
	
	

	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/auth/user/{userId}/password/{password}",
            method = RequestMethod.GET,
            produces = "application/json")
	@ResponseBody
    public ResponseEntity<Object> getEnterprise(@PathVariable String userId, @PathVariable String password, @RequestHeader HttpHeaders headers) throws Exception {
		try {
		Enterprise e  = enterpriseDao.authenticate(userId, password);
		JSONObject userJson = new JSONObject();
		userJson.put("enterpriseId", e.getEnterpriseId());
        userJson.put("enterpriseName", e.getEnterpriseName());
        userJson.put("enterpriseCheck",e.getEnterpriseCheck() );
        userJson.put("enterprisecountry", e.getEnterpriseCountry());
        userJson.put("enterpriseType", e.getEnterpriseType());
        userJson.put("industrySector", e.getIndustrySector());
       // userJson.put("ticketList", e.getTicket_list());
        userJson.put("walletamount",e.getWalletAmount() );
        userJson.put("userName", e.getUserName());
        userJson.put("type", "enterprise");
        JSONObject jsonToken = new JSONObject();
       
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
	catch(Exception e) {
		JSONObject userJson = new JSONObject();
		userJson.put("msg", e.getMessage());
		return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
	}
    }
	
	
	@RequestMapping(value = "/getenterprise/{enterpriseId}",
            method = RequestMethod.GET,
            produces = "application/json")
	@ResponseBody
    public ResponseEntity<Object> getEnterprise(@PathVariable int enterpriseId, @RequestHeader HttpHeaders headers) throws Exception {
		try {
		Enterprise e  = enterpriseDao.getEnterpriseData(enterpriseId);
		JSONObject userJson = new JSONObject();
		userJson.put("enterpriseId", e.getEnterpriseId());
        userJson.put("enterpriseName", e.getEnterpriseName());
        userJson.put("enterpriseCheck",e.getEnterpriseCheck() );
        userJson.put("enterprisecountry", e.getEnterpriseCountry());
        userJson.put("enterpriseType", e.getEnterpriseType());
        userJson.put("industrySector", e.getIndustrySector());
        userJson.put("walletamount",e.getWalletAmount() );
        userJson.put("userName", e.getUserName());
        userJson.put("type", "enterprise");
        
        return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
		}
		catch(Exception e) {
			JSONObject userJson = new JSONObject();
			userJson.put("msg", e.getMessage());
			return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
		}
		}
	
	@RequestMapping(value = "/getallenterprise/{enterpriseId}",
            method = RequestMethod.GET,
            produces = "application/json")
	@ResponseBody
    public ResponseEntity<Object> getallenterprise(@PathVariable int enterpriseId, @RequestHeader HttpHeaders headers) throws Exception {
		try {
		List<Enterprise> ee  = enterpriseDao.get_allenterprise();
		JSONObject userJson = new JSONObject();
		int count=0;
		for(Enterprise e : ee) {
			JSONObject u = new JSONObject();
			u.put("enterpriseId", e.getEnterpriseId());
			u.put("enterpriseName", e.getEnterpriseName());
			u.put("enterpriseType", e.getEnterpriseType());
			List<Ticket> ttt= e.getTicket_list();
			JSONObject ticket = new JSONObject();
			int tick=0;
			for(Ticket tt : ttt) {
				JSONObject t = new JSONObject();
				t.put("regId",tt.getRegId());
				t.put("ticketId",tt.getTicketId());
				t.put("ecoRiskCategory",tt.getEcoRiskCategory());
				t.put("industrySector",tt.getIndustrySector());
				t.put("ticketBy",tt.getTicketBy());
				t.put("ticketDescription",tt.getTicketDescription());
				t.put("ticketFor",tt.getTicketFor());
				t.put("ticketStatus",tt.getTicketStatus());
				t.put("ticketSubject",tt.getTicketSubject());
				t.put("ticketTo",tt.getTicketTo());
				t.put("ticketTime",tt.getTicketTime());
				t.put("enterpriseId",tt.getEnterprise().getEnterpriseId());
				tick++;
				ticket.put("ticket_"+tick, t);
			}
			u.put("enterpriseTickets", ticket );
			count++;
			userJson.put("e_"+count, u);
		}
        
        return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
   
		}
		catch(Exception e) {
			JSONObject userJson = new JSONObject();
			userJson.put("msg", e.getMessage());
			return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
		}}
	

}
