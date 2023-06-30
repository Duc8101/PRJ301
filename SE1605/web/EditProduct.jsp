<%@page import="Entity.Stock"%>
<%@page import="Model.DAOStock"%>
<%@page import="Const.ConstValue"%>
<%@page import="Entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="Model.DAOProduct"%>
<%@include file= "component/header.jsp" %>
<%
    String message = (String) request.getAttribute("message");
    Product product = (Product) request.getAttribute("product");
    int StoreID = (Integer) session.getAttribute("StoreID");
    DAOStock daoStock = new DAOStock();
    Stock stock = daoStock.getStock(StoreID, product.getID());
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <p style="color:red;margin-left: 4%"><%=message == null ? "" : message%></p>
            <div class="col-lg-12 title" style="margin-left: 4%">
                <form action="ManagerProduct" method="POST" class="text-left clearfix">
                    <input type="hidden" name="service" value="EditProduct">
                    <input type="hidden" name="ID" value="<%=product.getID()%>">
                    <div class="form-group"> 
                        <label>Product Name:</label><br>
                        <input type="text" class="form-control" name="name" style="width: 300px" placeholder="Product Name" value="<%=product.getProductName() == null ? "" : product.getProductName()%>" required>           
                    </div>
                    <div class="form-group">
                        <label>Price:</label><br>
                        <input type="number" step="any" name="price" class="form-control" placeholder= "Price" value="<%=product.getPrice()%>" required>
                    </div>

                    <div class="form-group">
                        <label>Released Year:</label><br>
                        <input type="number" name="year" class="form-control" min ="1" placeholder= "Released year" value="<%=product.getYear() == 0 ? "" : product.getYear()%>" required>
                    </div>

                    <div class="form-group">
                        <label>Category:</label><br>
                        <select name="category">
                            <%
                                for (String category : ConstValue.LIST_CATEGORY) {
                            %>
                            <option value="<%=category%>" <%=category.equals(product.getCategoryName()) ? "selected" : ""%>><%=category%></option>
                            <%}%>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Brand:</label><br>
                        <select name="brand">
                            <%
                                for (String brand : ConstValue.LIST_BRAND) {
                            %>
                            <option value="<%=brand%>" <%=brand.equals(product.getBrandName()) ? "selected" : ""%>><%=brand%></option>
                            <%}%>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Quantity:</label><br>
                        <input type="number" name="quantity" class="form-control" min ="0" placeholder= "quantity" value="<%=stock.getQuantity()%>" required>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-main text-center" onclick="return confirm('Are you sure you want to save your change?')">Save</button>
                    </div>
                </form> 
            </div>
        </div>
    </div>
</section>       
<%@include file= "component/footer.jsp" %>