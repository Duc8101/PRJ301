<%@page import="Model.DAOProduct"%>
<%@page import="java.util.List"%>
<%@page import="Entity.Product"%>
<%@page import="Entity.OrderDetail"%>
<jsp:include page="/View/Shared/header.jsp"></jsp:include>
<%
    List<OrderDetail> list = (List<OrderDetail>) request.getAttribute("list");
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
                                    <th>Image</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    double sum = 0;
                                    for (OrderDetail detail : list) {
                                        Product product = dao.getProduct(detail.getProductID() + "");
                                        if (product != null) {
                                %>
                                <tr>
                                    <td><%=product.getProductName()%></td>
                                    <td> 
                                        <img src="<%=product.getImage()%>" alt="" width="50" height="50"> 
                                    </td>
                                    <td><%=product.getPrice()%></td>
                                    <td><%=detail.getQuantity()%></td>
                                    <td><%=product.getPrice() * detail.getQuantity()%></td>
                                </tr>
                                <%
                                    sum = sum + product.getPrice() * detail.getQuantity();
                                %>
                                <%}%>
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
<jsp:include page="/View/Shared/footer.jsp"></jsp:include>