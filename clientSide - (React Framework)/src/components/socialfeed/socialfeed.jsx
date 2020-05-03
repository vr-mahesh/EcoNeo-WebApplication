import React,{ Component } from "react";
import axios from 'axios';
import {connect} from 'react-redux';
//import { ACTION_auth_success,ACTION_auth_fail } from '../../actions/AllActions';
import  * as ax  from '../../APIs/api';
import './socialfeed.scss';
import Story from '../story/story';
class SocialFeed extends Component{

    state={
       image_upload:"false",
       stories: []
    };

    componentDidMount() {
        if(sessionStorage.getItem("id") === "" || sessionStorage.getItem("id") === undefined || sessionStorage.getItem("id") === null ){
            window.location.href="/login";
        }
        var config = {
            headers: {"authtoken": sessionStorage.getItem("authtoken")}
          };
        axios.get('http://localhost:8080/neo/upload/getallstories',config)
          .then(res => {
              console.log(res.data);
              if(res.data === undefined || res.data===""){
                window.location.href="/login";
               
              }
              else{
                this.setState({stories: res.data});
              }
            
          });
      }

    uploadImage=(e)=>{
       // e.preventDefault();
       console.log(e)
        const formData = new FormData();
 let fileData={};
 fileData.storyText= document.getElementById("input_text").value;
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
if(document.getElementById("input_text").value =="" || document.getElementById("input_image").files.length > 2 ||  document.getElementById("input_image").files.length == 0 ){
    alert(" Text cannot be empty and also you can upload maximum of 2 images")
    return;
}
let count=0;
 //formData.append('fileData',fileData);
 for(let i = 0; i< document.getElementById("input_image").files.length; i++) {

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
    formData.append('file', document.getElementById("input_image").files[i])
}
     //  formData.append('file', );
       ax.upload_image(formData).then((payload)=>{
           if(payload !==undefined){
            console.log(payload);
            alert("image uploaded successfully");
            window.location.href="/socialfeed"
           }
           else{
               window.location.href="/login"
           }

       }
       )
      
    }

    render(){

return(
    <div>
 <div className="container">
            <div id="ssoo" className="row ssoo" >
                <div className="col-md-6">
                        <div className="files color">
                            <label>Upload Your  Story here </label>
                            <input type="file"  id="input_image" name="file" multiple /><br/>
                            <label>Info</label><input id="input_text" type="text"/>
                            <button className="btn btn-info ss" id="upload_image" onClick={this.uploadImage}> Upload  Image</button>
                        </div>
                </div>
            </div>
        </div>
        <div className="container" id="stories">
        {Object.values(this.state.stories).map(item => <Story story={item}></Story> )}
        </div>
    </div>
    
)
    }
}


// const mapStatetoProps=(state)=>{
// return{
//     auth_state:state.login.auth_state,
//     username: state.login.username,
// }
// }   
// const  matchDispatchtoProps= (dispatch)=>{
// return{
//     auth_success: (payload)=> {dispatch(ACTION_auth_success(payload))},
//     auth_fail: (payload)=> {dispatch(ACTION_auth_fail(payload))}
// }
// }

export default SocialFeed;