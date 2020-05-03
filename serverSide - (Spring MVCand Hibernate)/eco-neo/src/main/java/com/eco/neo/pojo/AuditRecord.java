package com.eco.neo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AuditRecord") 
public class AuditRecord {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditRecordId")
	private int auditRecordId;
	
	@OneToOne
	@JoinColumn(name = "ticketId")
	private Ticket ticketId;
    
    @Column(name = "water_ph")
  	private String water_ph;
    
    public int getAuditRecordId() {
		return auditRecordId;
	}


	public void setAuditRecordId(int auditRecordId) {
		this.auditRecordId = auditRecordId;
	}


	public Ticket getTicketId() {
		return ticketId;
	}


	public void setTicketId(Ticket ticketId) {
		this.ticketId = ticketId;
	}


	public String getWater_ph() {
		return water_ph;
	}


	public void setWater_ph(String water_ph) {
		this.water_ph = water_ph;
	}


	public String getWater_temp() {
		return water_temp;
	}


	public void setWater_temp(String water_temp) {
		this.water_temp = water_temp;
	}


	public String getWater_conductivity() {
		return water_conductivity;
	}


	public void setWater_conductivity(String water_conductivity) {
		this.water_conductivity = water_conductivity;
	}


	public String getOxygen_level() {
		return oxygen_level;
	}


	public void setOxygen_level(String oxygen_level) {
		this.oxygen_level = oxygen_level;
	}


	public String getW_ammonia() {
		return w_ammonia;
	}


	public void setW_ammonia(String w_ammonia) {
		this.w_ammonia = w_ammonia;
	}


	public String getW_metal() {
		return w_metal;
	}


	public void setW_metal(String w_metal) {
		this.w_metal = w_metal;
	}


	public String getW_pcb() {
		return w_pcb;
	}


	public void setW_pcb(String w_pcb) {
		this.w_pcb = w_pcb;
	}


	public String getW_cdom() {
		return w_cdom;
	}


	public void setW_cdom(String w_cdom) {
		this.w_cdom = w_cdom;
	}


	public String getOzone() {
		return ozone;
	}


	public void setOzone(String ozone) {
		this.ozone = ozone;
	}


	public String getAqi() {
		return aqi;
	}


	public void setAqi(String aqi) {
		this.aqi = aqi;
	}


	public String getCarbonmonoxide() {
		return carbonmonoxide;
	}


	public void setCarbonmonoxide(String carbonmonoxide) {
		this.carbonmonoxide = carbonmonoxide;
	}


	public String getSulphurdioxide() {
		return sulphurdioxide;
	}


	public void setSulphurdioxide(String sulphurdioxide) {
		this.sulphurdioxide = sulphurdioxide;
	}


	public String getPm() {
		return pm;
	}


	public void setPm(String pm) {
		this.pm = pm;
	}


	public String getWaste_materials() {
		return waste_materials;
	}


	public void setWaste_materials(String waste_materials) {
		this.waste_materials = waste_materials;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public String getTotal_eco_index() {
		return total_eco_index;
	}


	public void setTotal_eco_index(String total_eco_index) {
		this.total_eco_index = total_eco_index;
	}


	@Column(name = "water_temp")
  	private String water_temp;
    
    @Column(name = "water_conductivity")
  	private String water_conductivity;
    
    @Column(name = "oxygen_level")
  	private String oxygen_level;
    
    @Column(name = "w_ammonia")
  	private String w_ammonia;
    
    @Column(name = "w_metal")
  	private String w_metal;
    
    @Column(name = "w_pcb")
  	private String w_pcb;
    
    @Column(name = "w_cdom")
  	private String w_cdom;
    
    @Column(name = "ozone")
  	private String ozone;
    
    @Column(name = "aqi")
  	private String aqi;
    
    @Column(name = "carbonmonoxide")
  	private String carbonmonoxide;
    
    @Column(name = "sulphurdioxide")
  	private String sulphurdioxide;
    
    @Column(name = "pm")
  	private String pm;
    
    @Column(name = "waste_materials")
  	private String waste_materials;

    @Column(name = "notes")
  	private String notes;
    
 
    @Column(name = "total_eco_index")
  	private String total_eco_index;
    

}
