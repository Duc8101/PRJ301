<%@page import="Const.ConstValue"%>
<jsp:include page="/WEB-INF/View/Shared/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="sum" value="0"></c:set>
<c:set var="set" value="${map.keySet()}"></c:set>
    <section class="user-dashboard page-wrapper" style="height: 100vh">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
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
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="key" items="${set}">
                                    <c:set var="quantity" value="${map.get(key)}"></c:set>
                                    <c:set var="sum" value="${sum + quantity * key.getPrice()}"></c:set>
                                        <tr>
                                            <td>${key.getProductName()}</td>
                                        <td><img src="${key.getImage()}" alt="" width="50" height="50"></td>
                                        <td>${quantity}</td>
                                        <td>${key.getPrice()}</td>
                                        <td>${quantity * key.getPrice()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <h3>Total Amount: $${sum}</h3>
                    </div> 
                </div>
            </div>

            <c:set var="readonly" value="${mess == null ? '' : 'readonly'}"></c:set>
                <div class="col-md-4">
                    <h3>Billing Address</h3>
                    <form action="<%=ConstValue.CONTEXT_PATH%>/Cart/Checkout" method="POST">
                    <div class="col-12">
                        <input type="hidden" name="sum" value="${sum}">
                        <label for="Address" class="form-label">Address</label>
                        <input type="text" class="form-control" value="${address == null ? "" : address}" name="address" placeholder="Your delivery address" ${readonly} required>
                    </div>
                    <br>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary w-100" onclick="return confirm('Are you sure you want to check out?')" ${mess == null ? "" : "disabled"}>Check out</button>
                        <p style="color:green">${mess}</p> 
                        <p style="color:red">${message}</p>
                    </div>
                </form>

            </div>        
        </div>
    </div>

</section>
<jsp:include page="/WEB-INF/View/Shared/footer.jsp"></jsp:include>
