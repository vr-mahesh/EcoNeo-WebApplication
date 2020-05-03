import React,{ Component } from "react";
import  * as ax  from '../../APIs/api';
import './ticketcenter.scss';
class TicketCenter extends Component{
state={
  enterprise:{}
}
  componentDidMount(){
    if(sessionStorage.getItem("id") === "" || sessionStorage.getItem("id") === undefined || sessionStorage.getItem("id") === null ){
      window.location.href="/login";
  }
ax.getallenterprise().then((payload)=>{
  if(payload==undefined){
    window.location.reload();
}
else{
  console.log(payload);
  this.setState({enterprise: payload})
}
})

  }
 

    raise_ticket(e){
        e.preventDefault();
          let ticket ={
          };
          ticket.ticketBy=document.getElementById("ticketBy").value;
          ticket.ticketDescription=document.getElementById("ticketDescription").value;
          ticket.industrySector=document.getElementById("industrySector").value;
          ticket.ecoRiskCategory=document.getElementById("ecoRiskCategory").value;
          ticket.ticketSubject=document.getElementById("ticketSubject").value;
          let currentDate = new Date();
          let date = currentDate.getDate();
          let month = currentDate.getMonth(); 
          let year = currentDate.getFullYear();
          let dateString = date + "-" +(month + 1) + "-" + year;
          ticket.ticketTime = dateString;

          ticket.ticketFor=document.getElementById("ticketFor").options[document.getElementById("ticketFor").selectedIndex].text
          ticket.ticketTo=document.getElementById("ticketTo").options[document.getElementById("ticketTo").selectedIndex].text
          ticket.enterpriseId = document.getElementById("ticketFor").value;
          ticket.regId=document.getElementById("ticketTo").value;

if(ticket.ticketBy =="" || ticket.regId  =="" || ticket.enterpriseId  =="" || ticket.ticketFor  =="" || ticket.ticketTo  =="" || ticket.ticketDescription  =="" || ticket.industrySector  =="" || ticket.ecoRiskCategory  =="" || ticket.ecoRiskCategory  =="" ||  ticket.ticketSubject =="" ){
alert("Please fill all the fields")
return;
}

             ax.raiseTicket(ticket).then((payload)=>{
      if(payload!=undefined){
      if(payload.userName!=""){
          alert(" Tickets raised successfully")
        // this.props.reg_success(payload);
      }
      else{
       //  this.props.reg_fail(payload);
       alert("Tickets raised failed")
      }}
      else{
        alert("Tickets raised failed")
      }
  console.log(payload)
  })
  }
  

render(){
return(
    <div className="tcc">
       
                    <h3>Raise a Ticket</h3>
                      <form>
                      <div><label htmlFor="ticketBy">ticket Issued by </label>
                          <input type="text" name="ticketBy" id="ticketBy" required />
                          
                        </div>
                        <div>
                        <label htmlFor="ticketFor">Ticket against organization</label>
                          <select name="ticketFor" id="ticketFor" required>
                         
                         { Object.values(this.state.enterprise).map((e)=>{
if(e.enterpriseType=="Business Organization"){
return <option value={e.enterpriseId}>{e.enterpriseName}</option>
}
})
}
</select>
                        </div>
                        <div>
                        
                          <label htmlFor="ticketTo">Concerned Regulatory Authority </label>
                          <select name="ticketTo" id="ticketTo" required >
                         
                         { Object.values(this.state.enterprise).map((e)=>{
if(e.enterpriseType== "Regulatory Authority"){
return <option value={e.enterpriseId}>{e.enterpriseName}</option>
}
})
}
</select>
                        
                        </div>
                        <div> <label htmlFor="ticketSubject">Ticket Subject</label>
                          <input type="text" name="ticketSubject"  id="ticketSubject" required />
                         
                        </div>
                        <div>  <label htmlFor="ticketDescription">Ticket Description</label>
                        <textarea name="ticketDescription" id="ticketDescription" required ></textarea>
                        
                        </div>
                        <div><label htmlFor="industrySector">Industry Sector</label>
                          <input type="text" name="industrySector" id="industrySector" required />
                          
                        </div>
                        <div> <label htmlFor="ecoRiskCategory">Eco Risk Category </label>
                          <input type="text" name="ecoRiskCategory" id="ecoRiskCategory" required />
                         
                        </div>
                        <button className="btn btn-info" onClick={(e)=>{this.raise_ticket(e)}}>Raise a ticket</button>
                      </form>
    </div>
)
}
}

export default TicketCenter;