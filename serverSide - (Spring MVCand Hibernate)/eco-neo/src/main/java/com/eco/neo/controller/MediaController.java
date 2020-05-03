package com.eco.neo.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eco.neo.dao.CommentDAO;
import com.eco.neo.dao.StoryDAO;
import com.eco.neo.pojo.Comment;

@Controller
@RequestMapping("/media")
public class MediaController {
	
	@Autowired
    CommentDAO commentDao;
	
	

    @RequestMapping(value = "/comment/delete",
            method = RequestMethod.POST,
            produces = "application/json")
    public ResponseEntity<Object> deleteRatings(@RequestParam("comment_id") String comment_id ,RedirectAttributes redirectAttributes) {
        try {
        
        	int com = commentDao.delete_comment(Integer.parseInt(comment_id)); 
        		JSONObject comment = new JSONObject();
        		comment.put("deleted_comment",com);
       	 return new ResponseEntity<Object>(comment.toString(), HttpStatus.OK);
        } catch (Exception e) {
        	JSONObject comment = new JSONObject();
        	comment.put("deleted_comment","not deleted");
          	 return new ResponseEntity<Object>(comment.toString(), HttpStatus.OK);
        }
    }
	
}
