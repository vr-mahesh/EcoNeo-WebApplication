import React,{ Component } from "react";
import  * as ax  from '../../APIs/api';
import './profile.scss';
import Axios from "axios";
import Story from '../story/story';
class Profile extends Component{
    state={
            profData:{},
            stories: [],
            ownerType:""
          }

componentDidMount(){
    let userId=window.location.href.split("?")[1].split("/")[1];
    let type=window.location.href.split("?")[1].split("/")[0];
    let payload1="";
    let payload2="";
    //  ax.getProfileData(userId,type).then((payload)=>{
    //      console.log(payload);
    //      if(payload==undefined){
    //          window.location.reload();
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
                this.setState({profData: payload1,stories: payload2, ownerType: Object.values(payload2)[0].ownerType});
         
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
                {/* <h2>{sessionStorage.getItem("fullName")}</h2> */}
                
            </div>
            <div className="container" id="stories">
        {Object.values(this.state.stories).map(item => <Story story={item}></Story> )}
        </div>
            </div>
       
       )
    }

}
 



export default Profile;