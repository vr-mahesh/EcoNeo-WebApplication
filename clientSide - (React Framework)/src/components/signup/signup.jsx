import React,{ Component } from "react";
import {connect} from 'react-redux';
import { ACTION_reg_success,ACTION_reg_fail } from '../../actions/AllActions';
import  * as ax  from '../../APIs/api';
import './signup.scss';
class Signup extends Component{

    state={
      toggle:false,
       userName:"",
       password:"",
       userId:"",
       firstName:"",
       lastName:"",
       location:"",
       phone:"",
       walletAmount:"",
       role:""
    };

    register_enterprise(e){
      e.preventDefault();
        let enterprise ={
        };
        enterprise.userName=document.getElementById("e_userName").value;
        enterprise.password=document.getElementById("e_password1").value;
        enterprise.enterpriseName=document.getElementById("enterpriseName").value;
        enterprise.enterpriseLocation=document.getElementById("enterpriseLocation").value;
        enterprise.enterpriseCountry=document.getElementById("enterpriseCountry").value;

        enterprise.enterpriseType=document.getElementById("enterpriseType").value;
        enterprise.walletAmount ="10";
        enterprise.enterpriseCheck ="false";
        enterprise.industrySector=document.getElementById("industrySector").value;
        if( enterprise.userName =="" || enterprise.industrySector=="" || enterprise.enterpriseType=="" ||enterprise.enterpriseName=="" ||   enterprise.password==""|| enterprise.industrySector==""){
          alert("Please enter  Name , type and Sector")
         return;
        }

if(document.getElementById("e_password1").value == document.getElementById("e_password2").value){
  ax.registerEnterprise(enterprise).then((payload)=>{
    if(payload!=undefined){
    if(payload.userName!=""){
        alert(" User register success")
      // this.props.reg_success(payload);
    }
    else{
     //  this.props.reg_fail(payload);
       alert(" User  register fail")
    }}
    else{
       alert("User register fail");
    }
console.log(payload)
})
}
else{
alert("Password does not match... Please enter the password again");
}
}


