import React,{ Component } from "react";
import './story.scss';
import  * as ax  from '../../APIs/api';
//import Profile from '../profile/profile';
const Story =(props)=>{

    let comments =  Object.values(props.story.commentList).map((src)=>{
        return <div className="onecomment">
            <div className="commenter">{src.commentName}</div>
            <div   className="commenttext">{src.commentText}</div>
            <button className="btn btn-info" id={src.commentId} onClick={(e)=>{delete_comment(e)}} >delete</button>
            <hr/></div>
});

// let like_disable = Object.values(props.story.LikeList).map((src)=>{  
    
//     if(src.userId !== sessionStorage.getItem("id")){
  
//         return true

//     }
//     else{
//         return false

//     }
  
    
// });

let delete_story=(e)=>{
    console.log(e)
    let storyId = e.target.parentElement.parentElement.parentElement.id
    ax.del_story(storyId).then((payload)=>{
        if(payload !==undefined){
            console.log(payload);
            alert("image deleted successfully");
            window.location.reload();
           }
           else{
               window.location.href="/login"
           }
    })

}

let edit_story=(e)=>{
    if(e.target.nextElementSibling.style.display == "block"){
        e.target.nextElementSibling.style.display = "none";
        e.target.parentElement.parentElement.parentElement.style.height="67vh";
    }
    else{
        e.target.nextElementSibling.style.display = "block";
     
        e.target.parentElement.parentElement.parentElement.style.height="100vh"
    }
   
}

let confirm_edit=(e)=>{
console.log(e)
const formData = new FormData();
let fileData={};
fileData.storyId= e.target.parentElement.parentElement.parentElement.parentElement.id;
fileData.storyText=e.target.previousSibling.previousElementSibling.previousElementSibling.value;
if(fileData.storyText=="" || e.target.previousSibling.previousElementSibling.files.length > 2 ||  e.target.previousSibling.previousElementSibling.files.length == 0){
    alert(" Text cannot be empty and also you can upload maximum of 2 images")
    return;
}
let currentDate = new Date();
let date = currentDate.getDate();
let month = currentDate.getMonth(); 
let year = currentDate.getFullYear();
let dateString = date + "-" +(month + 1) + "-" + year;
fileData.storyTime = dateString;
fileData.storyOwner= sessionStorage.getItem("fullName")
fileData.ownerId= sessionStorage.getItem("id");
fileData.ownerType=sessionStorage.getItem("type");
for ( var key in fileData ) {
   formData.append(key, fileData[key]);
}  

//formData.append('fileData',fileData); 
let count =0;
for(let i = 0; i< e.target.previousSibling.previousElementSibling.files.length; i++) {
    if(e.target.previousSibling.previousElementSibling.files[i].name.split(".")[1] == "jpg" ){
        
    }
     else if(e.target.previousSibling.previousElementSibling.files[i].name.split(".")[1] == "jpeg" ){
        
    }
    else if (e.target.previousSibling.previousElementSibling.files[i].name.split(".")[1] == "png" ){
        
    }
    else{
    count++;
    }
    if(count>0){
        alert("allowed file types are jpg png nad jpeg");
        return;
    }
   formData.append('file', e.target.previousSibling.previousElementSibling.files[i])
}
    //  formData.append('file', );
      ax.update_image(formData).then((payload)=>{
          if(payload !==undefined){
           console.log(payload);
           alert("image updated successfully");
           window.location.reload();
          }
          else{
              window.location.href="/login"
          }

      }
      )
}


let like_button= ()=>{
    let aa = Object.values(props.story.LikeList).find(src=> src.userId == sessionStorage.getItem("id"))
    if(aa == undefined){
        return "btn btn-info"
    }
    else{
        return "btn btn-secondary"
    }
}



    let no_images = props.story.imageNames.includes(",");
    let images =  Object.values(props.story.file).map((src)=>{
              return <img src={"data:image/png;base64," + src} />
    });
   
let delete_comment=(e)=>{
 const comment_id = e.target.id;
    ax.delete_comment(comment_id).then((payload)=>{
        if(payload === undefined){
            alert("Something went wrong please try again");
           
            
        }else{
            alert("Comment Deleted Successfully");
            window.location.reload();
        }
    })
}

    let comment =(e)=>{
        const formData =  new FormData();
        const comment={};
        comment.storyId= e.target.parentElement.parentElement.id;
        comment.commentName=sessionStorage.getItem("fullName");
        comment.commentText =e.target.previousSibling.value;
      
        if( comment.commentText==""){
            alert("Please enter the comment");
            return;
        }
        for ( var key in comment ) {
            formData.append(key, comment[key]);
         }  
      
        ax.addComment(formData).then((payload)=>{
            if(payload === undefined){
                alert("Something went wrong please try again");
                
            }else{
                alert("Comment Added Successfully");
                window.location.reload();
            }
                console.log(payload)
        })
        
    } 

    let likes=(e)=>{
        if(e.target.className == "btn btn-secondary"){
            alert("You have Already Liked this picture");
           
        }
        else{
            const formData =  new FormData();
            const like={};
            like.storyId= e.target.parentElement.parentElement.id;
            like.userId=sessionStorage.getItem("id");
            like.userName =sessionStorage.getItem("userName");
            like.fullName =sessionStorage.getItem("fullName");
            for ( var key in like ) {
                formData.append(key, like[key]);
             }  
            
            ax.addLike(formData).then((payload)=>{
                if(payload === undefined){
                    alert("Something went wrong please try again");
                    
                }else{
                    alert("Liked Story Successfully");
                    window.location.href="/socialfeed";
                }
                    console.log(payload)
            }) 
        }
       
    } 

 return(
        <div className="each_story row" id={props.story.storyId} value={props.story.ownerId}>
        <div className="col-md-8 leftt">
           <div className="each_story_name"><a href={"/profile?"+props.story.ownerType+"/"+ props.story.ownerId}>{props.story.storyOwner}</a></div>
           <div className="each_story_text">{props.story.storyText}</div>
           <div className="each_story_imageSection">
            {  images }
        </div>
       
<button className={like_button()} type="button"   onClick={(e)=>{likes(e)}}>Like</button>
  {(props.story.ownerId == sessionStorage.getItem("id"))
  ?<div>
  <button className="btn btn-info" onClick={(e)=>{edit_story(e)}}>Edit  Story</button>
  <div style={{display: "none"}} className="edit_section">
      <input type="text" />
      <input type="file"   name="file" multiple /> <label>Upload Your File </label>
      <button className="btn btn-info confirm_edit" onClick={(e)=>{confirm_edit(e)}}>Confirm Edit</button>
  </div>
  <button className="btn btn-info del_story" onClick={(e)=>{delete_story(e)}}>Delete Story</button>
  </div>
  :
  <div></div>
  }
        
     

        
        </div>
        <div className="col-md-3 each_story_buttons">
        <div>{comments}</div>
        <input type="text" id="comment_box"/>
        <button className="comment btn btn-info" type="button"  onClick={(e)=>{comment(e)}} >Comment</button>
        </div>
        </div>
   
   )

};

function profile(e){
    console.log(e)
}
export default Story;