package com.bikkadit.service;

import java.util.List;

import com.bikkadit.dao.ContactDao;
import com.bikkadit.exception.NoDataFoundException;
import com.bikkadit.model.Contact;

public class ContactServiceImpl implements ContactService{
	private ContactDao contactDao;

	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao; 
	}

	@Override
	public String getNameById(Integer id) {
		String name = contactDao.findNameById(id);
		String formattedName = name.toUpperCase();
		return formattedName;
	}

	@Override
	public List<String> getAllContactName() {
		 List<String> names = contactDao.findNames();
	
		 if(!names.isEmpty()) {
			 return names;
		 }
		 return null;
	}

	@Override
	public Contact getContactById(Integer id) {
		Contact contact = contactDao.findById(id);
		
		
		if(contact == null) {
			throw new NoDataFoundException();
	}
	return contact;
	}
}
