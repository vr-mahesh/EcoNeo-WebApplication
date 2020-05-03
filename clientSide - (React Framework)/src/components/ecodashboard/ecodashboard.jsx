import React,{ Component } from "react";
import './ecodashboard.scss';
import  * as ax  from '../../APIs/api';
import * as jsPDF from 'jspdf';
import * as html2canvas from 'html2canvas';
import Accordion from 'react-bootstrap/Accordion'
class  Ecodashboard extends Component{
state={
    dataa:[],
    auditreport:[]
}

download_report=(e)=>{

}
 
handlePdf = () => {
    const input = document.getElementById('View_tickets');

    html2canvas(input)
        .then((canvas) => {
            const imgData = canvas.toDataURL('image/png');
            const pdf = new jsPDF('p', 'px', 'a4');
            var width = pdf.internal.pageSize.getWidth();
            var height = pdf.internal.pageSize.getHeight();

            pdf.addImage(imgData, 'JPEG', 0, 0, width, height);
            pdf.save("test.pdf");
        });
};
loadAuditReports(){
    var payload1={};
    ax.load_auditreport().then((payload)=>{
        console.log(payload)
       payload1=payload;
        document.getElementById("Audit_section").innerHTML="";
        let a=`<div class="row list_items header"><div class="col-md-2">Audit Record ID</div><div class="col-md-2">AQI</div><div class="col-md-2">totalecoindex</div><div class="col-md-2">Water PH</div></div>`
        ;
Object.values(payload).map((src)=>{
a+=`<div class="row list_items" id=${src.auditRecordId}><div class="col-md-2">${src.aqi}</div><div class="col-md-2">${src.aqi}</div><div class="col-md-2">${src.totalecoindex}</div><div class="col-md-2">${src.water_ph}</div><div class="col-md-2 download_report"></div></div>`
})
document.getElementById("Audit_section").innerHTML = a;

let items = document.querySelectorAll(".download_report");
items.forEach(function (item) {
            var button = document.createElement('button');
            button.innerHTML = 'Download Report';
            button.className="btn  btn-sm btn-info";
            button.onclick = function(e){
                console.log(e.target.parentElement.parentElement.id);
                Object.values(payload1).map((src)=>{
                    if(e.target.parentElement.parentElement.id == src.auditRecordId){

                        var doc = new jsPDF();
                       
                            doc.text(20, 10 + ( 10), 
                                "auditRecord Id: " + src.auditRecordId +"\n"+
                                

                                "aqi: " + src.aqi+"\n"+
                                "carbonmonoxide: " + src.carbonmonoxide+"\n"+
                                "notes: " + src.notes+"\n"+
                                "oxygenlevel: " + src.oxygenlevel+"\n"+
                                "ozone: " + src.ozone+"\n"+
                                "pm: " + src.pm+"\n"+
                                "sulphurdioxide: " + src.sulphurdioxide+"\n"+
                               
                                "totalecoindex: " + src.totalecoindex+"\n"+
                                "w_ammonia: " + src.w_ammonia+"\n"+
                                "water_ph : " + src.water_ph+"\n"+
                                "water_temp: " + src.water_temp
                                
                                );
                     
                        doc.save('AuditReport.pdf');
                    }
                })
              
            }
            item.appendChild(button);
        })

    })
}
viewTickets(e){

   Object.values(this.state.dataa).map((src)=>{
    let a="";
    if(src.enterpriseId== e.target.id){
        let divs=""
    Object.values(src.enterpriseTickets).map((e)=>{
            
                document.getElementById("View_tickets").innerHTML = "";
               divs+= `<h4>Ticket Subject :  ${e.ticketSubject}</h4><div class="container">   
           <div class="row roww"><div class="col-md-4 details_key">Ticket Raised by</div><div class="col-md-8 details_value">${e.ticketBy}</div></div>
           <div class="row roww"><div class="col-md-4 details_key">Regulatory Authority</div><div class="col-md-8 details_value">${e.ticketTo}</div></div>
           <div class="row roww"><div class="col-md-4 details_key">Eco Risk Category</div><div class="col-md-8 details_value">${e.ecoRiskCategory}</div></div>
           <div class="row roww"><div class="col-md-4 details_key">Ticket Status</div><div class="col-md-8 details_value">${e.ticketStatus}</div></div>
           <div class="row roww"><div class="col-md-4 details_key">Industry Sector</div><div class="col-md-8 details_value">${e.industrySector}</div></div>
         <hr/>
                </div>
                
                `
                
              
              
    })
    document.getElementById("View_tickets").innerHTML = divs;
    }
    })
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
          this.setState({dataa: payload})
        }
       })
//        ax.getAllAuditRecords().then((payload)=>{
//         console.log(payload);
//    })
    }
    render(){
return(
    <div className="container eco">
   
    <h3>Welcome to Eco Dashboard</h3><br/>
    <div className="info">Hello {sessionStorage.getItem("fullName")}, You can view the list and details of all the Tickets,Audit reports and also download them</div>
  <div className="row total_Section">
      <div className="col-md-6 left_Section">
         <div className="row title_sec"> <div className="col-md-5">Company Names</div> <div className="col-md-6">Number of Tickets</div>
         </div><div>{
            Object.values(this.state.dataa).map((src)=>{
                if(Object.values(src.enterpriseTickets).length >= 5){
                    return <div className="row red_e list_items"><div className="col-md-6"> {src.enterpriseName}</div><div className="col-md-3">{Object.values(src.enterpriseTickets).length}</div><div className="col-md-3"><button  className="btn btn-sm btn-info" id={src.enterpriseId} onClick={(e)=>{this.viewTickets(e)}}>View Tickets</button></div></div>

                }else if(Object.values(src.enterpriseTickets).length >= 3 && Object.values(src.enterpriseTickets).length < 5){
                    return <div className="row orange_e list_items"><div className="col-md-6"> {src.enterpriseName}</div><div className="col-md-3">{Object.values(src.enterpriseTickets).length}</div><div className="col-md-3"><button  className="btn btn-sm btn-info" id={src.enterpriseId}  onClick={(e)=>{this.viewTickets(e)}}>View Tickets</button></div></div>
 
                }else if(Object.values(src.enterpriseTickets).length >= 1 && Object.values(src.enterpriseTickets).length < 3){
                    return <div className="row yellow_e list_items"><div className="col-md-6"> {src.enterpriseName}</div><div className="col-md-3">{Object.values(src.enterpriseTickets).length}</div><div className="col-md-3"><button  className="btn  btn-sm btn-info"  id={src.enterpriseId} onClick={(e)=>{this.viewTickets(e)}} >View Tickets</button></div></div>

                }else{
                    return <div className="row green_e list_items"><div className="col-md-6"> {src.enterpriseName}</div><div className="col-md-3">{Object.values(src.enterpriseTickets).length}</div><div className="col-md-3"><button   className="btn  btn-sm btn-info" id={src.enterpriseId} onClick={(e)=>{this.viewTickets(e)}}>View Tickets</button></div></div>

                }
})

          }</div>
      </div>
      
      <div className="col-md-6 right_section" id="View_tickets">
         Tickets details
      </div>
      <div className="col-md-2">
      </div>
      <div className="col-md-6">
      <button className="btn btn-sm btn-info" onClick={()=>{this.loadAuditReports()}}>Load the Audit Reports</button>
      </div>

      <div className="col-md-3"><button className="btn btn-sm btn-info" onClick={()=>{this.handlePdf()}}>Download ticket Details</button></div>
     
  </div>
 
  <div className="container" id="Audit_section"></div>
    </div>
)
    }
}




export default Ecodashboard;