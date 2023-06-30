<%@page import="java.util.List"%>
<%@page import="Model.DAOStore"%>
<%@page import="Entity.Store"%>
<%@include file= "component/header.jsp" %>
<%
    Store store = (Store) session.getAttribute("store");
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");
    DAOStore daoStore = new DAOStore();
    List<String> list = daoStore.getAllState();
%>
<section class="products section bg-gray">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title" style="margin-left: 4%">
                <p style="color:red"><%=message == null ? "" : message%></p>
                <p style="color:green"><%=mess == null ? "" : mess%></p>
                <h2 class="text-center">Information</h2>
                <form class="text-left clearfix" action="EditStore" method="POST">
                    <div class="form-group">
                        <label>Store Name</label><br>
                        <input type="text" name="StoreName" class="form-control" required value="<%=store.getName()%>">
                    </div>
                    <div class="form-group">
                        <label>Phone</label><br>
                        <input type="text" name="phone" class="form-control" required value="<%=store.getPhone()%>">
                    </div>
                    <div class="form-group">
                        <label>Email</label><br>
                        <input type="email" name="email"  class="form-control" required value="<%=store.getEmail()%>">
                    </div>
                    <div class="form-group">
                        <label>Street</label><br>
                        <input type="text" name="street" class="form-control" required value="<%=store.getStreet()%>">
                    </div>
                    <div class="form-group">
                        <label>City</label><br>
                        <input type="text" name="city" class="form-control"  required value="<%=store.getCity()%>">
                    </div>
                    <div class="form-group">
                        <label>State</label><br>
                        <select disabled>
                            <%
                                for (String state : list) {
                            %>
                            <option <%=state.equals(store.getState()) ? "selected" : ""%>><%=state%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Zip Code</label><br>
                        <input type="text" name="zip" class="form-control"  readonly value="<%=store.getZipCode()%>">
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
