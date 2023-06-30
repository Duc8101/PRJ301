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
                    <li class="list-group-item" style="margin-left: 5%"><a href="Profile">Edit profile</a></li>
                    <li class="list-group-item" style="margin-left: 5%;background-color: #1bbb1b"><a href="ChangePassword">Change password</a></li>
                        <%
                            if (user.getRoleName().equals(User.ROLE_CUSTOMER)) {
                        %>
                    <li class="list-group-item" style="margin-left: 5%"><a href="ViewSeller">View profile seller</a></li>
                        <%}%>
                </ul>
            </div>            
            <div class="col-lg-9 title text-center" style="margin-left: 4%">
                <p style="color:red"><%=message == null ? "" : message%></p>
                <p style="color:green"><%=mess == null ? "" : mess%></p>
                <form action="ChangePassword" method="POST" class="text-left clearfix">
                    <div class="form-group">
                        <input type="password" name="old" class="form-control" placeholder="Current Password" required>
                    </div>
                    <div class="form-group">
                        <input type="password" name="new" class="form-control" placeholder="New Password" required>                     
                    </div>
                    <div class="form-group">
                        <input type="password" name="confirm" class="form-control" placeholder="Confirm Password" required>                    
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
