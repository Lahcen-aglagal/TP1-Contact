package test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.Connectiondb;
import modele.Contact;
import modele.ContactServiceImpl;
public class Main {

	public static void main(String[] args) {
		Connection conn;
		conn = Connectiondb.getConnection();
		
		ContactServiceImpl conS = new ContactServiceImpl() ; 
		List<Contact> contactlist = new ArrayList<Contact>();
		contactlist = conS.getAllContacts(); 
		for (Contact cont:contactlist) {
			System.out.println(cont.toString());
		}
		
	}

}
