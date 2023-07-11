<%@page import="Const.ConstValue"%>
<%@page import="Entity.User"%>
<jsp:include page="/View/Shared/header.jsp"></jsp:include>
<%
    User user = (User) request.getAttribute("user");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title" style="margin-left: 4%">
                <h2 class="text-center">Profile</h2>
                <form class="text-left clearfix">
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="FullName" name="FullName" disabled value="<%=user.getFullName()%>">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="Phone" name="phone" disabled value="<%=user.getPhone()%>">
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control"  placeholder="Email (optional)" name="email" disabled value="<%=user.getEmail() == null ? "" : user.getEmail()%>">
                    </div>
                    <div class="form-group">
                        <p>Gender:
                            <select name="gender" disabled>
                                <option value="<%=ConstValue.GENDER_MALE%>" <%=user.getGender().equals(ConstValue.GENDER_MALE) ? "selected" : ""%>><%=ConstValue.GENDER_MALE%></option>
                                <option value="<%=ConstValue.GENDER_FEMALE%>" <%=user.getGender().equals(ConstValue.GENDER_FEMALE) ? "selected" : ""%>><%=ConstValue.GENDER_FEMALE%></option>
                            </select>
                        </p>
                    </div>
                </form>
            </div>

        </div>
    </div>
</section>
<jsp:include page="/View/Shared/footer.jsp"></jsp:include>