    register_user(e){
      e.preventDefault();
        let user ={
        };
      user.userName=document.getElementById("userName").value;
      user.password=document.getElementById("password1").value;
      user.firstName=document.getElementById("firstName").value;
      user.lastName=document.getElementById("lastName").value;
      user.location=document.getElementById("location").value;
      user.phone=document.getElementById("phone").value;
      user.walletAmount ="1000";
      user.role=document.getElementById("role").value
      if(  user.userName =="" ||  user.lastName=="" || user.location=="" || user.firstName=="" ||  user.password=="" ||  user.phone >999999999 || user.phone ==""  ){
        alert("Please enter Name , type and Sector")
        return;
      }

if(document.getElementById("password1").value == document.getElementById("password2").value){
  ax.registerUser(user).then((payload)=>{
    if(payload!=undefined){
    if(payload.userName!=""){
        alert(" User register success")
      // this.props.reg_success(payload);
    }
    else{
     //  this.props.reg_fail(payload);
       alert(" User  register fail")
    }}
    else{
       alert("User register fail");
    }
console.log(payload)
})
}
else{
alert("Password does not match... Please enter the password again");
}
}

toggle =()=>{
  if(document.getElementById("UserRegistration").style.display!= "none"){
    document.getElementById("EnterpriseRegistration").style.display= "block";
    document.getElementById("UserRegistration").style.display= "none";
  }
  else{
    document.getElementById("EnterpriseRegistration").style.display= "none";
    document.getElementById("UserRegistration").style.display= "block";
  }
}
    render(){
return(
    <div className="reg_sec">
      
        <button class="btn btn-info switch aaa"  onClick={()=>{this.toggle()}}>Switch User Type</button>
        <div className="signup">
                    <div id="UserRegistration">
                    <h3>Register as User</h3>
                      <form>
                      <div><label htmlFor="userName">Email</label>
                          <input type="text" name="userName" id="userName" required />
                          
                        </div>
                        <div><label htmlFor="password1">Password</label>
                          <input type="password" name="password1" id="password1" required />
                          
                        </div>
                        <div> <label htmlFor="password2">Confirm Password</label>
                          <input type="password" name="password2" id="password2" required />
                         
                        </div>
                        <div> <label htmlFor="firstName">First Name</label>
                          <input type="text" name="firstName"  id="firstName" required />
                         
                        </div>
                        <div><label htmlFor="lastName">Last Name</label>
                          <input type="text" name="lastName" id="lastName" required />
                          
                        </div>
                        <div> <label htmlFor="phone">Phone</label>
                          <input type="number" max="99999999" name="phone" id="phone" required />
                         
                        </div>
                        <div> <label htmlFor="location">Location</label>
                          <input type="text" name="location" id="location" required />
                         
                        </div>
                        <div>  <label htmlFor="role">Role</label>
                          <input type="text" name="role" id="role" required />
                        
                        </div>
                        <button class="btn btn-info switch"  onClick={(e)=>{this.register_user(e)}}>Sign Up</button>
                        <a href="/login">Already have an account</a>
                      </form>
                </div>
                <div style={ {display: "none"}} id="EnterpriseRegistration">
                    <h3>Register as Enterprise</h3>
                      <form>
                      <div><label htmlFor="userName">Email</label>
                          <input type="text" name="e_userName" id="e_userName" required />
                          
                        </div>
                        <div><label htmlFor="e_password1">Password</label>
                          <input type="password" name="e_password1" id="e_password1" required />
                          
                        </div>
                        <div> <label htmlFor="e_password2">Confirm Password</label>
                          <input type="password" name="e_password2" id="e_password2" required />
                         
                        </div>
                        <div> <label htmlFor="enterpriseName">Enterprise Name</label>
                          <input type="text" name="enterpriseName"  id="enterpriseName" required />
                         
                        </div>
                        <div> <label htmlFor="enterpriseLocation">Location</label>
                          <input type="text" name="enterpriseLocation" id="enterpriseLocation" required />
                         
                        </div>
                        <div> <label htmlFor="enterpriseCountry">Country</label>
                          <input type="text" name="enterpriseCountry" id="enterpriseCountry" required />
                         
                        </div>
                        <div><label htmlFor="enterpriseType">Enterprise type </label>
                        <select name="enterpriseType" id="enterpriseType" required >
  <option value="Regulatory Authority">Regulatory Authority</option>
  <option value="NPO">NPO</option>
  <option value="Business Organization">Business Organization</option>
  <option value="Government">Government</option>
  <option value="NGO">NGO</option>
</select>
                          
                        </div>
                        <div>   <label htmlFor="industrySector">Industry Sector</label>
                        <select name="industrySector" id="industrySector" required >
  <option value="Chemical">Chemical</option>
  <option value="Mining">Mining</option>
  <option value="HealthCare">HealthCare</option>
  <option value="Aerospace">Aerospace</option>
  <option value="Defense">Defense</option>
  <option value="Services">Services</option>
  <option value="Utilities">Utilities</option>
  <option value="automotive">Automotive</option>
  <option value="energy">Energy</option>
  <option value="Software">Software</option>
  <option value="Food">Food</option>
</select>
                      
                       
                        </div>
                        <button  class="btn btn-info switch"  onClick={(e)=>{this.register_enterprise(e)}}>Sign Up</button>
                        <a href="/login">Already have an account</a>
                      </form>
                </div>
        </div>
         
      
    </div>
    
)
    }
}


// const mapStatetoProps=(state)=>{
// return{
//     userName:state.login.userName,
//     password:state.login.password,
//     userId:state.login.userId,
//     firstName:state.login.firstName,
//     lastName:state.login.lastName,
//     location:state.login.location,
//     phone:state.login.phone

// }
// }   
// const  matchDispatchtoProps= (dispatch)=>{
// return{
//     auth_success: (payload)=> {dispatch(ACTION_reg_success(payload))},
//     auth_fail: (payload)=> {dispatch(ACTION_reg_fail(payload))}
// }
// }

export default Signup;