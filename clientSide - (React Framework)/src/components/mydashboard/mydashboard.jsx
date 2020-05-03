import React,{ Component } from "react";
import  * as ax  from '../../APIs/api';
import './mydashboard.scss';
import Axios from "axios";

class MyDashboard extends Component{
    state={
           tickets:[],
          
          }

componentDidMount(){
    if(sessionStorage.getItem("id") === "" || sessionStorage.getItem("id") === undefined || sessionStorage.getItem("id") === null ){
        window.location.href="/login";
    }
    let enterpriseId=sessionStorage.getItem("id");
    let type=sessionStorage.getItem("type");;
    ax.getTicketsbyId(enterpriseId).then((payload)=>{
        if(payload==undefined){
            window.location.reload();
        }
        else{
this.setState({tickets: payload});
console.log(payload);
        }
    })
}
audit_Report(e){
   window.location.href="/auditrecord?"+e;
}
view_ticketDetails(ticketId){
  
    Object.values(this.state.tickets).map((e)=>{
        if(e.ticketId==ticketId){
            document.getElementById("TicketDetails").innerHTML = "";
            let divs= `<h4  class="details_key">Ticket Details : </h4><h4>${e.ticketSubject}</h4><div class="container">   
       <div class="row roww"><div class="col-md-4 details_key">Ticket Raised by</div><div class="col-md-8 details_value">${e.ticketBy}</div></div>
       <div class="row roww"><div class="col-md-4 details_key">Regulatory Authority</div><div class="col-md-8 details_value">${e.ticketTo}</div></div>
       <div class="row roww"><div class="col-md-4 details_key">Description</div><div class="col-md-8 details_value">${e.ticketDescription}</div></div>
       <div class="row roww"><div class="col-md-4 details_key">Eco Risk Category</div><div class="col-md-8 details_value">${e.ecoRiskCategory}</div></div>
       <div class="row roww"><div class="col-md-4 details_key">Ticket Status</div><div class="col-md-8 details_value">${e.ticketStatus}</div></div>
       <div class="row roww"><div class="col-md-4 details_key">Industry Sector</div><div class="col-md-8 details_value">${e.industrySector}</div></div>
       <div class="row roww"><div class="col-md-4 details_key">Response Message</div><div class="col-md-8 details_value"><input id="resp_msg" type="text" /></div></div>
       
            </div>
            
            `
            let t_id = e.ticketId;
            document.getElementById("TicketDetails").innerHTML = divs;
            var button = document.createElement('button');
            button.innerHTML = 'Submit Response';
            button.id=t_id;
            button.className="btn  btn-sm btn-info";
            button.onclick = function(event){
                let resp_text = document.getElementById("resp_msg").value
              ax.updateResponseMessage(event.target.id,resp_text).then((payload)=>{
              if(payload != undefined){
                  alert(" Response sent successfully")
              }
              else{
                  alert("Something went wrong please try again")
                  window.location.reload();
              }
              })
            
            };
            // where do we want to have the button to appear?
            // you can append it to another element just by doing something like
            // document.getElementById('foobutton').appendChild(button);
            document.getElementById("TicketDetails").appendChild(button);
       // return    <div className="ticket ticket_closed"><div className="ticket_param col-md-3">{e.ticketId}</div><div className="ticket_param col-md-3">{e.ticketSubject}</div><div className="ticket_param col-md-3">{e.ticketTime}</div><button id={e.ticketId}>View Details</button></div>
        }
       })
}

view_ticketDetails_REG(ticketId){

    Object.values(this.state.tickets).map((e)=>{
        if(e.ticketId==ticketId){
            document.getElementById("TicketDetails").innerHTML = "";
            let divs= `<h4  class="details_key">Ticket Details : </h4><h4>${e.ticketSubject}</h4><div class="container">   
       <div class="row roww"><div class="col-md-4 details_key">Ticket Raised by</div><div class="col-md-8 details_value">${e.ticketBy}</div></div>
       <div class="row roww"><div class="col-md-4 details_key">Regulatory Authority</div><div class="col-md-8 details_value">${e.ticketTo}</div></div>
       <div class="row roww"><div class="col-md-4 details_key">Description</div><div class="col-md-8 details_value">${e.ticketDescription}</div></div>
       <div class="row roww"><div class="col-md-4 details_key">Eco Risk Category</div><div class="col-md-8 details_value">${e.ecoRiskCategory}</div></div>
       <div class="row roww"><div class="col-md-4 details_key">Ticket Status</div><div class="col-md-8 details_value">${e.ticketStatus}</div></div>
       <div class="row roww"><div class="col-md-4 details_key">Industry Sector</div><div class="col-md-8 details_value">${e.industrySector}</div></div>
       
            </div>
            
            `
            let t_id = e.ticketId;
            document.getElementById("TicketDetails").innerHTML = divs;

       // return    <div className="ticket ticket_closed"><div className="ticket_param col-md-3">{e.ticketId}</div><div className="ticket_param col-md-3">{e.ticketSubject}</div><div className="ticket_param col-md-3">{e.ticketTime}</div><button id={e.ticketId}>View Details</button></div>
        }
       })
}

