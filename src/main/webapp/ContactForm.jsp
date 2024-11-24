<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modele.Contact" %>

<jsp:include page="Components/Header.jsp">
    <jsp:param name="title" value="Add Contact"/>
</jsp:include>

<div class="d-flex justify-content-center align-items-center" style="min-height: 100vh; background-color: #f8f9fa;">
    <div class="formContainer border border-primary rounded p-4 bg-white shadow" style="width: 400px;">
        <div class="text-center mb-4">
            <i class="fas fa-address-book fa-3x text-primary"></i>
            <h2 class="mt-2">Add a New Contact</h2>
        </div>
        <form action="ContactServlet" method="post">
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="name" name="name" placeholder="Enter your name" required>
                <label for="name">Name</label>
            </div>
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                <label for="email">Email</label>
            </div>
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-outline-primary">Add</button>
                <button type="button" class="btn btn-outline-info" onclick="location.href='contact'">Contact List</button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="Components/footer.jsp" />
