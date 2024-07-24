<%@ page import="java.util.Date" %>
<%@ page import="com.studentManagement.model.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Form</title>
    <style>
        /* Basic styling for the page */
        body { 
            font-family: Arial, sans-serif; 
            background-color: #f4f4f4; 
            margin: 0; 
            padding: 0; 
        }
        .container { 
            width: 50%; 
            margin: 20px auto; 
            background-color: #fff; 
            padding: 20px; 
            border-radius: 8px; 
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
        }
        h2 { 
            text-align: center; 
            color: #333; 
        }
        form { 
            margin: 0; 
            padding: 20px; 
            border: 1px solid #ddd; 
            border-radius: 8px; 
        }
        label { 
            display: block; 
            margin-bottom: 10px; 
            font-weight: bold; 
        }
        input[type=text], 
        input[type=date] { 
            width: calc(100% - 22px); 
            padding: 10px; 
            margin-bottom: 20px; 
            border: 1px solid #ccc; 
            border-radius: 5px; 
            font-size: 16px; 
        }
        input[type=submit], 
        .btn-edit, 
        .btn-delete { 
            padding: 10px 20px; 
            border: none; 
            border-radius: 5px; 
            color: white; 
            font-size: 16px; 
            cursor: pointer; 
            margin-right: 10px; 
        }
        input[type=submit] { 
            background-color: #4CAF50; 
        }
        input[type=submit]:hover { 
            background-color: #45a049; 
        }
        .btn-edit { 
            background-color: #2196F3; 
        }
        .btn-edit:hover { 
            background-color: #0b7dda; 
        }
        .btn-delete { 
            background-color: #f44336; 
        }
        .btn-delete:hover { 
            background-color: #d32f2f; 
        }
        .button-group { 
            text-align: center; 
            margin-top: 20px; 
        }
    </style>
</head>
<body>
    <div class="container">
        <h2><%= (request.getAttribute("student") == null) ? "Add New Student" : "Edit Student" %></h2>
        <form action="<%= (request.getAttribute("student") == null) ? "insert" : "update" %>" method="post">
            <input type="hidden" name="id" value="<%= (request.getAttribute("student") != null) ? ((Student)request.getAttribute("student")).getId() : "" %>">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= (request.getAttribute("student") != null) ? ((Student)request.getAttribute("student")).getName() : "" %>" required>

            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="<%= (request.getAttribute("student") != null) ? ((Student)request.getAttribute("student")).getEmail() : "" %>" required>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" value="<%= (request.getAttribute("student") != null) ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(((Student)request.getAttribute("student")).getDob()) : "" %>" required>

            <input type="submit" value="<%= (request.getAttribute("student") == null) ? "Add Student" : "Update Student" %>">
        </form>

        <!-- Additional buttons for Edit and Delete -->
        <div class="button-group">
            <% if (request.getAttribute("student") != null) { %>
                <a href="edit?id=<%= ((Student)request.getAttribute("student")).getId() %>" class="btn-edit">Edit</a>
                <a href="delete?id=<%= ((Student)request.getAttribute("student")).getId() %>" class="btn-delete">Delete</a>
            <% } %>
        </div>
    </div>
</body>
</html>
