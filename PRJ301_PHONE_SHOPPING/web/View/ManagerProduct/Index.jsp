<%@page import="Entity.Product"%>
<%@page import="Entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="Model.DAOOrderDetail"%>
<%@page import="Model.DAOCategory"%>
<jsp:include page="/View/Shared/header.jsp"></jsp:include>
<%
    DAOCategory daoCategory = new DAOCategory();
    DAOOrderDetail daoDetail = new DAOOrderDetail();
    List<Category> listCategory = daoCategory.getAllCategory();
    int CategoryID = (Integer) request.getAttribute("CategoryID");
    List<Product> listProduct = (List<Product>) request.getAttribute("list");
    int pageSelected = (Integer) request.getAttribute("pageSelected");
    int numberPage = (Integer) request.getAttribute("number");
    String preURL = (String) request.getAttribute("previous");
    String nextURL = (String) request.getAttribute("next");
    String firstURL = (String) request.getAttribute("first");
    String lastURL = (String) request.getAttribute("last");
    String mess = (String) request.getAttribute("mess");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title">
                <div class="row">
                    <a class="btn btn-primary" style="margin-left: 50%" href="ManagerProduct?service=AddProduct">Add Product</a>
                    <p style="margin-left: 10%;color:green"><%=mess == null ? "" : mess%></p>
                    <form action="ManagerProduct" style="margin-left: 10%">
                        <select name="CategoryID">
                            <%
                                for (Category category : listCategory) {
                            %>
                            <option value="<%=category.getID()%>" <%=category.getID() == CategoryID ? "selected" : ""%>><%=category.getName()%></option>
                            <%}%>
                        </select>
                        <button type="submit" class="btn btn-outline-primary ms-lg-2">Search</button>
                    </form>

                    <%
                        for (Product product : listProduct) {
                            boolean check = daoDetail.isExist(product.getProductID());
                    %>
                    <div class="col-lg-4 text-center">
                        <h2><%=product.getProductName()%></h2>
                        <img src="<%=product.getImage()%>" alt="alt" width="100" height="100"><br>
                        <p>Price: $<%=product.getPrice()%></p>
                        <a class="btn btn-success"  href="ManagerProduct?service=EditProduct&ProductID=<%=product.getProductID()%>">Edit</a>
                        <a class="btn btn-danger <%= check ? "disabled" : ""%>" onclick="return confirm('Are you sure you want to remove this product?')" href="ManagerProduct?service=DeleteProduct&ProductID=<%=product.getProductID()%>" aria-disabled="true" role="button">Delete</a>      
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
<jsp:include page="/View/Shared/footer.jsp"></jsp:include>
