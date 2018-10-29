package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Person;
import com.service.FriendsService;


@RestController
@RequestMapping(value="/find_friends")
public class FriendsController {

	@Autowired
	private FriendsService friendService;

   
	/**
	 * return all person which have at least one friend
	 */
	@RequestMapping(value = "/atleastonefriend", method = RequestMethod.GET)
	public void atLeastOneFriend(){
	  System.out.println("**atLeastOneFriend API started***");
	   
	  List<Person> personList = friendService.getPersonsWithAtLeastOneFriend();
	  for(Person person : personList){
		  System.out.println(person.getName());
	  }
		
	}
	
	/**
	 * return all person with no friend 
	 */
	@RequestMapping(value = "/withnofriend", method = RequestMethod.GET)
	public void withNoFriend(){
	  System.out.println("**withNoFriend API started***");
	  
	  List<Person> personList = friendService.getPersonWithNoFriends();
	  for(Person person : personList){
		  System.out.println(person.getName());
	  }
		
	}
	
	/**
	 * return list of mutual friends between two persons
	 */
	@RequestMapping(value = "/mutualfriends/{id1}/{id2}", method = RequestMethod.GET)
	public void mutualFriends(@PathVariable(value = "id1") long personId1,
			                  @PathVariable(value = "id2") long personId2 ){

		System.out.println("**mutualFriends API started***");
	   
		 List<Person> personList = friendService.getMutualFriends(personId1,personId2);
		  for(Person person : personList){
			  System.out.println(person.getName());
		  }
		
	}
	
}
