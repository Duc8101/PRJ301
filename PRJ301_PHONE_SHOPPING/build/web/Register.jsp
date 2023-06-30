<%@include file= "component/header.jsp" %>
<% // get attribute 
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title" style="margin-left: 4%">
                <p style="color:red"><%=message == null ? "" : message%></p>
                <p style="color:green"><%=mess == null ? "" : mess%></p>
                <h2 class="text-center">Create Account</h2>
                <form class="text-left clearfix" action="Register" method="POST">
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="FullName" name="FullName" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="Phone" name="phone" required>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control"  placeholder="Email (optional)" name="email">
                    </div>
                    <div class="form-group">
                        <p>Gender:
                            <select name="gender">
                                <option value="<%=User.GENDER_MALE%>"><%=User.GENDER_MALE%></option>
                                <option value="<%=User.GENDER_FEMALE%>"><%=User.GENDER_FEMALE%></option>
                            </select>
                        </p>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="Address" name="address" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="Username" name="username" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" name="password" required>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-main text-center">Create</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</section>
<%@include file= "component/footer.jsp" %>