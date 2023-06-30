<%@page import="Entity.Customer"%>
<%@page import="Const.ConstValue"%>
<%@page import="Model.DAOCustomer"%>
<%@page import="java.util.List"%>
<%@page import="Entity.Order"%>
<%@include file= "component/header.jsp" %>
<%
    DAOCustomer daoCustomer = new DAOCustomer();
    int status = (Integer) request.getAttribute("status");
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
                        <option value="<%=ConstValue.INT_STATUS_WAIT%>" <%=status == ConstValue.INT_STATUS_WAIT ? "selected" : ""%>><%=ConstValue.STRING_STATUS_WAIT%></option>
                        <option value="<%=ConstValue.INT_STATUS_PROCESS%>" <%=status == ConstValue.INT_STATUS_PROCESS ? "selected" : ""%>><%=ConstValue.STRING_STATUS_PROCESS%></option>
                        <option value="<%=ConstValue.INT_STATUS_DONE%>" <%=status == ConstValue.INT_STATUS_DONE ? "selected" : ""%>><%=ConstValue.STRING_STATUS_DONE%></option>
                        <option value="<%=ConstValue.INT_STATUS_CANCEL%>" <%=status == ConstValue.INT_STATUS_CANCEL ? "selected" : ""%>><%=ConstValue.STRING_STATUS_CANCEL%></option>
                    </select>
                    <button class="btn btn-outline-success" type="submit">Search Order</button>
                </form>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>User</th>
                            <th>Order Date</th>
                            <th>Required Date</th>
                            <th>Ship Date</th>
                            <th>Status</th>
                            <th>View</th>
                            <th>Detail</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            String color;
                            String Status;
                            for (Order order : listOrder) {
                                Customer customer = daoCustomer.getCustomer(order.getCustomerID());
                                if (order.getStatus() == ConstValue.INT_STATUS_PROCESS) {
                                    color = "blue";
                                    Status = ConstValue.STRING_STATUS_PROCESS;
                                } else if (order.getStatus() == ConstValue.INT_STATUS_DONE) {
                                    color = "green";
                                    Status = ConstValue.STRING_STATUS_DONE;
                                } else if (order.getStatus() == ConstValue.INT_STATUS_CANCEL) {
                                    color = "red";
                                    Status = ConstValue.STRING_STATUS_CANCEL;
                                } else {
                                    color = "";
                                    Status = ConstValue.STRING_STATUS_WAIT;
                                }
                        %>
                        <tr>
                            <td><%=order.getOrderID()%></td>
                            <td><%=customer.getUsername()%></td>       
                            <td><%=order.getOrderDate()%></td>
                            <td><%=order.getRequiredDate()%></td>
                            <td><%=order.getShippedDate() == null ? "" : order.getShippedDate()%></td>
                            <td style="color:<%=color%>"><%=Status%></td>
                            <td><a class="btn btn-info" href="ManagerOrder?service=ViewCustomer&username=<%=customer.getUsername()%>" aria-disabled="true" role="button">View</a></td>
                            <td>
                                <a class="btn btn-primary" href="Detail?OrderID=<%=order.getOrderID()%>">Detail</a>
                            </td>
                            <td>
                                <a class="btn btn-success <%=order.getStatus() == ConstValue.INT_STATUS_PROCESS || order.getStatus() == ConstValue.INT_STATUS_WAIT ? "" : "disabled"%>" href="ManagerOrder?service=EditOrder&OrderID=<%=order.getOrderID()%>" aria-disabled="true" role="button">Edit</a>
                            </td>
                        </tr>
                        <%}%>             
                    </tbody>
                </table>
            </div>
        </div>
        <%
            if (numberPage > 1) {
        %>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center list-group" style="margin-left: 40%">
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