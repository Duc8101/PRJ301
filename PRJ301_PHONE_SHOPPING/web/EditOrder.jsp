<%@page import="java.util.List"%>
<%@page import="Entity.Order"%>
<%@include file= "component/header.jsp" %>
<%
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");
    Order order = (Order) request.getAttribute("order");
    List<String> listStatus = (List<String>) request.getAttribute("list");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title" style="margin-left: 4%">
                <p style="color:red"><%=message == null ? "" : message%></p>
                <p style="color:green"><%=mess == null ? "" : mess%></p>
                <form method="POST" class="text-left clearfix" action="ManagerOrder">
                    <input type="hidden" name="OrderID" value="<%=order.getOrderID()%>">
                    <input type="hidden" name="address" value="<%=order.getAddress()%>">
                    <input type="hidden" name="UserID" value="<%=order.getUserID()%>">
                    <input type="hidden" name="OrderDate" value="<%=order.getOrderDate()%>">
                    <select name="status" <%=(order.getStatus().equals(Order.STATUS_COMPLETED) && mess != null) || order.getStatus().equals(Order.STATUS_REJECTED) ? "disabled" : ""%>>
                        <%
                            for (String status : listStatus) {
                                if (order.getStatus().equals(Order.STATUS_NEW)) {
                        %>
                        <option value="<%=status%>" <%=status.equals(order.getStatus()) ? "selected" : ""%> <%=status.equals(Order.STATUS_COMPLETED) ? "disabled" : ""%>><%=status%></option>
                        <%} else if (order.getStatus().equals(Order.STATUS_SHIPPING)) {%>
                        <option value="<%=status%>" <%=status.equals(order.getStatus()) ? "selected" : ""%> <%=status.equals(Order.STATUS_NEW) || status.equals(Order.STATUS_REJECTED) ? "disabled" : ""%>><%=status%></option>
                        <%} else {%>
                        <option value="<%=status%>" <%=status.equals(order.getStatus()) ? "selected" : ""%> ><%=status%></option>
                        <%}%>
                        <%}%>
                    </select>
                    <br />
                    <br />
                    <br />
                    <div class="form-group">
                        Ship Date :<input type="date" name="ShipDate" value="<%=order.getShipDate() == null ? "" : order.getShipDate()%>" <%=order.getStatus().equals(Order.STATUS_SHIPPING) || (order.getStatus().equals(Order.STATUS_COMPLETED) && mess == null) ? "" : "disabled"%>>
                    </div>
                    <div>
                        <button type="submit" <%=mess == null || order.getStatus().equals(Order.STATUS_SHIPPING) ? "" : "disabled"%> onclick="return confirm('Are you sure you want to save your change?')">Save</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</section>
<%@include file= "component/footer.jsp" %>