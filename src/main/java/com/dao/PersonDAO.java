package com.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Person;


@Repository
@Transactional
public class PersonDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getPersonsWithAtLeastOneFriend(){
		List<Person> personList = new ArrayList<Person>();
		try{
			String sql = "Select id from total_friend where total_f=1";
			List<Object> list= getSession().createSQLQuery(sql).list();
		
		     for(Object id : list){
		    	 Long id2 = (long) ((BigInteger) id).intValue();
		    	personList.addAll((List<Person>) getSession().createQuery("select p from Person p where p.id=:Id")
		    			.setParameter("Id", id2).list());
		     }
		}catch(Exception e){
			System.err.println("Exception in getPersonsWithAtLeastOneFriend : "+e);
		}
		return personList;
	}
	@SuppressWarnings("unchecked")
	public List<Person> getPersonWithNoFriends(){
		List<Person> personList = new ArrayList<Person>();
		try{
			String sql = "Select id from total_friend where total_f=0";
			List<Object> list= getSession().createSQLQuery(sql).list();
		
		     for(Object id : list){
		    	 Long id2 = (long) ((BigInteger) id).intValue();
		    	personList.addAll((List<Person>) getSession().createQuery("select p from Person p where p.id=:Id")
		    			.setParameter("Id", id2).list());
		     }
		}catch(Exception e){
			System.err.println("Exception in getPersonWithNoFriends : "+e);
		}
		return personList;
	}
	@SuppressWarnings("unchecked")
	public List<Person> getMutualFriends(long personId1, long personId2){
		
		List<Person> personList = new ArrayList<Person>();
		try{
		
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT to_id  FROM friends WHERE from_id = ?");
			sb.append(" UNION ");
			sb.append(" SELECT from_id  FROM friends WHERE to_id = ? ");
 
			List<Object> list = getSession().createSQLQuery(sb.toString()).setParameter(0, personId1).setParameter(1, personId1).list();
			//System.out.println("list1 size :"+list);
			//
			StringBuffer sb2 = new StringBuffer();
			sb2.append("SELECT to_id  FROM friends WHERE from_id = ?");
			sb2.append(" UNION ");
			sb2.append(" SELECT from_id  FROM friends WHERE to_id = ? ");
			List<Object> list2 = getSession().createSQLQuery(sb2.toString()).setParameter(0, personId2).setParameter(1, personId2).list();
			//System.out.println("list2 size :"+list2);
			List<Object> listF = new ArrayList<Object>();
			for (Object id : list) {
		           if(list2.contains(id)) {
		           	listF.add(id);
		           }
		     }
			//System.out.println("listF size :"+listF);
		     for(Object id : listF){
		    	 Long id2 = (long) ((BigInteger) id).intValue();
		    	personList.addAll((List<Person>) getSession().createQuery("select p from Person p where p.id=:Id")
		    			.setParameter("Id", id2).list());
		    	
		     }
			
			
		}catch(Exception e){
			System.err.println("Exception in getMutualFriends : "+e);
		}

		  
		  return personList;
	}
}
