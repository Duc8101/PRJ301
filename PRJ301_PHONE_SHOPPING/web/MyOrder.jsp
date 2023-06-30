<%@page import="Entity.Order"%>
<%@page import="java.util.List"%>
<%@include file= "component/header.jsp" %>
<%  // get attribute
    List<Order> list = (List<Order>) request.getAttribute("list");
    int No = (Integer) request.getAttribute("No");
    int pageSelected = (Integer) request.getAttribute("pageSelected");
    int numberPage = (Integer) request.getAttribute("number");
    String preURL = (String) request.getAttribute("previous");
    String nextURL = (String) request.getAttribute("next");
    String firstURL = (String) request.getAttribute("first");
    String lastURL = (String) request.getAttribute("last");
%>
<section class="user-dashboard page-wrapper" style="height: 160vh">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="dashboard-wrapper user-dashboard">
                    <div class="table-responsive" >
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Order Date</th>
                                    <th>Shipped Date</th>
                                    <th>Status</th>
                                    <th>Address</th>
                                    <th class="col-md-2 col-sm-3">Detail</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    String color;
                                    for (Order order : list) {
                                        if (order.getStatus().equals(Order.STATUS_SHIPPING)) {
                                            color = "blue";
                                        } else if (order.getStatus().equals(Order.STATUS_COMPLETED)) {
                                            color = "green";
                                        } else if (order.getStatus().equals(Order.STATUS_REJECTED)) {
                                            color = "red";
                                        } else {
                                            color = "";
                                        }
                                %>
                                <tr>
                                    <td><%=No%></td>
                                    <td><%=order.getOrderDate()%></td>
                                    <td><%=order.getShipDate() == null ? "" : order.getShipDate()%></td>
                                    <td style="color:<%=color%>"><%=order.getStatus()%></td>
                                    <td><%=order.getAddress()%></td>
                                    <td><a href="Detail?OrderID=<%=order.getOrderID()%>" class="btn btn-success">Detail</a></td>
                                </tr>
                                <%No++;%>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%
            if (numberPage > 1) {
        %>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center list-group" style="margin-left: 60%">
                <li class="page-item <%=pageSelected == 1 ? "disabled" : ""%>"><a class="page-link btn btn-primary btn-lg <%=pageSelected == 1 ? "disabled" : ""%>" href="<%=firstURL%>" aria-disabled="true" role="button">First</a></li>  
                <li class="page-item <%=pageSelected == 1 ? "disabled" : ""%>"><a class="page-link btn btn-primary btn-lg <%=pageSelected == 1 ? "disabled" : ""%>" href="<%=preURL%>" aria-disabled="true" role="button">Previous</a></li>  
                <li class="page-item"><a class="page-link btn btn-primary btn-lg" href="" aria-disabled="true" role="button"><%=pageSelected%>/<%=numberPage%></a></li>  
                <li class="page-item <%=pageSelected == numberPage ? "disabled" : ""%>" ><a class="page-link btn btn-primary btn-lg <%=pageSelected == numberPage ? "disabled" : ""%>" href="<%=nextURL%>" aria-disabled="true" role="button">Next</a></li>
                <li class="page-item <%=pageSelected == numberPage ? "disabled" : ""%>" ><a class="page-link btn btn-primary btn-lg <%=pageSelected == numberPage ? "disabled" : ""%>" href="<%=lastURL%>" aria-disabled="true" role="button">Last</a></li>
            </ul>
        </nav>
        <%}%>
    </div>
</section>
<%@include file= "component/footer.jsp" %>