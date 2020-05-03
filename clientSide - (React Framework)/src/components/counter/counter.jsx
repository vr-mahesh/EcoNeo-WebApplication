import React,{ Component } from "react";
import {connect} from 'react-redux';
import { inc_counter,dec_counter } from '../../actions/AllActions';
import  * as ax  from '../../APIs/counterdata';
import * as jsPDF from 'jspdf'
import './counter.scss';
import mahesh from '../../images/econeo_home.PNG';
class Home extends Component{

    componentDidMount(){
        // var employees = [
        //     {"firstName":"John", "lastName":"Doe"}, 
        //     {"firstName":"Anna", "lastName":"Smith"},
        //     {"firstName":"Peter", "lastName":"Jones"}
        // ];
        
        // var doc = new jsPDF();
        // employees.forEach(function(employee, i){
        //     doc.text(20, 10 + (i * 10), 
        //         "First Name: " + employee.firstName +
        //         "Last Name: " + employee.lastName);
        // });
        // doc.save('Test.pdf');

    }
    render(){
return(
    <div>
     <div className="Main_Image">
         <div className="heading"><h1>ECO-NEO</h1></div>
         <div className="desc"> <h5>Eco - Welfare Web platform that helps create awareness about environmental
laws and tracks environmental issues caused by Organizations or Industries and reporting them
to respective authorities. It also includes a social feed.</h5></div>
     </div>
    </div>
)
    }
}




export default Home;