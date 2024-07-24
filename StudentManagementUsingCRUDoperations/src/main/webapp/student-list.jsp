<%@ page import="java.util.List" %>
<%@ page import="com.studentManagement.model.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>
    <style>
        /* Basic styling for the page */
        body { 
            font-family: Arial, sans-serif; 
            background-color: #f4f4f4; 
            margin: 0; 
            padding: 0; 
        }
        .container { 
            width: 80%; 
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
        a { 
            text-decoration: none; 
            color: #007bff; 
            font-weight: bold; 
        }
        a:hover { 
            text-decoration: underline; 
        }
        table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-top: 20px; 
        }
        th, td { 
            padding: 10px; 
            border: 1px solid #ddd; 
            text-align: left; 
        }
        th { 
            background-color: #f4f4f4; 
        }
        .actions a { 
            margin-right: 10px; 
            color: #007bff; 
        }
        .actions a:hover { 
            text-decoration: underline; 
        }
        .add-new { 
            display: inline-block; 
            margin-bottom: 20px; 
            padding: 10px 20px; 
            background-color: #28a745; 
            color: white; 
            border-radius: 5px; 
            text-align: center; 
        }
        .add-new:hover { 
            background-color: #218838; 
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Student List</h2>
        <a href="new" class="add-new">Add New Student</a>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Date of Birth</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    // Retrieve the list of students from the request attribute
                    List<Student> listStudent = (List<Student>) request.getAttribute("listStudent");
                    
                    // Loop through each student and display their details in the table
                    for (Student student : listStudent) {
                %>
                <tr>
                    <td><%= student.getId() %></td>
                    <td><%= student.getName() %></td>
                    <td><%= student.getEmail() %></td>
                    <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(student.getDob()) %></td>
                    <td class="actions">
                        <a href="edit?id=<%= student.getId() %>">Edit</a>
                        <a href="delete?id=<%= student.getId() %>">Delete</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
