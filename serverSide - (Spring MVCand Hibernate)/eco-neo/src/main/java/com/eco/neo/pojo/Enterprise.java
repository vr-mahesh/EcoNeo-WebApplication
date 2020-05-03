package com.eco.neo.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Enterprise") 
public class Enterprise {

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getEnterpriseLocation() {
		return enterpriseLocation;
	}

	public void setEnterpriseLocation(String enterpriseLocation) {
		this.enterpriseLocation = enterpriseLocation;
	}

	public String getEnterpriseCountry() {
		return enterpriseCountry;
	}

	public void setEnterpriseCountry(String enterpriseCountry) {
		this.enterpriseCountry = enterpriseCountry;
	}

	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getIndustrySector() {
		return industrySector;
	}

	public void setIndustrySector(String industrySector) {
		this.industrySector = industrySector;
	}

	public String getEnterpriseCheck() {
		return enterpriseCheck;
	}

	public void setEnterpriseCheck(String enterpriseCheck) {
		this.enterpriseCheck = enterpriseCheck;
	}

	public List<Ticket> getTicket_list() {
		return ticket_list;
	}

	public void setTicket_list(List<Ticket> ticket_list) {
		this.ticket_list = ticket_list;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enterpriseId")
	private int enterpriseId;
	
    @Column(name = "enterpriseName")
	private String enterpriseName;
    
	
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "userName")
	private String userName;
    

    public String getEnterprisePassword() {
		return enterprisePassword;
	}

	public void setEnterprisePassword(String enterprisePassword) {
		this.enterprisePassword = enterprisePassword;
	}

	@Column(name = "enterprisePassword")
	private String enterprisePassword;
    
    
    @Column(name = "enterpriseLocation")
	private String enterpriseLocation;
    
    @Column(name = "enterpriseCountry")
	private String enterpriseCountry;
    
    @Column(name = "enterpriseType")
	private String enterpriseType;
    
    @Column(name = "industrySector")
	private String industrySector;
    
    @Column(name = "enterpriseCheck")
	private String enterpriseCheck;
    
    
    public String getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(String walletAmount) {
		this.walletAmount = walletAmount;
	}

	@Column(name = "walletAmount")
	private String walletAmount;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enterprise")
    private List<Ticket> ticket_list = new ArrayList<Ticket>();
    
    public void addTicket(Ticket ticket) {
    	ticket_list.add( ticket );
    	ticket.setEnterprise(this);
	}
}
