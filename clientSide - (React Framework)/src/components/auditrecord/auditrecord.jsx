import React,{ Component } from "react";
import  * as ax  from '../../APIs/api';
import './auditrecord.scss';
import Axios from "axios";

class AuditRecord extends Component{
    state={
      
          }

componentDidMount(){
    let enterpriseId=sessionStorage.getItem("id");
    let type=sessionStorage.getItem("type");;
  
}
submit_audit(){
let dataa={}
dataa.water_ph = document.getElementById("water_ph").value
dataa.water_temp = document.getElementById("water_temp").value
dataa.oxygen_level = document.getElementById("oxygen_level").value
dataa.conductivity = document.getElementById("conductivity").value
dataa.ammonia_content = document.getElementById("ammonia_content").value


dataa.metal = document.getElementById("metal").value
dataa.pcb = document.getElementById("pcb").value
dataa.cdom = document.getElementById("cdom").value
dataa.ozone = document.getElementById("ozone").value
dataa.carbonmonoxide = document.getElementById("carbonmonoxide").value


dataa.sulphurdioxide = document.getElementById("sulphurdioxide").value
dataa.pm = document.getElementById("pm").value
dataa.aqi = document.getElementById("aqi").value
dataa.wastematerials = document.getElementById("wastematerials").value
dataa.notes = document.getElementById("notes").value
dataa.totalEcoIndex = document.getElementById("totalEcoIndex").value

dataa.ticketId=window.location.href.split("?")[1];

ax.submit_audit(dataa).then((payload)=>{
  if(payload != undefined){
    alert("Submitteed successfully");
  }
  else{
    alert("something unusual happened please try again submitting the form");
    window.location.reload();
  }
console.log(payload)
})
}

close_ticket(){
    let id= window.location.href.split("?")[1];
ax.close_ticket(id).then((payload)=>{
  if(payload != undefined){
    alert("Ticket Closed successfully");
  }
  else{
    alert("something unusual happened please try again");
    window.location.reload();
  }
    })
}

    render(){
        return(
            <div className="container-fluid" id="auditreport">
            <h3>Audit Record</h3>
           <div className="row">
               <div className="col-md-6">
                        <div>
                          <label htmlFor="e_password1">Water PH content</label>
                          <input type="text" name="e_password1" id="water_ph"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">Water Temperature</label>
                          <input type="text" name="e_password1" id="water_temp"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">Oxygen level</label>
                          <input type="text" name="e_password1" id="oxygen_level"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">Conductivity</label>
                          <input type="text" name="e_password1" id="conductivity"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">Ammonia Content</label>
                          <input type="text" name="e_password1" id="ammonia_content"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">Metal Content</label>
                          <input type="text" name="e_password1" id="metal"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">PCB</label>
                          <input type="text" name="e_password1" id="pcb"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">CDOM Test</label>
                          <input type="text" name="e_password1" id="cdom"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">Ozone</label>
                          <input type="text" name="e_password1" id="ozone"  />
                        </div>
               </div>
               <div className="col-md-6">
               <div>
                          <label htmlFor="e_password1">carbon monoxide</label>
                          <input type="text" name="e_password1" id="carbonmonoxide"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">sulphur dioxide</label>
                          <input type="text" name="e_password1" id="sulphurdioxide"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">Particle Matter</label>
                          <input type="text" name="e_password1" id="pm"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">Air Quality Index</label>
                          <input type="text" name="e_password1" id="aqi"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">Waste materials</label>
                          <input type="text" name="e_password1" id="wastematerials"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">Total Eco Index</label>
                          <input type="text" name="e_password1" id="totalEcoIndex"  />
                        </div>
                        <div>
                          <label htmlFor="e_password1">Notes</label>
                          <input type="text" name="e_password1" id="notes"  />
                        </div>
               </div>
              <button id="submit_audit" className="btn  btn-info" onClick={()=>{this.submit_audit()}}>Submit Audit</button>
              <button id="close_ticket" className="btn  btn-info" onClick={()=>{this.close_ticket()}}>Close Ticket</button>
           </div>
            </div>
       
       )
    }

}
 



export default AuditRecord;