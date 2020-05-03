package com.eco.neo.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.eco.neo.pojo.AuditRecord;
import com.eco.neo.pojo.Comment;
import com.eco.neo.pojo.Ticket;

public class AuditDAO extends DAO {

	public Ticket getticketbyId(int id) {
		
		Query query = getSession().createQuery("from Ticket where ticketId=:id");
		query.setInteger("id", id);
		return (Ticket)query.uniqueResult();
		
	}

	public AuditRecord addAuditRecord(AuditRecord a) {
		begin();
		getSession().save(a);
		commit();
		close();
		return (AuditRecord)a;
	}

	public List<AuditRecord> getAllAuditRecord() {
		
		Query ar = getSession().createQuery("from AuditRecord");
		
		return (List<AuditRecord>)ar.list();
	}

}
