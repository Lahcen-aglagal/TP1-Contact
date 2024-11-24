package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modele.Contact;
import modele.ContactService;
import modele.ContactServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactService contactService = new ContactServiceImpl();

    public ContactServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Contact> contacts = contactService.getAllContacts();
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("ContactList.jsp").forward(request, response);
     }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String email = request.getParameter("Contactemail");
            if (email != null) {
                contactService.deleteContact(email);
            }
            response.sendRedirect("ContactList.jsp");
        }
        else {
            String name = request.getParameter("name");
            String email = request.getParameter("email");

            if (contactService.addContact(new Contact(name, email))) {
                request.setAttribute("success", true);
            } else {
                request.setAttribute("success", false);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("ContactForm.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
