package com.eco.neo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.eco.neo.dao.AuditDAO;
import com.eco.neo.dao.EnterpriseDAO;
import com.eco.neo.pojo.AuditRecord;
import com.eco.neo.pojo.Enterprise;
import com.eco.neo.pojo.Ticket;
import com.eco.neo.pojo.User;
import com.google.gson.Gson;

@Controller
@RequestMapping("/auditrecord")
public class AuditController {
	

	@Autowired
    AuditDAO auditDao;

	

	@RequestMapping(value="/add", 
			method = RequestMethod.POST, 
			produces = "application/json")
			public ResponseEntity<Object> addEnterprise(@RequestBody String body, HttpServletRequest request) throws Exception{
		try {
		JSONObject json = new JSONObject(body);
			
			AuditRecord a = new AuditRecord();
			
			a.setAqi(json.getString("aqi"));
			a.setCarbonmonoxide(json.getString("carbonmonoxide"));
			a.setNotes(json.getString("notes"));
			a.setOxygen_level(json.getString("oxygen_level"));
			a.setOzone(json.getString("ozone"));
			
			a.setPm(json.getString("pm"));
			a.setSulphurdioxide(json.getString("sulphurdioxide"));
			int id= Integer.parseInt(json.getString("ticketId"));
			
			Ticket t = auditDao.getticketbyId(id);
			a.setTicketId(t);
			
			a.setTotal_eco_index(json.getString("totalEcoIndex"));
			a.setW_ammonia(json.getString("ammonia_content"));
			
			a.setW_cdom(json.getString("cdom"));
			a.setW_metal(json.getString("metal"));
			a.setW_pcb(json.getString("pcb"));
			a.setWaste_materials(json.getString("wastematerials"));
			a.setWater_conductivity(json.getString("conductivity"));
			a.setWater_ph(json.getString("water_ph"));
			a.setWater_temp(json.getString("water_temp"));
		
			AuditRecord  aa = auditDao.addAuditRecord(a);
			
			
			JSONObject userJson = new JSONObject();
			userJson.put("data", "true");
			return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
		}
		catch(Exception e) {
			JSONObject userJson = new JSONObject();
			userJson.put("msg", e.getMessage());
			return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
		}
			}

	

	@RequestMapping(value = "/getallauditrecords/{enterpriseId}",
            method = RequestMethod.GET,
            produces = "application/json")
	@ResponseBody
    public ResponseEntity<Object> getallauditrecords(@PathVariable int enterpriseId, @RequestHeader HttpHeaders headers) throws Exception {
		
		List<AuditRecord>  aa = auditDao.getAllAuditRecord();
	
		JSONObject json = new JSONObject();
		int count=0;
		for(AuditRecord a : aa) {
			JSONObject j = new JSONObject();
			j.put("auditRecordId", a.getAuditRecordId());
			j.put("aqi", a.getAqi());
			j.put("carbonmonoxide", a.getCarbonmonoxide());
			j.put("notes", a.getNotes());
			j.put("oxygenlevel", a.getOxygen_level());
			j.put("ozone", a.getOzone());
			j.put("pm", a.getPm());
			j.put("sulphurdioxide", a.getSulphurdioxide());
			
//			Ticket t = a.getTicketId();
//			
//			
//			j.put("ticketId", t.getTicketId());
//			j.put("ticketby", t.getTicketBy());
//			j.put("ticketTo", t.getTicketTo());
//			j.put("ticketSubject", t.getTicketSubject());
//
//			j.put("ticketDescription", t.getTicketDescription());
//
//			j.put("ticketFor", t.getTicketFor());
			
			j.put("totalecoindex", a.getTotal_eco_index());
			j.put("w_ammonia", a.getW_ammonia());
			j.put("w_cdom", a.getW_cdom());
			j.put("w_pcb", a.getW_pcb());
			j.put("wastematerials", a.getWaste_materials());
			j.put("water_ph", a.getWater_ph());
			j.put("waterconductivity", a.getWater_conductivity());
			j.put("water_temp", a.getWater_temp());
			count++;
			json.put("audit_"+count,j);
		}
	
			
        return new ResponseEntity<Object>(json.toString(), HttpStatus.OK);
    }
}
