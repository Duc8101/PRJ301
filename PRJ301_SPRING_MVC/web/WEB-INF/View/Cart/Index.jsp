<%@page import="Const.ConstValue"%>
<jsp:include page="/WEB-INF/View/Shared/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="page-header">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="content">
                    <h1 class="page-name">Show Cart</h1>
                </div>
            </div>
        </div>
    </div>
</section>

<c:set var="sum" value="0"></c:set>
<c:set var="set" value="${map.keySet()}"></c:set>
    <section class="user-dashboard page-wrapper" style="height: 100vh">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
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
                                        <th class="col-md-2 col-sm-3">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="key" items="${set}">
                                    <c:set var="quantity" value="${map.get(key)}"></c:set>
                                    <c:set var="sum" value="${sum + quantity * key.getPrice()}"></c:set>
                                        <tr>
                                            <td><button class="btn"><a href="<%=ConstValue.CONTEXT_PATH%>/Cart/AddCart?ProductID=${key.getProductID()}">${key.getProductName()}</a></button></td>
                                        <td><img src="${key.getImage()}" alt="" width="50" height="50"></td>
                                        <td>${quantity}</td>
                                        <td>${key.getPrice()}</td>
                                        <td>${quantity * key.getPrice()}</td>
                                        <td><a href="<%=ConstValue.CONTEXT_PATH%>/Cart/RemoveCart?ProductID=${key.getProductID()}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete?')"> Delete</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <h3>Total Amount: ${sum}</h3>
                    </div> 

                </div>
            </div>
        </div>

        <a href="<%=ConstValue.CONTEXT_PATH%>/Cart/Checkout" class="btn btn-success w-100 ${map.isEmpty() ? "disabled" : ""}" aria-disabled="true" role="button">check out</a>
    </div>

</section>
<jsp:include page="/WEB-INF/View/Shared/footer.jsp"></jsp:include>
