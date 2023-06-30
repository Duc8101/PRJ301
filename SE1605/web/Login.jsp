<%@include file= "component/header.jsp" %>
<section class="signin-page account">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="block text-center">
                    <h2 class="text-center">Welcome Back</h2>
                    <form class="text-left clearfix" action="Login" method="POST">
                        <div class="form-group">
                            <input type="text" class="form-control"  placeholder="Username" name="username" required>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Password" name="password" required>
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="remember" value="true">
                            <label>Remember me</label>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-main text-center">Login</button>
                        </div>
                    </form>
                    <p class="mt-20">New in this site ?<a href="Register"> Create New Account</a></p>
                    <% // get attribute
                        String message = (String) request.getAttribute("message");
                    %>
                    <p style="color: red"><%=message == null ? "" : message%></p>

                </div>
            </div>
        </div>
    </div>
</section>
<%@include file= "component/footer.jsp" %>