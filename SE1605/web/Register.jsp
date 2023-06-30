<%@page import="Entity.Store"%>
<%@page import="java.util.List"%>
<%@page import="Model.DAOStore"%>
<%@include file= "component/header.jsp" %>
<%
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");
    DAOStore daoStore = new DAOStore();
    List<String> list = daoStore.getAllState();
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title" style="margin-left: 4%">
                <p style="color:red"><%=message == null ? "" : message%></p>
                <p style="color:red"><%=mess == null ? "" : mess%></p>
                <h2 class="text-center">Create Account</h2>
                <form class="text-left clearfix" action="Register" method="POST">
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
                        <input type="text" class="form-control"  placeholder="Street" name="street" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="City" name="city" required>
                    </div>
                    <div class="form-group">
                        <select name="state">
                            <%
                                for (String state : list) {
                            %>
                            <option value="<%=state%>"><%=state%></option>
                            <%}%>
                        </select>
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