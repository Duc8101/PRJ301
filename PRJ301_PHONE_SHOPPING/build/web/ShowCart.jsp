<%@page import="Entity.Product"%>
<%@page import="java.util.*"%>
<%@include file= "component/header.jsp" %>
<section class="page-header">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="content">
                    <h1 class="page-name">Show Cart</h1>
                </div>
            </div>
        </div>
    </div>
</section>

<% // get attribute 
    Map<Product, Integer> map = (Map<Product, Integer>) request.getAttribute("map");
    Set<Product> set = map.keySet();
    double sum = 0;
%>
<section class="user-dashboard page-wrapper" style="height: 100vh">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
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
                                    <th class="col-md-2 col-sm-3">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Product key : set) {
                                        int quantity = map.get(key);
                                        sum = sum + quantity * key.getPrice();
                                %>
                                <tr>
                                    <td><button class="btn"><a href="Cart?service=AddCart&ProductID=<%=key.getProductID()%>"><%=key.getProductName()%></a></button></td>
                                    <td><img src="<%=key.getImage()%>" alt="" width="50" height="50"></td>
                                    <td><%=quantity%></td>
                                    <td><%=key.getPrice()%></td>
                                    <td><%=(double) Math.round((quantity * key.getPrice() * 100)) / 100%></td>
                                    <td><a href="Cart?service=RemoveCart&ProductID=<%=key.getProductID()%>" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete?')"> Delete</a></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                        <h3>Total Amount: <%=(double) Math.round((sum * 100)) / 100%></h3>
                    </div> 

                </div>
            </div>
        </div>

        <a href="Cart?service=CheckOut" class="btn btn-success w-100 <%=map.isEmpty() ? "disabled" : ""%>" aria-disabled="true" role="button">check out</a>
    </div>

</section>
<%@include file= "component/footer.jsp" %>
