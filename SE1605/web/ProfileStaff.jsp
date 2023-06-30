<%@page import="Entity.Staff"%>
<%@include file= "component/header.jsp" %>
<%
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");
    Staff staff = daoStaff.getStaff(username);
%>
<section class="products section bg-gray" >
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-2 title text-center">
                <ul class="list-group">
                    <li class="list-group-item" style="margin-left: 5%;background-color: #1bbb1b"><a href="ProfileStaff">Edit profile</a></li>
                    <li class="list-group-item" style="margin-left: 5%"><a href="ChangePassword">Change password</a></li>
                </ul>
            </div>            
            <div class="col-lg-9 title text-center" style="margin-left: 4%">            
                <h2 class="text-center">Profile</h2> 
                <p style="color:red"><%=message == null ? "" : message%></p>
                <p style="color:green"><%=mess == null ? "" : mess%></p>
                <form action="ProfileStaff" method="POST" class="text-left clearfix">
                    <div class="form-group">
                        <label>First Name</label><br>
                        <input type="text" name="FirstName"  class="form-control" placeholder="Your First Name" value="<%=staff.getFirstName()%>" required>
                    </div>
                    <div class="form-group">
                        <label>Last Name</label><br>
                        <input type="text" name="LastName"  class="form-control" placeholder="Your Last Name" value="<%=staff.getLastName()%>" required>
                    </div>
                    <div class="form-group">
                        <label>Phone</label><br>
                        <input type="text" name="phone" class="form-control" placeholder="Phone format :(123) 456-7890 (optional)" value="<%=staff.getPhone() == null ? "" : staff.getPhone()%>">
                    </div>
                    <div class="form-group">
                        <label>Email</label><br>
                        <input type="email" name="email" class="form-control" placeholder="Your email" value="<%=staff.getEmail()%>" required>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-main text-center">Save</button>
                    </div>
                </form> 
            </div>
        </div>
    </div>
</section>
<%@include file= "component/footer.jsp" %>