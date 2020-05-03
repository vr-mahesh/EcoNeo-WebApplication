import React,{ Component } from "react";
import {connect} from 'react-redux';
import { ACTION_auth_success,ACTION_auth_fail } from '../../actions/AllActions';
import  * as ax  from '../../APIs/api';
import './login.scss';
class Login extends Component{

    state={
       jwttoken:"",
       auth_state:"",
       username:"",
       password:""
    };


    componentDidMount(){
 
        sessionStorage.setItem("authtoken","");
        sessionStorage.setItem("type","user");
        sessionStorage.setItem("id","");
        sessionStorage.setItem("fullName","");
        sessionStorage.setItem("userName","");
        
 
    }
    authenticate_user(e){
      e.preventDefault();
    
      let username=document.getElementById("username").value;
      let password=document.getElementById("password").value;
      if(username =="" || password ==""){
          alert("Please enter the values");
          return;
      }
      ax.getuserdata(username,password).then((payload)=>{
         if(payload!=undefined){
        alert("login Successful");
        sessionStorage.setItem("authtoken",JSON.parse(payload.authToken).expiration)
        sessionStorage.setItem("type","user");
        sessionStorage.setItem("id",JSON.parse(payload.userId))
        sessionStorage.setItem("fullName",(payload.fullName))
        sessionStorage.setItem("userName",(payload.userName))
        window.location.href="/ecodashboard";
        }
         else{
          alert("Login Failed... Please Try Again with correct credentials");
         }
     console.log(payload)
      })
      
    }

    
    authenticate_enterprise(e){
      e.preventDefault();
      let username=document.getElementById("e_username").value;
      let password=document.getElementById("e_password").value;
      if(username =="" || password ==""){
        alert("Please enter the values");
        return;
    }
      ax.getenterprisedata(username,password).then((payload)=>{
      
        if(payload!=undefined){
          alert("login Successful");
          sessionStorage.setItem("authtoken",JSON.parse(payload.authToken).expiration)
          sessionStorage.setItem("type","enterprise");
          sessionStorage.setItem("id",JSON.parse(payload.enterpriseId))
          sessionStorage.setItem("fullName",(payload.enterpriseName))
          sessionStorage.setItem("userName",(payload.userName))
          window.location.href="/ecodashboard";
          }
           else{
            alert("Login Failed... PLease  enter the correct password");
           }
       console.log(payload)
      
      })
      
    }

log_out(){
  sessionStorage.setItem("authtoken","");
  sessionStorage.setItem("type","");
  sessionStorage.setItem("id","");
}
    
toggle =()=>{
  if(document.getElementById("UserLogin").style.display!= "none"){
    document.getElementById("EnterpriseLogin").style.display= "block";
    document.getElementById("UserLogin").style.display= "none";
  }
  else{
    document.getElementById("EnterpriseLogin").style.display= "none";
    document.getElementById("UserLogin").style.display= "block";
  }
}
    render(){
return(
    <div className="loogin">
        <button class="btn btn-info switch" onClick={()=>{this.toggle()}}>Switch User Type</button>
<div  id="UserLogin" className="login">
          <form>
            <h3> User Login</h3>
            <div>
            <label htmlFor="email">Email</label>
              <input type="text" maxLength="20" id="username" required />
              
            </div>
            <div><label htmlFor="password">Password</label>
              <input type="password" maxLength="20" id="password" required />
              
            </div>
            <button class="btn btn-info" onClick={(e)=>{this.authenticate_user(e)}}>Login</button>

            <a href="/signup">Don't have an account</a>
          </form>
        </div>
        <div   style={ {display: "none"}} id="EnterpriseLogin" className="login">
          <form>
            <h3> Enterprise Login</h3>
            <div><label htmlFor="email">Email</label>
              <input type="text" maxLength="20" id="e_username" required />
              
            </div>
            <div> <label htmlFor="password">Password</label>
              <input type="password" maxLength="20" id="e_password" required />
             
            </div>
            <button class="btn btn-info" onClick={(e)=>{this.authenticate_enterprise(e)}}>Login</button>

            <a href="/signup">Don't have an account</a>
          </form>
        </div>
        <div>{this.props.username}</div>
        <div>{this.props.auth_state}</div>
        {/* <button class="btn btn-info" onClick={()=>{this.log_out()}}>log out</button> */}
    </div>
    
)
    }
}


const mapStatetoProps=(state)=>{
return{
    auth_state:state.login.auth_state,
    username: state.login.username,
}
}   
const  matchDispatchtoProps= (dispatch)=>{
return{
    auth_success: (payload)=> {dispatch(ACTION_auth_success(payload))},
    auth_fail: (payload)=> {dispatch(ACTION_auth_fail(payload))}
}
}

export default connect(mapStatetoProps,matchDispatchtoProps)(Login);