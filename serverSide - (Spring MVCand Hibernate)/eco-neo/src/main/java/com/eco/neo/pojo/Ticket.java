package com.eco.neo.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ticket") 
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId")
	private int ticketId;
	

	@Column(name = "ticketBy")
	private String ticketBy;
    
///// enterpise to which ticket is issued ---- enterprise id linked
	@Column(name = "ticketFor")
	private String ticketFor;
	
public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketBy() {
		return ticketBy;
	}

	public void setTicketBy(String ticketBy) {
		this.ticketBy = ticketBy;
	}

	public String getTicketFor() {
		return ticketFor;
	}

	public void setTicketFor(String ticketFor) {
		this.ticketFor = ticketFor;
	}

	public String getTicketTo() {
		return ticketTo;
	}

	public void setTicketTo(String ticketTo) {
		this.ticketTo = ticketTo;
	}

	public String getTicketSubject() {
		return ticketSubject;
	}

	public void setTicketSubject(String ticketSubject) {
		this.ticketSubject = ticketSubject;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public String getTicketTime() {
		return ticketTime;
	}

	public void setTicketTime(String ticketTime) {
		this.ticketTime = ticketTime;
	}

	public String getIndustrySector() {
		return industrySector;
	}

	public void setIndustrySector(String industrySector) {
		this.industrySector = industrySector;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getEcoRiskCategory() {
		return ecoRiskCategory;
	}

	public void setEcoRiskCategory(String ecoRiskCategory) {
		this.ecoRiskCategory = ecoRiskCategory;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	//// regulatory authority
	@Column(name = "ticketTo")
	private String ticketTo;
    

	@Column(name = "ticketSubject")
	private String ticketSubject;
    

	@Column(name = "ticketDescription")
	private String ticketDescription;
    

	@Column(name = "ticketTime")
	private String ticketTime;
    

	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	@Column(name = "regId")
	private int regId;

	@Column(name = "industrySector")
	private String industrySector;
	

	@Column(name = "responseMessage")
	private String responseMessage;
	

	@Column(name = "ecoRiskCategory")
	private String ecoRiskCategory;
	

	@Column(name = "ticketStatus")
	private String ticketStatus;
	
	 @ManyToOne(cascade=CascadeType.ALL)
	 @JoinColumn(name="enterpriseId")
	 private Enterprise enterprise;
	
}
