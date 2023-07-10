<%@page import="Entity.Product"%>
<%@page import="Entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="Model.DAOCategory"%>
<jsp:include page="/WEB-INF/Shared/header.jsp"></jsp:include>
<%  DAOCategory daoCategory = new DAOCategory();
    List<Category> listCategory = daoCategory.getAllCategory();
    int CategoryID = (Integer) request.getAttribute("CategoryID");
    String properties = (String) request.getAttribute("properties");
    String flow = (String) request.getAttribute("flow");
    List<Product> listProduct = (List<Product>) request.getAttribute("list");
    int pageSelected = (Integer) request.getAttribute("pageSelected");
    int numberPage = (Integer) request.getAttribute("number");
    String preURL = (String) request.getAttribute("previous");
    String nextURL = (String) request.getAttribute("next");
    String firstURL = (String) request.getAttribute("first");
    String lastURL = (String) request.getAttribute("last");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-3 title text-center">
                <h2>Category</h2>
                <ul class="list-group" style="margin-top: 10%">
                    <li class="list-group-item list-group-item-action list-group-item-warning" style="margin-left: 8%"><a href="Home">ALL</a></li>
                        <%
                            for (Category category : listCategory) {
                        %>
                    <li class="list-group-item list-group-item-action list-group-item-warning" style="margin-left: 8%"><a href="Home?CategoryID=<%=category.getID()%>"><%=category.getName()%></a></li>
                        <%}%>
                </ul>
            </div>
            <div class="col-lg-9 title">
                <h2 class=" text-center">Product</h2>
                <div class="row" style="margin-left: 3%">
                    <form action="Home" style="margin-left: 3%">
                        <%
                            if (CategoryID != 0) {
                        %>
                        <input type="hidden" name="CategoryID" value="<%=CategoryID%>">
                        <%}%>
                        <select name="properties">
                            <option value="ProductName" <%="ProductName".equals(properties) ? "selected" : ""%>>Name</option>
                            <option value="price" <%="price".equals(properties) ? "selected" : ""%>>Price</option>
                        </select>
                        <select name="flow">
                            <option value="ASC" <%="ASC".equals(flow) ? "selected" : ""%>>Ascending</option>
                            <option value="DESC" <%="DESC".equals(flow) ? "selected" : ""%>>Descending</option>
                        </select>
                        <button type="submit" class="btn btn-outline-primary ms-lg-2">Sort</button>
                    </form>

                    <%
                        for (Product product : listProduct) {
                    %>
                    <div  class="col-lg-4 text-center">
                        <h2><%=product.getProductName()%></h2>
                        <img src="<%=product.getImage()%>" alt="alt" width="100" height="100"><br>
                        <p>Price: $<%=product.getPrice()%></p>
                        <button class="posision-fixed" style="radius: 10px" ><a href="Cart?service=AddCart&ProductID=<%=product.getProductID()%>">Add to Cart</a></button>
                    </div>
                    <%}%>
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
<jsp:include page="/WEB-INF/Shared/footer.jsp"></jsp:include>

