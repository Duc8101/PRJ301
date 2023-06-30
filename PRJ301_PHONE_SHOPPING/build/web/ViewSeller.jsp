<%@include file= "component/header.jsp" %>
<%
    User userSeller = (User) request.getAttribute("user");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-2 title text-center">
                <ul class="list-group">
                    <li class="list-group-item" style="margin-left: 5%"><a href="Profile">Edit profile</a></li>
                    <li class="list-group-item" style="margin-left: 5%"><a href="ChangePassword">Change password</a></li>
                        <%
                            if (user.getRoleName().equals(User.ROLE_CUSTOMER)) {
                        %>
                    <li class="list-group-item" style="margin-left: 5%;background-color: #1bbb1b"><a href="ViewSeller">View profile seller</a></li>
                        <%}%>
                </ul>
            </div>            
            <div class="col-lg-9 title text-center" style="margin-left: 4%">            
                <h2 class="text-center">Information seller</h2> 
                <form method="POST" class="text-left clearfix">
                    <div class="form-group">
                        <input type="text" name="FullName"  class="form-control" placeholder="Your FullName" value="<%=userSeller.getFullName()%>" readonly required>
                    </div>
                    <div class="form-group">
                        <p>Gender:
                            <select name="gender" disabled>
                                <option value="<%=User.GENDER_MALE%>" <%=userSeller.getGender().equalsIgnoreCase(User.GENDER_MALE) ? "selected" : ""%>>Male</option>
                                <option value="<%=User.GENDER_FEMALE%>" <%=userSeller.getGender().equalsIgnoreCase(User.GENDER_FEMALE) ? "selected" : ""%>>Female</option>
                            </select>
                        </p>
                    </div>
                    <div class="form-group">
                        <input type="text" name="phone" class="form-control" placeholder="Your phone" value="<%=userSeller.getPhone()%>" readonly required>
                    </div>
                    <div class="form-group">
                        <input type="email" name="email" class="form-control" placeholder="Your email" readonly value="<%=userSeller.getEmail() == null ? "" : user.getEmail()%>" >
                    </div>         
                </form> 
            </div>
        </div>
    </div>
</section>

<%@include file= "component/footer.jsp" %>
