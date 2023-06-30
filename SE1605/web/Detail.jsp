<%@page import="Entity.OrderItem"%>
<%@page import="Model.DAOProduct"%>
<%@page import="Entity.Product"%>
<%@page import="java.util.List"%>
<%@include file= "component/header.jsp" %>
<%
    List<OrderItem> list = (List<OrderItem>) request.getAttribute("list");
    DAOProduct dao = new DAOProduct();
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
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    double sum = 0;
                                    for (OrderItem item : list) {
                                        Product product = dao.getProduct(item.getProductID() + "");
                                %>
                                <tr>
                                    <td><%=product.getProductName()%></td>
                                    <td><%=product.getPrice()%></td>
                                    <td><%=item.getQuantity()%></td>
                                    <td><%=product.getPrice() * item.getQuantity()%></td>
                                </tr>
                                <%
                                    sum = sum + product.getPrice() * item.getQuantity();
                                %>
                                <%}%>
                            </tbody>
                        </table>
                        <div style="color: orange; font-size: 20px">Grand Total = $<%=(double) Math.round(sum * 100) / 100%></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file= "component/footer.jsp" %>  
