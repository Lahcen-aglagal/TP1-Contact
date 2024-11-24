package modele;

import java.util.List;

public interface ContactService {
	boolean addContact(Contact contact);
    Contact getContactById(int Id);
    List<Contact> getAllContacts();
    boolean updateContact(Contact contact);
    public boolean deleteContact(String email) ;
}
