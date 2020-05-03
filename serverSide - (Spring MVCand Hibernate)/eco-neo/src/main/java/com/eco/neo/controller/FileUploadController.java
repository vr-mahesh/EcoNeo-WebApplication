package com.eco.neo.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.util.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;
import java.util.StringJoiner;
import java.util.TimeZone;

import com.eco.neo.dao.StoryDAO;
import com.eco.neo.dao.UserDAO;
import com.eco.neo.exception.UserException;
import com.eco.neo.pojo.Comment;
import com.eco.neo.pojo.Likes;
import com.eco.neo.pojo.Stories;
import com.eco.neo.pojo.User;
import com.eco.neo.response.Message;


@Controller
@RequestMapping("/upload")
public class FileUploadController {
	
	@Autowired
    StoryDAO storyDao;
	
	private static String UPLOADED_FOLDER = "C://econeo//images//";

	 @RequestMapping(value = "/image", method = RequestMethod.POST)
    public ResponseEntity<Object> storyUpload(@RequestParam("file") MultipartFile[] files,
    		@RequestParam("storyOwner") String storyOwner ,
    		@RequestParam("ownerId") String ownerId ,
    		@RequestParam("ownerType") String ownerType ,
    		@RequestParam("storyText") String storyText, 
    		@RequestParam("storyTime") String storyTime,RedirectAttributes redirectAttributes) {
		 
		 
		 //System.out.println(fileData);
		 
		 try {
		 JSONObject fileJson = new JSONObject();
		   fileJson.put("storyOwner",storyOwner);
		   fileJson.put("ownerId",ownerId);
		   fileJson.put("storyText",storyText);
		   fileJson.put("storyTime",storyTime);
		   fileJson.put("ownerType",storyTime);
		 Stories s = new Stories();
		 s.setStoryOwner(storyOwner.toString());
		 s.setOwnerId(ownerId.toString());
		 s.setStoryText(storyText.toString());
		 s.setStoryTime(storyTime.toString());
		 s.setLikes(0);
		 s.setOwnerType(ownerType.toString());
		 
		 Random rand = new Random();
		 int commentId= rand.nextInt();
		 s.setCommentId(commentId);	
			StringJoiner sj = new StringJoiner(" ,");
			 for (MultipartFile file : files) {

		            if (file.isEmpty()) {
		                continue; //next pls
		            }

		            try {
		            	 Random rand1 = new Random();
		        		 int img= rand1.nextInt();
		            	
		          String imgg = "img_" + String.valueOf(img)+".";
		                byte[] bytes = file.getBytes();
		                String fileName = file.getOriginalFilename();
		                String[] type = fileName.split("\\.");
		                Path path = Paths.get(UPLOADED_FOLDER + imgg + type[1]);
		                Files.write(path, bytes);

		                sj.add(imgg + type[1]);

		            } catch (IOException e) {
		                e.printStackTrace();
		            }

		        }

		        String uploadedFileName = sj.toString();
		        fileJson.put("uploadedFileName",uploadedFileName);
		        s.setImageNames(uploadedFileName.toString());
              Stories storyAdded = storyDao.createStory(s);
        return new ResponseEntity<Object>(fileJson.toString(), HttpStatus.OK);
		 }
		 catch(Exception e) {
				JSONObject userJson = new JSONObject();
				userJson.put("msg", e.getMessage());
				return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
			}
    }
	 

