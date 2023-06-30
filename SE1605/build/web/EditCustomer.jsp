<%@page import="Entity.Customer"%>
<%@include file= "component/header.jsp" %>
<%
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");
    Customer customer = (Customer) request.getAttribute("customer");
%>
<section class="products section bg-gray" >
    <div class="container-fluid">
        <div class="row">         
            <div class="col-lg-12 title text-center" style="margin-left: 4%">            
                <h2 class="text-center">Profile</h2> 
                <p style="color:red"><%=message == null ? "" : message%></p>
                <p style="color:green"><%=mess == null ? "" : mess%></p>
                <form action="ManagerCustomer" method="POST" class="text-left clearfix">
                    <div class="form-group">
                        <label>Username</label><br>
                        <input type="text" name="username" class="form-control" value="<%=customer.getUsername()%>" readonly>
                    </div>  
                    <div class="form-group">
                        <label>Zip code</label><br>
                        <input type="number" name="zip" class="form-control" value="<%=customer.getZipCode() == null ? "" : customer.getZipCode()%>" required>
                    </div>  
                    <div class="text-center">
                        <button type="submit" class="btn btn-main text-center">Save</button>
                    </div>
                </form> 
            </div>
        </div>
    </div>
</section>
<%@include file= "component/footer.jsp" %>
