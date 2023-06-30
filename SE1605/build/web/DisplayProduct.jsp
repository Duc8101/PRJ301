<%@page import="Model.DAOOrderItem"%>
<%@page import="Entity.Stock"%>
<%@page import="Model.DAOStock"%>
<%@page import="java.util.List"%>
<%@page import="Entity.Product"%>
<%@include file= "component/header.jsp" %>
<%
    List<Product> listProduct = (List<Product>) request.getAttribute("listProduct");
    String[] listCategory = (String[]) request.getAttribute("listCategory");
    String[] listBrand = (String[]) request.getAttribute("listBrand");
    String category = (String) request.getAttribute("category");
    String brand = (String) request.getAttribute("brand");
    int pageSelected = (Integer) request.getAttribute("pageSelected");
    int numberPage = (Integer) request.getAttribute("number");
    String preURL = (String) request.getAttribute("previous");
    String nextURL = (String) request.getAttribute("next");
    String firstURL = (String) request.getAttribute("first");
    String lastURL = (String) request.getAttribute("last");
    String mess = (String) request.getAttribute("mess");
%>
<section class="products section bg-gray" style="height: 200vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title">
                <p style="margin-left: 50%"><a class="btn btn-primary" href="ManagerProduct?service=AddProduct">Add Product</a></p>
                <p style="margin-left: 3%;color:green"><%=mess == null ? "" : mess%></p>
                <div class="row" style="margin-left: 3%">
                    <form action="ManagerProduct">
                        Category:<select name="category">
                            <%
                                for (String name : listCategory) {
                            %>
                            <option value="<%=name%>" <%=name.equalsIgnoreCase(category) ? "selected" : ""%>><%=name%></option>
                            <%}%>
                        </select>
                        Brand:<select name="brand">
                            <%
                                for (String name : listBrand) {
                            %>
                            <option value="<%=name%>" <%=name.equalsIgnoreCase(brand) ? "selected" : ""%>><%=name%></option>
                            <%}%>
                        </select>
                        <button type="submit" class="btn btn-outline-primary ms-lg-2">Search</button>
                    </form>
                    <%
                        DAOStock daoStock = new DAOStock();
                        DAOOrderItem daoItem = new DAOOrderItem();
                    %>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Released year</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Category</th>
                                <th>Brand</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Product product : listProduct) {
                                    boolean check = daoItem.isExist(product.getID());
                            %>
                            <tr>
                                <td><%=product.getProductName()%></td>
                                <td><%=product.getYear()%></td>
                                <td><%=product.getPrice()%></td>
                                <%
                                    int StoreID = (Integer) session.getAttribute("StoreID");
                                    Stock stock = daoStock.getStock(StoreID, product.getID());
                                %>
                                <td><%=stock.getQuantity()%></td>
                                <td><%=product.getCategoryName()%></td>
                                <td><%=product.getBrandName()%></td>
                                <td><a class="btn btn-success" href="ManagerProduct?service=EditProduct&ID=<%=product.getID()%>">Edit</a></td>
                                <td><a class="btn btn-danger <%=check ? "disabled" : ""%>" onclick="return confirm('Are you sure you want to remove this product?')" href="ManagerProduct?service=DeleteProduct&ID=<%=product.getID()%>" aria-disabled="true" role="button">Delete</a></td>    
                            </tr>
                            <%}%>
                        </tbody>
                    </table>


                </div>
            </div>

        </div>
        <%
            if (numberPage > 1) {
        %>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center list-group" style="margin-left: 50%">
                <li class="page-item <%=pageSelected == 1 ? "disabled" : ""%>"><a class="page-link btn btn-primary btn-lg <%=pageSelected == 1 ? "disabled" : ""%>" href="<%=firstURL%>" aria-disabled="true" role="button">First</a></li>  
                <li class="page-item <%=pageSelected == 1 ? "disabled" : ""%>"><a class="page-link btn btn-primary btn-lg <%=pageSelected == 1 ? "disabled" : ""%>" href="<%=preURL%>" aria-disabled="true" role="button">Previous</a></li>
                <li class="page-item"><a href="" class="page-link btn btn-primary btn-lg"><%=pageSelected%>/<%=numberPage%></a></li>
                <li class="page-item <%=pageSelected == numberPage ? "disabled" : ""%>" ><a class="page-link btn btn-primary btn-lg <%=pageSelected == numberPage ? "disabled" : ""%>" href="<%=nextURL%>" aria-disabled="true" role="button">Next</a></li>
                <li class="page-item <%=pageSelected == numberPage ? "disabled" : ""%>" ><a class="page-link btn btn-primary btn-lg <%=pageSelected == numberPage ? "disabled" : ""%>" href="<%=lastURL%>" aria-disabled="true" role="button">Last</a></li>
            </ul>
        </nav>
        <%}%>
    </div>
</section>
<%@include file= "component/footer.jsp" %>