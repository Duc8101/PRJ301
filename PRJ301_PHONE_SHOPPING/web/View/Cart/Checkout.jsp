<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="Entity.Product"%>
<jsp:include page="/View/Shared/header.jsp"></jsp:include>
<% // get attribute 
    Map<Product, Integer> map = (Map<Product, Integer>) request.getAttribute("map");
    Set<Product> set = map.keySet();
    double sum = 0;
%>
<section class="user-dashboard page-wrapper" style="height: 100vh">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="dashboard-wrapper user-dashboard">
                    <div class="table-responsive" >
                        <table class="table">
                            <thead>
                                <tr>   
                                    <th>Name</th>
                                    <th>Image</th>
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
                                    <td><img src="<%=key.getImage()%>" alt="" width="50" height="50"></td>
                                    <td><%=quantity%></td>
                                    <td><%=key.getPrice()%></td>
                                    <td><%=(double) Math.round((quantity * key.getPrice() * 100)) / 100%></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                        <h3>Total Amount: $<%=(double) Math.round((sum * 100)) / 100%></h3>
                    </div> 

                </div>
            </div>
            <%// get attribute
                String mess = (String) request.getAttribute("mess");
                String message = (String) request.getAttribute("message");
                String address = (String) request.getAttribute("address");
                String readonly = mess == null ? "" : "readonly";
            %>
            <div class="col-md-4">
                <h3>Billing Address</h3>
                <form action="Cart?service=Checkout" method="POST">
                    <div class="col-12">
                        <label for="Address" class="form-label">Address</label>
                        <input type="text" class="form-control" value="<%=address == null ? "" : address%>" name="address" placeholder="Your delivery address" <%=readonly%> required>
                    </div>
                    <br>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary w-100" onclick="return confirm('Are you sure you want to check out?')" <%=mess == null ? "" : "disabled"%>>Check out</button>
                        <p style="color:green"><%=mess == null ? "" : mess%></p> 
                        <p style="color:red"><%=message == null ? "" : message%></p>
                        <%  // if check out
                            if (mess != null) {
                                for (Product key : set) {
                                    session.removeAttribute(key.getProductID() + "");
                                }
                            }
                        %>

                    </div>
                </form>

            </div>        
        </div>
    </div>

</section>
<jsp:include page="/View/Shared/footer.jsp"></jsp:include>