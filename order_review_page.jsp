<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="andrei.Product" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Review</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/style1.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fork-awesome@1.1.7/css/fork-awesome.min.css" integrity="sha256-gsmEoJAws/Kd3CjuOQzLie5Q3yshhvmo7YNtBG7aaEY=" crossorigin="anonymous">
    <link rel="icon" href="img/Logo_JolliBytesComp_96x96.png" type="image/png" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body class="bg-background" style="background: url('img/test.png')">
<nav class="navbar navbar-expand-lg navbar-dark navbar-black bg-black fixed-top pt-4 pb-4 text-center">
    <div class="container">
        <a class="navbar-brand montblack" href="index.html">
            <img src="img\Logo_JolliBytesComp_96x96.png" width="40" height="40"/> JolliBytesComp
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto montlight" style="padding-right: 40px;">
                <li class="nav-item">
                    <a class="nav-link" href="index.html">Home</a>
                </li>
                <li class="nav-item dropdown montlight active">
                    <a class="nav-link dropdown-toggle" href="#" role="button" id="dropsupport" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Support 
                    </a>
                    <div class="dropdown-menu bg-dark bg-black" aria-labelledby="dropsupport">
                        <a class="dropdown-item text-white" href="contact.html">Contact Us</a>
                    </div>
                </li>
                <li class="nav-item dropdown montlight">
                    <a class="nav-link dropdown-toggle" href="#" role="button" id="dropaccount" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Account
                    </a>
                    <div class="dropdown-menu bg-dark bg-black" aria-labelledby="dropaccount">
                        <a class="dropdown-item text-white" href="login.html"><i class="fa fa-key" aria-hidden="true"></i> Login</a>
                        <a class="dropdown-item text-white" href="registration.html"><i class="fa fa-user-plus" aria-hidden="true"></i> Register</a>
                    </div>
                </li>
            </ul>
            <span class="navbar-text montlight">
                <form class="form-inline" action="SearchServlet" method="get">
                    <div class="input-group">
                        <input class="form-control" type="text" name="search" placeholder="Search" aria-label="Search" aria-describedby="Search Button">
                        <div class="input-group-append">
                            <button class="btn btn-outline-warning" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </form>
            </span>
        </div>
    </div>
</nav>
<style>
        body4 {
            background: url('img/test.png');
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container8 {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .table th,
        .table td {
            border: 1px solid #dee2e6;
            padding: 8px;
            text-align: left;
        }
        .table th {
            background-color: #f8f9fa;
        }
        .btn-send {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .btn-send:hover {
            background-color: #0056b3;
        }
    </style>
<body4>
	<div class="container6">
    <h2>Order Review</h2>
    <p>Thank you for your order! Please review the details below:</p>
    <table class="table">
        <thead>
            <tr>
                <th>Product ID</th>
                <th>Product Type</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Image</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Retrieve products from session
                List<Product> products = (List<Product>) session.getAttribute("products");
                if (products != null) {
                    for (Product product : products) {
            %>
                        <tr>
                            <td><%= product.getId() %></td>
                            <td><%= product.getProductType() %></td>
                            <td><%= product.getProductName() %></td>
                            <td><%= product.getPrice() %></td>
                            <td><%= product.getQuantity() %></td>
                            <td> <!-- Adjusted table row for the image column -->
           					 </td>
                        </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
    <tfoot>
        <tr>
            <!-- Display Total Price -->
            <th colspan="6" class="text-center"><h2>Total Price: ${totalPrice}</h2></th>
        </tr>
    </tfoot>
    <!-- Display delivery details here -->
    <p>User Details:</p>
<p><h6>Name:</h6> <%= request.getAttribute("name") %></p>
<p><h6>Address:</h6> <%= request.getAttribute("address") %></p>
<p><h6>Address1:</h6> <%= request.getAttribute("address1") %></p>
<p><h6>City:</h6> <%= request.getAttribute("city") %></p>
<p><h6>Phone:</h6> <%= request.getAttribute("phone") %></p>
<p><h6>Email:</h6> <%= request.getAttribute("email") %></p>
    <!-- Add payment options and proceed button here -->
    
    <form action="payment_details_form.jsp" method="post">
    <input type="submit" value="Proceed with Payment">
	</form>
</div>
</body4>
</html>