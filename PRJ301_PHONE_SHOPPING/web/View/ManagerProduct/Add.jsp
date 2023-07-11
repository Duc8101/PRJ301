<%@page import="Entity.Product"%>
<%@page import="Entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="Model.DAOCategory"%>
<jsp:include page="/View/Shared/header.jsp"></jsp:include>
<%
    DAOCategory daoCategory = new DAOCategory();
    List<Category> listCategory = daoCategory.getAllCategory();
    String message = (String) request.getAttribute("message");
    Product product = (Product) request.getAttribute("product");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <p style="color:red;margin-left: 4%"><%=message == null ? "" : message%></p>
            <div class="col-lg-12 title" style="margin-left: 4%">
                <form action="ManagerProduct?service=AddProduct" method="POST" class="text-left clearfix">
                    <div class="form-group">
                        <label>Category</label><br>
                        <select name="CategoryID">
                            <%
                                for (Category category : listCategory) {
                            %>
                            <option value="<%=category.getID()%>" <%=category.getID() == product.getCategoryID() ? "selected" : ""%>><%=category.getName()%></option>
                            <%}%>
                        </select>
                    </div>

                    <div class="form-group"> 
                        <label>Product Name</label><br>
                        <input type="text" class="form-control" name="name" style="width: 300px" placeholder="Product Name" value="<%=product.getProductName() == null ? "" : product.getProductName()%>" required>           
                    </div>

                    <div class="form-group">
                        <label>Image link</label><br>
                        <input type="text" name="image" class="form-control" value="<%=product.getImage() == null ? "" : product.getImage()%>" placeholder="Image link" required>
                    </div> 
                    <div class="form-group">
                        <label>Price</label><br>
                        <input type="number" step="any" name="price" class="form-control" placeholder= "Price" value="<%=product.getPrice()%>" required>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-main text-center" onclick="return confirm('Are you sure you want to save your change?')">Save</button>
                    </div>
                </form> 
            </div>
        </div>
    </div>
</section>       
<jsp:include page="/View/Shared/footer.jsp"></jsp:include>