	 @RequestMapping(value = "/updateimage", method = RequestMethod.POST)
    public ResponseEntity<Object> updateimage(@RequestParam("file") MultipartFile[] files,
    		@RequestParam("storyOwner") String storyOwner ,
    		@RequestParam("ownerId") String ownerId ,
    		@RequestParam("ownerType") String ownerType ,
    		@RequestParam("storyText") String storyText, 
    		@RequestParam("storyId") String storyId, 
    		@RequestParam("storyTime") String storyTime,RedirectAttributes redirectAttributes) {
		 
		 
		 //System.out.println(fileData);
		 
		 try {
		 JSONObject fileJson = new JSONObject();
		   fileJson.put("storyOwner",storyOwner);
		   fileJson.put("ownerId",ownerId);
		   fileJson.put("storyText",storyText);
		   fileJson.put("storyTime",storyTime);
		   fileJson.put("ownerType",storyTime);
		 Stories s = new Stories();
		 s.setStoryOwner(storyOwner.toString());
		 s.setOwnerId(ownerId.toString());
		 s.setStoryText(storyText.toString());
		 s.setStoryTime(storyTime.toString());
		 s.setLikes(0);
		 s.setOwnerType(ownerType.toString());
		 
		 Random rand = new Random();
		 int commentId= rand.nextInt();
		 s.setCommentId(commentId);	
			StringJoiner sj = new StringJoiner(" ,");
			 for (MultipartFile file : files) {

		            if (file.isEmpty()) {
		                continue; //next pls
		            }

		            try {
		            	 Random rand1 = new Random();
		        		 int img= rand1.nextInt();
		            	
		          String imgg = "img_" + String.valueOf(img)+".";
		                byte[] bytes = file.getBytes();
		                String fileName = file.getOriginalFilename();
		                String[] type = fileName.split("\\.");
		                Path path = Paths.get(UPLOADED_FOLDER + imgg + type[1]);
		                Files.write(path, bytes);

		                sj.add(imgg + type[1]);

		            } catch (IOException e) {
		                e.printStackTrace();
		            }

		        }

		        String uploadedFileName = sj.toString();
		        fileJson.put("uploadedFileName",uploadedFileName);
		        s.setImageNames(uploadedFileName.toString());
		        int storyid= Integer.parseInt(storyId);
              String updated = storyDao.updateStory(storyText,uploadedFileName,storyid);
        return new ResponseEntity<Object>(fileJson.toString(), HttpStatus.OK);}
		 catch(Exception e) {
				JSONObject userJson = new JSONObject();
				userJson.put("msg", e.getMessage());
				return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
			}
    }
 
	 
	 
