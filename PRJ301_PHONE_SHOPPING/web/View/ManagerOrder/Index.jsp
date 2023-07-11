<%@page import="Const.ConstValue"%>
<%@page import="Entity.User"%>
<%@page import="Entity.Order"%>
<%@page import="java.util.List"%>
<%@page import="Model.DAOUser"%>
<jsp:include page="/View/Shared/header.jsp"></jsp:include>
<%
    DAOUser dao = new DAOUser();
    String status = (String) request.getAttribute("status");
    int pageSelected = (Integer) request.getAttribute("pageSelected");
    int numberPage = (Integer) request.getAttribute("number");
    String preURL = (String) request.getAttribute("previous");
    String nextURL = (String) request.getAttribute("next");
    String firstURL = (String) request.getAttribute("first");
    String lastURL = (String) request.getAttribute("last");
    List<Order> listOrder = (List<Order>) request.getAttribute("list");
%>
<section class="products section bg-gray" style="height: 160vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title">
                <form style="margin-left:1%" action="ManagerOrder">
                    <select name="status">
                        <%
                            for (String Status : ConstValue.LIST_STATUS) {
                        %>
                        <option value="<%=Status%>" <%=Status.equals(status) ? "selected" : ""%>><%=Status%></option>
                        <%}%>
                    </select>
                    <button class="btn btn-outline-success" type="submit">Search Order</button>
                </form>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>User</th>
                            <th>Order Date</th>
                            <th>Ship Date</th>
                            <th>Address</th>
                            <th>Status</th>
                            <th>View</th>
                            <th>Detail</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            String color;
                            for (Order order : listOrder) {
                                User user = dao.getUser(order.getUserID());
                                if (order.getStatus().equals(ConstValue.STATUS_SHIPPING)) {
                                    color = "blue";
                                } else if (order.getStatus().equals(ConstValue.STATUS_COMPLETED)) {
                                    color = "green";
                                } else if (order.getStatus().equals(ConstValue.STATUS_REJECTED)) {
                                    color = "red";
                                } else {
                                    color = "";
                                }
                                if (user != null) {
                        %>
                        <tr>
                            <td><%=order.getOrderID()%></td>
                            <td><%=user.getUsername()%></td>       
                            <td><%=order.getOrderDate()%></td>
                            <td><%=order.getShipDate() == null ? "" : order.getShipDate()%></td>
                            <td><%=order.getAddress()%></td>
                            <td style="color:<%=color%>"><%=order.getStatus()%></td>
                            <td><a class="btn btn-info" href="ManagerOrder?service=ViewCustomer&username=<%=user.getUsername()%>" aria-disabled="true" role="button">View</a></td>
                            <td>
                                <a class="btn btn-primary" href="ManagerOrder?service=Detail&OrderID=<%=order.getOrderID()%>">Detail</a>
                            </td>
                            <td>
                                <a class="btn btn-success <%=order.getStatus().equals(ConstValue.STATUS_SHIPPING) || order.getStatus().equals(ConstValue.STATUS_NEW) ? "" : "disabled"%>" href="ManagerOrder?service=EditOrder&OrderID=<%=order.getOrderID()%>" aria-disabled="true" role="button">Edit</a>
                            </td>
                        </tr>
                        <%}%>  
                        <%}%>             
                    </tbody>
                </table>
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
<jsp:include page="/View/Shared/footer.jsp"></jsp:include>
