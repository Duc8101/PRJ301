<%@page import="Entity.Customer"%>
<%@include file= "component/header.jsp" %>
<%
    Customer customer = (Customer) request.getAttribute("customer");
%>
<section class="products section bg-gray" >
    <div class="container-fluid">
        <div class="row">          
            <div class="col-lg-12 title text-center" style="margin-left: 4%">            
                <h2 class="text-center">Profile</h2> 
                <form action="ProfileCustomer" method="POST" class="text-left clearfix">
                    <div class="form-group">
                        <label>First Name</label><br>
                        <input type="text" name="FirstName"  class="form-control" placeholder="Your First Name" value="<%=customer.getFirstName()%>" readonly required>
                    </div>
                    <div class="form-group">
                        <label>Last Name</label><br>
                        <input type="text" name="LastName"  class="form-control" placeholder="Your Last Name" value="<%=customer.getLastName()%>" readonly required>
                    </div>
                    <div class="form-group">
                        <label>Phone</label><br>
                        <input type="text" name="phone" class="form-control" readonly value="<%=customer.getPhone() == null ? "" : customer.getPhone()%>">
                    </div>
                    <div class="form-group">
                        <label>Email</label><br>
                        <input type="email" name="email" class="form-control" placeholder="Your email" value="<%=customer.getEmail()%>" readonly required>
                    </div>
                    <div class="form-group">
                        <label>Street</label><br>
                        <input type="text" name="street" class="form-control" placeholder="Your street" value="<%=customer.getStreet()%>" readonly required>
                    </div>
                    <div class="form-group">
                        <label>City</label><br>
                        <input type="text" name="city" class="form-control" placeholder="Your city" value="<%=customer.getCity()%>" readonly required>
                    </div>
                    <div class="form-group">
                        <label>Zip code</label><br>
                        <input type="text" name="zip" class="form-control" placeholder="Your zip code" value="<%=customer.getZipCode() == null ? "" : customer.getZipCode()%>" readonly required>
                    </div>  
                </form> 
            </div>
        </div>
    </div>
</section>
<%@include file= "component/footer.jsp" %>