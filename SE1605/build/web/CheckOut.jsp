<%@page import="Model.DAOStore"%>
<%@page import="Entity.Customer"%>
<%@page import="Model.DAOCustomer"%>
<%@page import="java.util.*"%>
<%@page import="Entity.Product"%>
<%@include file= "component/header.jsp" %>
<% // get attribute 
    Map<Product, Integer> map = (Map<Product, Integer>) request.getAttribute("map");
    Set<Product> set = map.keySet();
    double sum = 0;
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");
    DAOCustomer daoCustomer = new DAOCustomer();
    Customer customer = daoCustomer.getCustomer(username);
    DAOStore daoStore = new DAOStore();
    List<String> list = daoStore.getAllState();
%>
<section class="user-dashboard page-wrapper">
    <div class="container">
        <div class="row">
            <div class="dashboard-wrapper user-dashboard">
                <div class="table-responsive" >
                    <table class="table">
                        <thead>
                            <tr>   
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Product key : set) {
                                    int quantity = map.get(key);
                                    sum = sum + quantity * key.getPrice();
                            %>
                            <tr>
                                <td><%=key.getProductName()%></td>
                                <td><%=quantity%></td>
                                <td><%=key.getPrice()%></td>
                                <td><%=(quantity * key.getPrice())%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <h3>Total Amount: $<%=sum%></h3>
                </div> 
            </div>
            <p style="color:red"><%=message == null ? "" : message%></p>
            <p style="color:green"><%=mess == null ? "" : mess%></p>
            <form action="Cart" method="POST">
                <div class="form-group">
                    <label>First Name</label><br>
                    <input type="text" class="form-control"  placeholder="FirstName" name="FirstName" disabled value="<%=customer.getFirstName()%>">
                </div>
                <div class="form-group">
                    <label>Last Name</label><br>
                    <input type="text" class="form-control"  placeholder="LastName" name="LastName" disabled value="<%=customer.getLastName()%>">
                </div>
                <div class="form-group">
                    <label>Phone</label><br>
                    <input type="text" class="form-control"  placeholder="Phone format :(123) 456-7890 (optional)" name="phone" disabled value="<%=customer.getPhone() == null ? "" : customer.getPhone()%>">
                </div>
                <div class="form-group">
                    <label>Email</label><br>
                    <input type="email" class="form-control"  placeholder="Email" name="email" disabled value="<%=customer.getEmail()%>">
                </div>
                <div class="form-group">
                    <label>Street</label><br>
                    <input type="text" class="form-control"  placeholder="Street" name="street" disabled value="<%=customer.getStreet()%>">
                </div>
                <div class="form-group">
                    <label>City</label><br>
                    <input type="text" class="form-control"  placeholder="City" name="city" disabled value="<%=customer.getCity()%>">
                </div>
                <div class="form-group">
                    <label>State</label><br>
                    <select name="state" disabled>
                        <%
                            for (String state : list) {
                        %>
                        <option value="<%=state%>" <%=customer.getState().equals(state) ? "selected" : ""%>><%=state%></option>
                        <%}%>
                    </select>
                </div>
                <div class="form-group">
                    <label>Required date</label><br>
                    <input type="date" class="form-control" required  name="required">
                </div> 
                <button type="submit" class="btn btn-primary w-100" onclick="return confirm('Are you sure you want to check out?')" <%=mess == null ? "" : "disabled"%>>Check out</button>
            </form>

        </div>
    </div>

</section>
<%@include file="component/footer.jsp" %>
