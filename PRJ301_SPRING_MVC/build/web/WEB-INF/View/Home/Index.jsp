<%@page import="Const.ConstValue"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/View/Shared/header.jsp"></jsp:include>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-3 title text-center">
                <h2>Category</h2>
                <ul class="list-group" style="margin-top: 10%">
                    <li class="list-group-item list-group-item-action list-group-item-warning" style="margin-left: 8%"><a href="<%=ConstValue.CONTEXT_PATH%>/Home">ALL</a></li>
                        <c:forEach var="category" items="${listCategory}">
                        <li class="list-group-item list-group-item-action list-group-item-warning" style="margin-left: 8%"><a href="<%=ConstValue.CONTEXT_PATH%>/Home?CategoryID=${category.getID()}">${category.getName()}</a></li>
                        </c:forEach>
                </ul>
            </div>
            <div class="col-lg-9 title">
                <h2 class=" text-center">Product</h2>
                <div class="row" style="margin-left: 3%">
                    <form action="<%=ConstValue.CONTEXT_PATH%>/Home" style="margin-left: 3%">
                        <c:if test="${CategoryID != 0}">
                            <input type="hidden" name="CategoryID" value="${CategoryID}">
                        </c:if>
                        <select name="properties">
                            <option value="ProductName" ${"ProductName".equals(properties) ? "selected" : ""}>Name</option>
                            <option value="price" ${"price".equals(properties) ? "selected" : ""}>Price</option>
                        </select>
                        <select name="flow">
                            <option value="ASC" ${"ASC".equals(flow) ? "selected" : ""}>Ascending</option>
                            <option value="DESC" ${"DESC".equals(flow) ? "selected" : ""}>Descending</option>
                        </select>
                        <button type="submit" class="btn btn-outline-primary ms-lg-2">Sort</button>
                    </form>
                    <c:forEach var="product" items="${listProduct}">
                        <div  class="col-lg-4 text-center">
                            <h2>${product.getProductName()}</h2>
                            <img src="${product.getImage()}" alt="alt" width="100" height="100"><br>
                            <p>Price: $${product.getPrice()}</p>
                            <button class="posision-fixed" style="radius: 10px" ><a href="<%=ConstValue.CONTEXT_PATH%>/Cart/AddCart?ProductID=${product.getProductID()}">Add to Cart</a></button>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <c:if test="${number > 1}">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center list-group" style="margin-left: 60%">
                    <li class="page-item ${pageSelected == 1 ? "disabled" : ""}"><a class="page-link btn btn-primary btn-lg ${pageSelected == 1 ? "disabled" : ""}" href="${first}" aria-disabled="true" role="button">First</a></li>  
                    <li class="page-item ${pageSelected == 1 ? "disabled" : ""}"><a class="page-link btn btn-primary btn-lg ${pageSelected == 1 ? "disabled" : ""}" href="${previous}" aria-disabled="true" role="button">Previous</a></li>  
                    <li class="page-item"><a class="page-link btn btn-primary btn-lg" href="" aria-disabled="true" role="button">${pageSelected}/${number}</a></li>  
                    <li class="page-item ${pageSelected == number ? "disabled" : ""}" ><a class="page-link btn btn-primary btn-lg ${pageSelected == number ? "disabled" : ""}" href="${next}" aria-disabled="true" role="button">Next</a></li>
                    <li class="page-item ${pageSelected == number ? "disabled" : ""}" ><a class="page-link btn btn-primary btn-lg ${pageSelected == number ? "disabled" : ""}" href="${last}" aria-disabled="true" role="button">Last</a></li>
                </ul>
            </nav>
        </c:if>
    </div>
</section>
<jsp:include page="/WEB-INF/View/Shared/footer.jsp"></jsp:include>
