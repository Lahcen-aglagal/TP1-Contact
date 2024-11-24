package modele;

import java.util.ArrayList;
import java.util.List;
import ConnectDB.Connectiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactServiceImpl implements ContactService {
    @Override
    public boolean addContact(Contact contact) {
        Connection conn = Connectiondb.getConnection();
        String query = "INSERT INTO contact (name, email) VALUES (?, ?)";
        try (PreparedStatement pr = conn.prepareStatement(query)) {
            pr.setString(1, contact.getName());
            pr.setString(2, contact.getEmail());
            if (pr.executeUpdate() > 0) {
                System.out.println("User " + contact.getName() + " inserted successfully.");
                return true; 
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return false; 
    }


    @Override
    public Contact getContactById(int Id) {
        Connection conn = null;
        try {
            conn = Connectiondb.getConnection();
            String query = "SELECT * FROM contact WHERE id = ?";
            try (PreparedStatement pr = conn.prepareStatement(query)) {
                pr.setInt(1, Id);
                ResultSet rs = pr.executeQuery();
                if (rs.next()) {
                    return new Contact(rs.getString("name"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Contact not found with name: " + Id);
        return null;
    }
    @Override
    public List<Contact> getAllContacts() {
    	Connection conn = Connectiondb.getConnection();
        String query = "SELECT * FROM contact";
        List<Contact> contacts = new ArrayList<>();
        try (PreparedStatement pr = conn.prepareStatement(query)) {
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                contacts.add(new Contact(rs.getString("name"), rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public boolean updateContact(Contact contact) {
        Connection conn = Connectiondb.getConnection();
        String query = "UPDATE contact SET email = ? WHERE name = ?";
        try (PreparedStatement pr = conn.prepareStatement(query)) {
            pr.setString(1, contact.getEmail());
            pr.setString(2, contact.getName());
            if (pr.executeUpdate() > 0) {
                System.out.println("Contact " + contact.getName() + " updated successfully.");
                return true ;
            } else {
                System.out.println("Contact not found for update: " + contact.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false ;
    }

    @Override
    public boolean deleteContact(String email) {
        Connection conn = null;
        PreparedStatement pr = null;
        try {
            conn = Connectiondb.getConnection(); 
            String query = "DELETE FROM contact WHERE email=?";
            
            pr = conn.prepareStatement(query); 
            pr.setString(1, email); 

            if (pr.executeUpdate() > 0) {
                System.out.println("Contact " + email + " deleted successfully.");
                return true; 
            } else {
                System.out.println("Contact not found for deletion: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } 
        return false; 
    }

}
