<%@include file= "component/header.jsp" %>
<%
    String message = (String) request.getAttribute("message");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title" style="margin-left: 4%">
                <p style="color:red"><%=message == null ? "" : message%></p>
                <h2 class="text-center">Create Account</h2>
                <form class="text-left clearfix" action="ManagerStaff" method="POST">
                    <input type="hidden" name="service" value="AddStaff">
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="FirstName" name="FirstName" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="LastName" name="LastName" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="Phone format :(123) 456-7890 (optional)" name="phone">
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control"  placeholder="Email" name="email" required>
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
