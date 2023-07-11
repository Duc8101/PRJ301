<%@page import="Const.ConstValue"%>
<%@page import="Entity.User"%>
<jsp:include page="/View/Shared/header.jsp"></jsp:include>
<%
    User seller = (User) request.getAttribute(ConstValue.USERNAME_SELLER);
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">        
            <div class="col-lg-12 title text-center" style="margin-left: 4%">            
                <h2 class="text-center">Information seller</h2> 
                <form method="POST" class="text-left clearfix">
                    <div class="form-group">
                        <input type="text" name="FullName"  class="form-control" placeholder="Your FullName" value="<%=seller.getFullName()%>" readonly required>
                    </div>
                    <div class="form-group">
                        <p>Gender:
                            <select name="gender" disabled>
                                <option value="<%=ConstValue.GENDER_MALE%>" <%=seller.getGender().equalsIgnoreCase(ConstValue.GENDER_MALE) ? "selected" : ""%>>Male</option>
                                <option value="<%=ConstValue.GENDER_FEMALE%>" <%=seller.getGender().equalsIgnoreCase(ConstValue.GENDER_FEMALE) ? "selected" : ""%>>Female</option>
                            </select>
                        </p>
                    </div>
                    <div class="form-group">
                        <input type="text" name="phone" class="form-control" placeholder="Your phone" value="<%=seller.getPhone()%>" readonly required>
                    </div>
                    <div class="form-group">
                        <input type="email" name="email" class="form-control" placeholder="Your email" readonly value="<%=seller.getEmail() == null ? "" : seller.getEmail()%>" >
                    </div>         
                </form> 
            </div>
        </div>
    </div>
</section>
<jsp:include page="/View/Shared/footer.jsp"></jsp:include>
