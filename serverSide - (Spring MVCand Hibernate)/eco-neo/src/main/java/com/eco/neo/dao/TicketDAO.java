package com.eco.neo.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.eco.neo.pojo.Enterprise;
import com.eco.neo.pojo.Ticket;

public class TicketDAO extends DAO{

	public List<Ticket> getTicketsbyEID(int id) {
		
		Query query = getSession().createQuery("from Ticket");
		return (List<Ticket>)query.getResultList();
		
	}

	public void	updateResponseMsg(int ticketId, String resp_msg) {
		begin();
		String hql = "UPDATE Ticket set responseMessage=:resp_msg WHERE ticketId=:ticketId";
        Query query = getSession().createQuery(hql);
        query.setParameter("resp_msg", resp_msg);
        query.setParameter("ticketId", ticketId);
        int result = query.executeUpdate();
		commit();
		close();
	}

	public void closeTicket(int ticketId) {
		begin();
		String hql = "UPDATE Ticket set ticketStatus=:resp_msg WHERE ticketId=:ticketId";
        Query query = getSession().createQuery(hql);
        query.setParameter("resp_msg", "closed");
        query.setParameter("ticketId", ticketId);
        int result = query.executeUpdate();
		commit();
		close();
	}

}