	 @RequestMapping(value = "/getallstories", method = RequestMethod.GET)
	 public  ResponseEntity<Object> getallstories() throws IOException {
		 try {
		 JSONObject Total = new JSONObject();
		 List<String> imgArr= new ArrayList<String>();
	     int noOfImages=0;
		 List<Object[]> str = storyDao.getAllStories();
		 int count=0;
		 for(Object[] a : str) {
			 JSONObject d = new JSONObject();
			 JSONObject img = new JSONObject();
			 JSONObject commentList = new JSONObject();
			 JSONObject likeList = new JSONObject();
			d.put("storyId",a[0] );
			 d.put("storyOwner",a[1]);
			 d.put("ownerId",a[2]);
			 d.put("storyId",a[3]);
			 int storyId=(Integer)a[3];
			 Stories story = storyDao.getStorybyId(storyId);
			 List<Comment> comm = story.getComments();
			 int comment_count=0;
			 for(Comment i : comm) {
				 JSONObject comments = new JSONObject();
				 comments.put("commentName",i.getCommentName());
				 comments.put("commentText",i.getCommentText());
				 comments.put("commentId",i.getCommentId());
				 commentList.put("comment"+comment_count ,comments);
				 comment_count++;
			 }
			 d.put("commentList",commentList);
			 
			 List<Likes> likes = story.getLike_list();
			 int like_count=0;
			 for(Likes i : likes) {
				 JSONObject lik = new JSONObject();
				 lik.put("userName",i.getUserName());
				 lik.put("userId",i.getUserId());
				 lik.put("storyId",i.getStory().getStoryId());
				 lik.put("fullName",i.getFullName());
				 likeList.put("like_"+like_count ,lik);
				 like_count++;
			 }
			 d.put("LikeList",likeList);
			 
			 
			 
			 d.put("storyText",a[4]);
			 d.put("imageNames",a[5]);
			 String aa = String.valueOf(a[5]);
			 d.put("storyTime",a[6]);
			 d.put("likes",a[7]);
			 d.put("commentId",a[8]);
			 d.put("ownerType",a[9]);
			 
			 if(aa.contains(",")) {
				String[]  imges = aa.split(",");
				for(String i : imges) {
					String filePath= UPLOADED_FOLDER + i;
					ClassLoader classLoader = ClassLoader.getSystemClassLoader();
					//File file = ResourceUtils.getFile(filePath);
					File file = new File(filePath);
					String file_no ="file_"+(++noOfImages);
					// Path path = Paths.get(filePath);
					 //Path errorFilePath = Paths.get(FileSystems.getDefault().getPath(filePath).toString().replace("\\C:\\","C:\\"));
					byte[] data = new byte[(int) file.length()]; 
					  FileInputStream fis = new FileInputStream(file);
					  fis.read(data); //read file into bytes[]
					  fis.close();
					
				     
					//byte[] fileContent = Files.readAllBytes(file);
					 String encoded = Base64.getEncoder().encodeToString(data);
					  //img.put("fileName_"+noOfImages,aa);  
					  img.put(file_no,encoded);
				}
				  
			 }
			 else {
				 String filePath= UPLOADED_FOLDER + aa;
				 File file = new File(filePath);
				//	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
					//File file = ResourceUtils.getFile(filePath);
				 // imgArr.add("new text blah "+ aa);
					// Path path = Paths.get(filePath);
					// Path errorFilePath = Paths.get(FileSystems.getDefault().getPath(filePath).toString().replace("\\C:\\","C:\\"));
					 
					 byte[] data = new byte[(int) file.length()]; 
					  FileInputStream fis = new FileInputStream(file);
					  fis.read(data); //read file into bytes[]
					  fis.close();
				      //byte[] data = Files.readAllBytes(path);
				//	byte[] fileContent = Files.readAllBytes(file.toPath());
					  String encoded = Base64.getEncoder().encodeToString(data);
					//  img.put("fileName_0",aa);  
				  img.put("file_0",encoded);
			 }
			 d.put("file",img);
			 String story_count= "story_"+ (++count);
			 Total.put(story_count, d); 
		 }
		 
			
			return new ResponseEntity<Object>(Total.toString(), HttpStatus.OK);
		 }
		 catch(Exception e) {
				JSONObject userJson = new JSONObject();
				userJson.put("msg", e.getMessage());
				return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
			}
	 }
	 
	 
	 @RequestMapping(value = "/getProfileStories/{userId}", method = RequestMethod.GET)
	 public  ResponseEntity<Object> getProfileStories(@PathVariable int userId) throws IOException {
		 try {
		 JSONObject Total = new JSONObject();
		 List<String> imgArr= new ArrayList<String>();
	     int noOfImages=0;
		 List<Object[]> str = storyDao.getProfileStories(userId);
		 int count=0;
		 for(Object[] a : str) {
			 JSONObject d = new JSONObject();
			 JSONObject img = new JSONObject();
			 JSONObject commentList = new JSONObject();
			 JSONObject likeList = new JSONObject();
			d.put("storyId",a[0] );
			 d.put("storyOwner",a[1]);
			 d.put("ownerId",a[2]);
			 d.put("storyId",a[3]);
			 
			 int storyId=(Integer)a[3];
			 Stories story = storyDao.getStorybyId(storyId);
			 List<Comment> comm = story.getComments();
			 int comment_count=0;
			 for(Comment i : comm) {
				 JSONObject comments = new JSONObject();
				 comments.put("commentName",i.getCommentName());
				 comments.put("commentText",i.getCommentText());
				 comments.put("commentId",i.getCommentId());
				 commentList.put("comment"+comment_count ,comments);
				 comment_count++;
			 }
			 d.put("commentList",commentList);
			 
			 List<Likes> likes = story.getLike_list();
			 int like_count=0;
			 for(Likes i : likes) {
				 JSONObject lik = new JSONObject();
				 lik.put("userName",i.getUserName());
				 lik.put("userId",i.getUserId());
				 lik.put("storyId",i.getStory().getStoryId());
				 lik.put("fullName",i.getFullName());
				 likeList.put("like_"+like_count ,lik);
				 like_count++;
			 }
			 d.put("LikeList",likeList);
			 
			 
			 d.put("storyText",a[4]);
			 d.put("imageNames",a[5]);
			 String aa = String.valueOf(a[5]);
			 d.put("storyTime",a[6]);
			 d.put("likes",a[7]);
			 d.put("commentId",a[8]);
			 d.put("ownerType",a[9]);
			 
			 if(aa.contains(",")) {
				String[]  imges = aa.split(",");
				for(String i : imges) {
					String filePath= UPLOADED_FOLDER + i;
					ClassLoader classLoader = ClassLoader.getSystemClassLoader();
					//File file = ResourceUtils.getFile(filePath);
					File file = new File(filePath);
					String file_no ="file_"+(++noOfImages);
					// Path path = Paths.get(filePath);
					 //Path errorFilePath = Paths.get(FileSystems.getDefault().getPath(filePath).toString().replace("\\C:\\","C:\\"));
					byte[] data = new byte[(int) file.length()]; 
					  FileInputStream fis = new FileInputStream(file);
					  fis.read(data); //read file into bytes[]
					  fis.close();
					
				     
					//byte[] fileContent = Files.readAllBytes(file);
					 String encoded = Base64.getEncoder().encodeToString(data);
					  //img.put("fileName_"+noOfImages,aa);  
					  img.put(file_no,encoded);
				}
				  
			 }
			 else {
				 String filePath= UPLOADED_FOLDER + aa;
				 File file = new File(filePath);
				//	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
					//File file = ResourceUtils.getFile(filePath);
				 // imgArr.add("new text blah "+ aa);
					// Path path = Paths.get(filePath);
					// Path errorFilePath = Paths.get(FileSystems.getDefault().getPath(filePath).toString().replace("\\C:\\","C:\\"));
					 
					 byte[] data = new byte[(int) file.length()]; 
					  FileInputStream fis = new FileInputStream(file);
					  fis.read(data); //read file into bytes[]
					  fis.close();
				      //byte[] data = Files.readAllBytes(path);
				//	byte[] fileContent = Files.readAllBytes(file.toPath());
					  String encoded = Base64.getEncoder().encodeToString(data);
					//  img.put("fileName_0",aa);  
				  img.put("file_0",encoded);
			 }
			 d.put("file",img);
			 String story_count= "story_"+ (++count);
			 Total.put(story_count, d); 
		 }
		 
			
			return new ResponseEntity<Object>(Total.toString(), HttpStatus.OK);
		 }
		 catch(Exception e) {
				JSONObject userJson = new JSONObject();
				userJson.put("msg", e.getMessage());
				return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
			}
	 }
    
	 
	 @RequestMapping(value = "/addcomment", method = RequestMethod.POST)
	 public  ResponseEntity<Object> addComment(@RequestParam("storyId") String storyId ,
	    		@RequestParam("commentName") String commentName ,
	    		@RequestParam("commentText") String commentText,RedirectAttributes redirectAttributes) throws IOException {
		 	try {
		 JSONObject comment = new JSONObject();
		 comment.put("storyId",storyId);
		 comment.put("commentName",commentName);
		 comment.put("commentText",commentText);
		  int storyIdd=Integer.parseInt(storyId);
		 Stories story = storyDao.getStorybyId(storyIdd);
		 Comment c = new Comment();
		 c.setCommentName(commentName);
		 c.setCommentText(commentText);
		 story.addComment(c);
		 
		 storyDao.add_Comment(c);
			
		 return new ResponseEntity<Object>(comment.toString(), HttpStatus.OK);
		 	}
		 	catch(Exception e) {
				JSONObject userJson = new JSONObject();
				userJson.put("msg", e.getMessage());
				return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
			}
	 }
	 

