<%@page import="java.util.Map"%>
<%@page import="Const.ConstValue"%>
<%@page import="Entity.Order"%>
<%@page import="java.util.List"%>
<%@include file= "component/header.jsp" %>
<%  // get attribute
    List<Order> list = (List<Order>) request.getAttribute("list");
    Map<Integer, String> map = (Map<Integer, String>) request.getAttribute("map");
    int pageSelected = (Integer) request.getAttribute("pageSelected");
    int numberPage = (Integer) request.getAttribute("number");
    String preURL = (String) request.getAttribute("previous");
    String nextURL = (String) request.getAttribute("next");
    String firstURL = (String) request.getAttribute("first");
    String lastURL = (String) request.getAttribute("last");
    int No = (Integer) request.getAttribute("No");
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
                                    <th>Required Date</th>
                                    <th>Shipped Date</th>
                                    <th>Status</th>
                                    <th class="col-md-2 col-sm-3">Detail</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    String color;
                                    for (Order order : list) {
                                        if (order.getStatus() == ConstValue.INT_STATUS_PROCESS) {
                                            color = "blue";
                                        } else if (order.getStatus() == ConstValue.INT_STATUS_DONE) {
                                            color = "green";
                                        } else if (order.getStatus() == ConstValue.INT_STATUS_CANCEL) {
                                            color = "red";
                                        } else {
                                            color = "";
                                        }
                                %>
                                <tr>
                                    <td><%=No%></td>
                                    <td><%=order.getOrderDate()%></td>
                                    <td><%=order.getRequiredDate()%></td>
                                    <td><%=order.getShippedDate() == null ? "" : order.getShippedDate()%></td>
                                    <td style="color:<%=color%>"><%=map.get(order.getStatus())%></td>
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
            <ul class="pagination justify-content-center list-group" style="margin-left: 50%">
                <li class="page-item <%=pageSelected == 1 ? "disabled" : ""%>"><a class="page-link btn btn-primary btn-lg <%=pageSelected == 1 ? "disabled" : ""%>" href="<%=firstURL%>" aria-disabled="true" role="button">First</a></li>  
                <li class="page-item <%=pageSelected == 1 ? "disabled" : ""%>"><a class="page-link btn btn-primary btn-lg <%=pageSelected == 1 ? "disabled" : ""%>" href="<%=preURL%>" aria-disabled="true" role="button">Previous</a></li>
                <li class="page-item"><a href="" class="page-link btn btn-primary btn-lg"><%=pageSelected%>/<%=numberPage%></a></li>
                <li class="page-item <%=pageSelected == numberPage ? "disabled" : ""%>" ><a class="page-link btn btn-primary btn-lg <%=pageSelected == numberPage ? "disabled" : ""%>" href="<%=nextURL%>" aria-disabled="true" role="button">Next</a></li>
                <li class="page-item <%=pageSelected == numberPage ? "disabled" : ""%>" ><a class="page-link btn btn-primary btn-lg <%=pageSelected == numberPage ? "disabled" : ""%>" href="<%=lastURL%>" aria-disabled="true" role="button">Last</a></li>
            </ul>
        </nav>
        <%}%>                     
    </div>
</section>
<%@include file= "component/footer.jsp" %>