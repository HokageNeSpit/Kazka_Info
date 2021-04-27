<%@ page import="kazka.model.FairyTale" %>
<%@ page import="kazka.model.DataBase" %>
<%--
  Created by IntelliJ IDEA.
  User: memlo
  Date: 5/7/2020
  Time: 6:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shotcut icon" type="image/jpg" href="icon.jpg">
    <title>Fairy Tales</title>
</head>
<body>
<%DataBase db = (DataBase) request.getAttribute("db"); %>
<%FairyTale fairyTale = (FairyTale) request.getAttribute("fairyTale"); %>
<nav>
    <h1>
        <center>Welcome to FairyTales.com</center>
    </h1>
</nav>

<%if (db.info) {%>
<div class="alert alert-success alertAutoHide" role="alert">
    Success!
</div>
<%}%>
<form method="post" class="myForm">
    <div class="controlPanel">
        <button type="submit" name="user" class="btn btn-outline-dark" value="<%= 1 %>">Admin</button>
        <button type="submit" name="user" class="btn btn-outline-success" value="<%= 0 %>">User</button>
    </div>
    <div class="mainContainer">
        <div class="list" id="list">
            <%int i = 0;%>
            <%for (FairyTale ft : db.fairyTales) {%>
            <h3>Fairy Tale Story: <%= ft.getName()%>.</h3>
            Rating: <%= ft.getRate()%> likes.
            <%if (!db.isAdmin && db.liked(i)) {%>
            <button type="submit" name="like" value="<%= i %>" class="btn btn-outline-danger"><i
                    class="fa fa-heart"></i></button>
            <% }%>
            <div class="descriptionList" id="descriptionList">
                <%= ft.getDescription()%>
            </div>
            <%if (db.isAdmin) {%>
            <button type="submit" name="delete" value="<%= i %>" class="btn btn-outline-danger">Delete</button>
            <a href="controller?id=<%= i %>" class="btn btn-outline-info">Edit</a>
            <% }%>
            <br>
            <br>
            <% i++;
            }%>
        </div>
        <%if (db.isAdmin) {%>
        <div class="admin">
      <span>
        <label for="exampleInputEmail1">Enter name of fairy tale:</label>
          <input type="text" class="form-control" name="nameOfFairyTale" id="exampleInputEmail1"
                 value="<%= fairyTale == null ? "" : fairyTale.getName() %>">
        <br>
      </span>
            <span>
            <label for="exampleFormControlTextarea1">Enter description of fairy tale:</label>
            <textarea class="form-control" id="exampleFormControlTextarea1"
                      name="description"><%= fairyTale == null ? "" : fairyTale.getDescription() %></textarea>
        <br>
      </span>
            <span>
        <button type="submit" name="add" value="<%= 1337 %>"
                class="btn btn-outline-primary"><%= fairyTale == null ? "Add" : "Save" %></button>
      </span>
            <br>
        </div>
        <% }%>
        <%if (!db.isAdmin) {%>
        <div class="user">
            List of favorite fairy tales:<br>
            <table>
                <%for (FairyTale ft : db.listOfUserLike) {%>
                <tr>
                    <td>
                        <%= ft.getName() %>
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
        <% }%>
    </div>
</form>
</body>
</html>
