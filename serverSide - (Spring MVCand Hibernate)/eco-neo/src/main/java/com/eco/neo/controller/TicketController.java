package com.eco.neo.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eco.neo.dao.EnterpriseDAO;
import com.eco.neo.dao.TicketDAO;
import com.eco.neo.pojo.Enterprise;
import com.eco.neo.pojo.Ticket;
import com.eco.neo.pojo.User;
import com.google.gson.Gson;


@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
    TicketDAO ticketDao;

	@RequestMapping(value = "/getTicketsby_EID/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
	@ResponseBody
    public ResponseEntity<Object> getEnterprise(@PathVariable int id, @RequestHeader HttpHeaders headers) throws Exception {
		 
			List<Ticket> tt = ticketDao.getTicketsbyEID(id);
					int count=0;
			JSONObject total = new JSONObject();
			 for(Ticket t : tt) {
			JSONObject ticket = new JSONObject();
			ticket.put("regId",t.getRegId());
			ticket.put("ticketId",t.getTicketId());
			ticket.put("ecoRiskCategrory",t.getEcoRiskCategory());
			ticket.put("industrySector",t.getIndustrySector());
			ticket.put("ticketBy",t.getTicketBy());
			ticket.put("ticketDescription",t.getTicketDescription());
			ticket.put("ticketFor",t.getTicketFor());
			ticket.put("ticketStatus",t.getTicketStatus());
			ticket.put("ticketSubject",t.getTicketSubject());
			ticket.put("ticketTo",t.getTicketTo());
			ticket.put("ticketTime",t.getTicketTime());
			ticket.put("enterpriseId",t.getEnterprise().getEnterpriseId());
			
			count++;
			total.put("ticket"+count,ticket);
			 }
	
			
			return new ResponseEntity<Object>(total.toString(), HttpStatus.OK);
			}
	
	

	@RequestMapping(value="/updateResponseMessage", 
			method = RequestMethod.POST, 
			produces = "application/json")
			public ResponseEntity<Object> addUser(@RequestBody String body, HttpServletRequest request) throws Exception{
			JSONObject json = new JSONObject(body);
			 int ticketId= Integer.parseInt(json.getString("ticketId"));
			 String resp_msg = json.getString("responseMessage");
			 
		      ticketDao.updateResponseMsg(ticketId,resp_msg);
		      
		      Email email = new SimpleEmail();
		      email.setHostName("smtp.googlemail.com");
		      email.setSmtpPort(465);
		      email.setAuthenticator(new DefaultAuthenticator("team.econeo@gmail.com", "mahi1234$$"));
		      email.setSSLOnConnect(true);
		      email.setFrom("team.econeo@gmail.com");
		      email.setSubject("Response for ticket "+ ticketId);
		      email.setMsg(" Below is the respaonse message for ticket ID"+ ticketId + " Response message is : "+ resp_msg);
		      email.addTo("maheshprasad4498@gmail.com");
		      email.send();
			
			return new ResponseEntity<Object>(json.toString(), HttpStatus.OK);
			}
	
	@RequestMapping(value="/closeticket", 
			method = RequestMethod.POST, 
			produces = "application/json")
			public ResponseEntity<Object> closeTicket(@RequestBody String body, HttpServletRequest request) throws Exception{
			JSONObject json = new JSONObject(body);
			 int ticketId= Integer.parseInt(json.getString("id"));
			
			
		      ticketDao.closeTicket(ticketId);
		      
			
			return new ResponseEntity<Object>(json.toString(), HttpStatus.OK);
			}
	
	
	
}
