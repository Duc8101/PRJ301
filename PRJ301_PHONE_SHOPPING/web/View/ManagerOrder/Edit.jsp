<%@page import="Const.ConstValue"%>
<%@page import="Entity.Order"%>
<jsp:include page="/View/Shared/header.jsp"></jsp:include>
<%
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");
    Order order = (Order) request.getAttribute("order");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title" style="margin-left: 4%">
                <p style="color:red"><%=message == null ? "" : message%></p>
                <p style="color:green"><%=mess == null ? "" : mess%></p>
                <form method="POST" class="text-left clearfix" action="ManagerOrder?service=EditOrder&OrderID=<%=order.getOrderID()%>">
                    <input type="hidden" name="address" value="<%=order.getAddress()%>">
                    <input type="hidden" name="UserID" value="<%=order.getUserID()%>">
                    <input type="hidden" name="OrderDate" value="<%=order.getOrderDate()%>">
                    <select name="status" <%=(order.getStatus().equals(ConstValue.STATUS_COMPLETED) && mess != null) || order.getStatus().equals(ConstValue.STATUS_REJECTED) ? "disabled" : ""%>>
                        <%
                            for (String status : ConstValue.LIST_STATUS) {
                                if (!order.getStatus().equals(ConstValue.STATUS_ALL)) {
                                    if (order.getStatus().equals(ConstValue.STATUS_NEW)) {
                        %>
                        <option value="<%=status%>" <%=status.equals(order.getStatus()) ? "selected" : ""%> <%=status.equals(ConstValue.STATUS_COMPLETED) ? "disabled" : ""%>><%=status%></option>
                        <%} else if (order.getStatus().equals(ConstValue.STATUS_SHIPPING)) {%>
                        <option value="<%=status%>" <%=status.equals(order.getStatus()) ? "selected" : ""%> <%=status.equals(ConstValue.STATUS_NEW) || status.equals(ConstValue.STATUS_REJECTED) ? "disabled" : ""%>><%=status%></option>
                        <%} else {%>
                        <option value="<%=status%>" <%=status.equals(order.getStatus()) ? "selected" : ""%> ><%=status%></option>
                        <%}%>
                        <%}%>
                        <%}%>
                    </select>
                    <br />
                    <br />
                    <br />
                    <div class="form-group">
                        Ship Date :<input type="date" name="ShipDate" value="<%=order.getShipDate() == null ? "" : order.getShipDate()%>" <%=order.getStatus().equals(ConstValue.STATUS_SHIPPING) || (order.getStatus().equals(ConstValue.STATUS_COMPLETED) && mess == null) ? "" : "disabled"%>>
                    </div>
                    <div>
                        <button type="submit" <%=mess == null || order.getStatus().equals(ConstValue.STATUS_SHIPPING) ? "" : "disabled"%> onclick="return confirm('Are you sure you want to save your change?')">Save</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="/View/Shared/footer.jsp"></jsp:include>
