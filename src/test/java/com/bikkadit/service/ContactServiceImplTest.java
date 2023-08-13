package com.bikkadit.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bikkadit.dao.ContactDao;
import com.bikkadit.model.Contact;

class ContactServiceImplTest {


	public static ContactServiceImpl contactServiceImpl=null;
	 //here we are initialised with null becz 3 layer ko hm yaha ek hi jagah globally declared  kr rahe taki bar bar usko create nii krna pade 
 //contact servieimpl ko globally bna rahe hai 
	
	//niche hm 3 alaG ALAG MEthod me stubbing kr rahe hai ,prevoius project me ek hi me sab kuchh kiye the
	@BeforeAll
	public static void init()
	{
		//Creating Mocking 
		ContactDao daoproxy = EasyMock.createMock(ContactDao.class);

		//First Method : This is the Stubbing/Mocking for the getAllContactName (requirement list of names)
		List<String> list= new ArrayList<>();		
		list.add("rahul");
		list.add("sachin");
		list.add("sunil");
		EasyMock.expect(daoproxy.findNames()).andReturn(list);
		
		
		//Second Method : This is the Stubbing/Mocking for the getContactById (requirement Contact object)
 		Contact contact= new Contact();
 		contact.setContactId(101);
 		contact.setContactname("Rahul");
 		contact.setContactNumber(134564645);
 		EasyMock.expect(daoproxy.findById(101)).andReturn(contact);
 		
 		
 		//Third Method : This is the Stubbing /Mocking for the getNameById (Requirement Just String )
 		EasyMock.expect(daoproxy.findNameById(101)).andReturn("rahul");
 		
 		//Setting the interface implementation  daoproxy 
 		contactServiceImpl= new ContactServiceImpl();
 		contactServiceImpl.setContactDao(daoproxy);
 		//Switch the method of stubbing /dumping 
 		EasyMock.replay(daoproxy);
		
	}
	
	
	@Test
	public void getAllContactNameTest()
	{
		List<String> listnames = contactServiceImpl.getAllContactName();
		System.out.println(listnames);
		assertNotNull(listnames);
		
	}
	
	
	@Test
	public void getContactByIdTest()
	{
		Contact contact = contactServiceImpl.getContactById(101);
		System.out.println(contact);
		Integer actualresult = contact.getContactId();
		
		Integer expectedresult=101;
		
		//assertEquals(expectedresult, actualresult);
		assertNotNull(contact);
		//assertEquals(101, contact.getContactId());
		
		
	}
	
	
	@Test
	public void getNameByIdTest()
	{
		String name = contactServiceImpl.getNameById(101);
		System.out.println(name);
		assertNotNull(name);
	}
	
}
