<%@page import="Const.ConstValue"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="Entity.Order"%>
<%@include file= "component/header.jsp" %>
<%
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");
    Order order = (Order) request.getAttribute("order");
    Map<Integer, String> map = (Map<Integer, String>) request.getAttribute("map");
    Set<Integer> key = map.keySet();
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title" style="margin-left: 4%">
                <p style="color:red"><%=message == null ? "" : message%></p>
                <p style="color:green"><%=mess == null ? "" : mess%></p>
                <form method="POST" class="text-left clearfix" action="ManagerOrder">
                    <input type="hidden" name="OrderID" value="<%=order.getOrderID()%>">
                    <input type="hidden" name="CustomerID" value="<%=order.getCustomerID()%>">
                    <input type="hidden" name="OrderDate" value="<%=order.getOrderDate()%>">
                    <input type="hidden" name="RequiredDate" value="<%=order.getRequiredDate()%>">
                    <select name="status" <%=(order.getStatus() == ConstValue.INT_STATUS_DONE && mess != null) || order.getStatus() == ConstValue.INT_STATUS_CANCEL ? "disabled" : ""%>>
                        <%
                            for (Integer status : key) {
                                if (order.getStatus() == ConstValue.INT_STATUS_WAIT) {
                        %>
                        <option value="<%=status%>" <%=status.equals(order.getStatus()) ? "selected" : ""%> <%=status == ConstValue.INT_STATUS_DONE ? "disabled" : ""%>><%=map.get(status)%></option>
                        <%} else if (order.getStatus() == ConstValue.INT_STATUS_PROCESS) {%>
                        <option value="<%=status%>" <%=status.equals(order.getStatus()) ? "selected" : ""%> <%=status == ConstValue.INT_STATUS_WAIT || status == ConstValue.INT_STATUS_CANCEL ? "disabled" : ""%>><%=map.get(status)%></option>
                        <%} else {%>
                        <option value="<%=status%>" <%=status.equals(order.getStatus()) ? "selected" : ""%> ><%=map.get(status)%></option>
                        <%}%>
                        <%}%>
                    </select>
                    <br />
                    <br />
                    <br />
                    <div class="form-group">
                        Ship Date :<input type="date" name="ShipDate" value="<%=order.getShippedDate() == null ? "" : order.getShippedDate()%>" <%=order.getStatus() == ConstValue.INT_STATUS_PROCESS || (mess == null && order.getStatus() == ConstValue.INT_STATUS_DONE) ? "" : "disabled"%>>
                    </div>
                    <div>
                        <button type="submit" <%=mess == null || order.getStatus() == ConstValue.INT_STATUS_PROCESS ? "" : "disabled"%> onclick="return confirm('Are you sure you want to save your change?')">Save</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</section>
<%@include file= "component/footer.jsp" %>