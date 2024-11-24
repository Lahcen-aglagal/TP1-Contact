<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modele.Contact" %>

<jsp:include page="Components/Header.jsp">
    <jsp:param name="title" value="Contact List"/>
</jsp:include>

<body class="container mt-5">
    <h1 class="mb-4 text-center">Contact List</h1>
    <%
        List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");
        if (contacts != null && !contacts.isEmpty()) {
    %>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% for (Contact contact : contacts) { %>
            <tr>
                <td><%= contact.getName() %></td>
                <td><%= contact.getEmail() %></td>
                <td>
                    <form action="ContactServlet" method="post" style="display:inline;">
                        <input type="hidden" name="Contactemail" value="<%=contact.getEmail()%>">
                        <button type="submit" class="btn btn-outline-danger btn-sm" name="action" value="delete">Delete</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <%
        } else {
    %>
    <div class="alert alert-warning text-center" role="alert">
        <strong>DATABASE IS EMPTY</strong>
    </div>
    <%
        }
    %>
    <div class="text-center mt-4">
        <a href="ContactForm.jsp" class="btn btn-outline-primary">Back to Form</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
