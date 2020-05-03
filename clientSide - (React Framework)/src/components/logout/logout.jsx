import React,{ Component } from "react";
import {connect} from 'react-redux';
import { ACTION_auth_success,ACTION_auth_fail } from '../../actions/AllActions';
import  * as ax  from '../../APIs/api';
import './logout.css';
class Logout extends Component{

   


    componentDidMount(){
 
        sessionStorage.setItem("authtoken","");
        sessionStorage.setItem("type","user");
        sessionStorage.setItem("id","");
        sessionStorage.setItem("fullName","");
        sessionStorage.setItem("userName","");
        
 
    }

    loginAgain(){
        window.location.href="/login";
    }


    render(){
return(
    <div className="scsss">   <div>
    Thank you for using Eco Neo
      </div>
      <button  className="btn  btn-sm btn-info"  onClick={()=>{this.loginAgain()}}>Click here to Login Again</button>
      </div>
 
)
    }
}




export default Logout;