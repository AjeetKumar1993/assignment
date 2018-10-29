package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PersonDAO;
import com.entity.Person;

@Service
public class FriendsService {
	
	@Autowired
	private PersonDAO personDAO;
	
	public List<Person> getPersonsWithAtLeastOneFriend(){
		List<Person> personList = personDAO.getPersonsWithAtLeastOneFriend();
		return personList;
	}
	
	public List<Person> getPersonWithNoFriends(){
		List<Person> personList = personDAO.getPersonWithNoFriends();
		return personList;
	}
	
	public List<Person> getMutualFriends(long personId1, long personId2){
		List<Person> personList = personDAO.getMutualFriends(personId1, personId2);
		return personList;
	}
}