	 @RequestMapping(value = "/addlike", method = RequestMethod.POST)
	 public  ResponseEntity<Object> addLike(@RequestParam("storyId") String storyId ,
	    		@RequestParam("userId") String userId,
	    		@RequestParam("userName") String userName,@RequestParam("fullName") String fullName,RedirectAttributes redirectAttributes) throws IOException {
		 	try {
		 JSONObject comment = new JSONObject();
		 comment.put("storyId",storyId);
		 comment.put("fullName",fullName);
		 comment.put("userId",userId);
		  int storyIdd=Integer.parseInt(storyId);
		 Stories story = storyDao.getStorybyId(storyIdd);
		Likes l = new Likes();
		l.setFullName(fullName);
		l.setUserId(userId);
		l.setUserName(userName);
		story.addLikes(l);
	
		 
		 storyDao.add_Likes(l);
			
		 return new ResponseEntity<Object>(comment.toString(), HttpStatus.OK);
		 	}catch(Exception e) {
				JSONObject userJson = new JSONObject();
				userJson.put("msg", e.getMessage());
				return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
			}
	 }
	 
	 

		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/deletestory/{storyId}",
	            method = RequestMethod.GET,
	            produces = "application/json")
		@ResponseBody
	    public ResponseEntity<Object> getUser(@PathVariable String storyId,  @RequestHeader HttpHeaders headers) throws Exception {
			
			try {
			int id = Integer.parseInt(storyId);
			int res = storyDao.delete_story(id);
			JSONObject userJson = new JSONObject();
			userJson.put("storyId", res);
			
	        return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
	    }
			catch(Exception e) {
				JSONObject userJson = new JSONObject();
				userJson.put("msg", e.getMessage());
				return new ResponseEntity<Object>(userJson.toString(), HttpStatus.OK);
			}
			}
	 
	 

	   

}
