<%@include file= "component/header.jsp" %>
<% // get attribute
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-2 title text-center">
                <ul class="list-group">
                    <li class="list-group-item" style="margin-left: 5%;background-color: #1bbb1b"><a href="Profile">Edit profile</a></li>
                    <li class="list-group-item" style="margin-left: 5%"><a href="ChangePassword">Change password</a></li>
                        <%
                            if (user.getRoleName().equals(User.ROLE_CUSTOMER)) {
                        %>
                    <li class="list-group-item" style="margin-left: 5%"><a href="ViewSeller">View profile seller</a></li>
                        <%}%>
                </ul>
            </div>            
            <div class="col-lg-9 title text-center" style="margin-left: 4%">            
                <h2 class="text-center">Profile</h2> 
                <p style="color:red"><%=message == null ? "" : message%></p>
                <p style="color:green"><%=mess == null ? "" : mess%></p>
                <form action="Profile" method="POST" class="text-left clearfix">
                    <div class="form-group">
                        <label>Full Name</label><br>
                        <input type="text" name="FullName"  class="form-control" placeholder="Your FullName" value="<%=user.getFullName()%>" required>
                    </div>
                    <div class="form-group">
                        <label>Gender</label><br>
                        <select name="gender">
                            <option value="<%=User.GENDER_MALE%>" <%=user.getGender().equalsIgnoreCase(User.GENDER_MALE) ? "selected" : ""%>>Male</option>
                            <option value="<%=User.GENDER_FEMALE%>" <%=user.getGender().equalsIgnoreCase(User.GENDER_FEMALE) ? "selected" : ""%>>Female</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Phone</label><br>
                        <input type="text" name="phone" class="form-control" placeholder="Your phone" value="<%=user.getPhone()%>" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label><br>
                        <input type="email" name="email" class="form-control" placeholder="Your email" value="<%=user.getEmail() == null ? "" : user.getEmail()%>" >
                    </div>
                    <%
                        if (user.getRoleName().equals(User.ROLE_CUSTOMER)) {
                    %>
                    <div class="form-group">
                        <label>Address</label><br>
                        <input type="text" name="address" class="form-control" placeholder="Your address" value="<%=user.getAddress()%>" required>
                    </div>   
                    <%} else {%>
                    <div class="form-group">
                        <label>Money</label><br>
                        <input type="text" name="money" class="form-control" value="<%=user.getMoney()%>" disabled>
                    </div>   
                    <%}%>
                    <div class="text-center">
                        <button type="submit" class="btn btn-main text-center">Save</button>
                    </div>
                </form> 
            </div>
        </div>
    </div>
</section>

<%@include file= "component/footer.jsp" %>