    render(){
        return(
            <div className="container-fluid" id="mydashboard">
            <div className="row">
            <div className="col-md-7">
<div className="row">
<div className="col-md-12 OpenTicketsSection" id="OpenTickets">
                <h5>Open Tickets</h5>
                <div id="open_tickets">
                <div className="ticket"><div className="ticket_param col-md-1"> Id</div><div className="ticket_param col-md-5">Ticket Subject</div><div className="ticket_param col-md-3">Ticket Status</div></div>
              {
                Object.values(this.state.tickets).map((e)=>{
 if(e.ticketStatus=="open" && e.enterpriseId == sessionStorage.getItem("id")){
    return  <div className="ticket ticket_open"><div className="ticket_param col-md-1">{e.ticketId}</div><div className="ticket_param col-md-5">{e.ticketSubject}</div><div className="ticket_param col-md-3">{e.ticketTime}</div><button className="btn  btn-sm btn-info"  id={e.ticketId} onClick={()=>{this.view_ticketDetails(e.ticketId)}}>View Details</button></div>
 }
})

                  
              }
                </div>
            </div>
            
            <div className="col-md-12 closedTicketsSection" id="ClosedTickets">
                <h5>Closed Tickets</h5>
                <div id="closed_tickets">
                <div className="ticket"><div className="ticket_param col-md-1"> Id</div><div className="ticket_param col-md-5">Ticket Subject</div><div className="ticket_param col-md-3">Ticket Status</div></div>
             
{

    Object.values(this.state.tickets).map((e)=>{
 if(e.ticketStatus=="closed"  && e.enterpriseId == sessionStorage.getItem("id")){
     return  <div className="ticket ticket_closed"><div className="ticket_param col-md-1">{e.ticketId}</div><div className="ticket_param col-md-5">{e.ticketSubject}</div><div className="ticket_param col-md-3">{e.ticketTime}</div><button id={e.ticketId}  className="btn  btn-sm btn-info" onClick={()=>{this.view_ticketDetails(e.ticketId)}}>View Details</button></div>
 }
})
    
}

                </div>
            </div>
            <div className="col-md-12 TicketDetails" id="auditReport">
            <h5>Audit Reports</h5>
            <div id="audit_report">
            <div className="ticket"><div className="ticket_param col-md-5">Ticket Subject</div><div className="ticket_param col-md-3">Ticket Status</div></div>
             
            {

Object.values(this.state.tickets).map((e)=>{
if(e.ticketStatus=="open" && e.regId==sessionStorage.getItem("id")){
 return  <div className="ticket ticket_yellow"><div className="ticket_param col-md-5">{e.ticketSubject}</div><div className="ticket_param col-md-3">{e.ticketStatus}</div><button id={e.ticketId} className="btn  btn-sm btn-info" onClick={()=>{this.view_ticketDetails_REG(e.ticketId)}}>View Details</button><button id={e.ticketId}  className="btn  btn-sm btn-info" onClick={()=>{this.audit_Report(e.ticketId)}}>Audit Report</button></div>
}
else if(e.ticketStatus=="closed" && e.regId==sessionStorage.getItem("id")){
    return  <div className="ticket ticket_blue"><div className="ticket_param col-md-5">{e.ticketSubject}</div><div className="ticket_param col-md-3">{e.ticketStatus}</div><button id={e.ticketId}  className="btn  btn-sm btn-info" onClick={()=>{this.view_ticketDetails_REG(e.ticketId)}}>View Details</button></div>
}
})

}
            </div>
           
            </div>
</div>
            </div>
            
            <div className="col-md-5 TicketDetails" id="TicketDetails">
            <h5>Ticket Details</h5>
            </div>
            
            </div>
            
            </div>
       
       )
    }

}
 



export default MyDashboard;