import React,{ Component } from "react";
import  * as ax  from '../../APIs/api';
import './myprofile.scss';
import Axios from "axios";
import Story from '../story/story';
class MyProfile extends Component{
    state={
            profData:{},
            stories: [],
            ownerType:""
          }

componentDidMount(){
    if(sessionStorage.getItem("id") === "" || sessionStorage.getItem("id") === undefined || sessionStorage.getItem("id") === null ){
        window.location.href="/login";
    }
    let userId=sessionStorage.getItem("id");
    let type=sessionStorage.getItem("type");;
    let payload1="";
    let payload2="";
    //  ax.getProfileData(userId,type).then((payload)=>{
    //      console.log(payload);
    //      if(payload==undefined){
    //       //  window.location.reload();
    //       this.setState({ loading: false })
    //      }
    //      else{
    //         payload1= payload;
    //      }
       

    //  })
     ax.profileStories(userId).then((payload)=>{
            console.log(payload);
            if(payload==undefined){
                window.location.reload();
            }
            else{
                payload2 = payload;
            this.setState({stories: payload2});
     
             }
          
     })


    console.log(window.location.href);
}

    render(){
        
        return(
            <div className="container-fluid">
            <div id="backgroundImage">
            </div>
            <div id="profileImage">
                <h2>{sessionStorage.getItem("fullName")}</h2>
                
            </div>
            <div className="container" id="stories">
        {Object.values(this.state.stories).map(item => <Story story={item}></Story> )}
        </div>
        
            
            </div>
       
       )
    }

}
 



export default MyProfile;