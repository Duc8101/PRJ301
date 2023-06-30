<%@include file= "component/header.jsp" %>
<%
    User accUser = (User) request.getAttribute("user");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title" style="margin-left: 4%">
                <h2 class="text-center">Profile</h2>
                <form class="text-left clearfix">
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="FullName" name="FullName" disabled value="<%=accUser.getFullName()%>">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control"  placeholder="Phone" name="phone" disabled value="<%=accUser.getPhone()%>">
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control"  placeholder="Email (optional)" name="email" disabled value="<%=accUser.getEmail() == null ? "" : accUser.getEmail()%>">
                    </div>
                    <div class="form-group">
                        <p>Gender:
                            <select name="gender" disabled>
                                <option value="<%=User.GENDER_MALE%>" <%=accUser.getGender().equals(User.GENDER_MALE) ? "selected" : ""%>><%=User.GENDER_MALE%></option>
                                <option value="<%=User.GENDER_FEMALE%>" <%=accUser.getGender().equals(User.GENDER_FEMALE) ? "selected" : ""%>><%=User.GENDER_FEMALE%></option>
                            </select>
                        </p>
                    </div>
                </form>
            </div>

        </div>
    </div>
</section>
<%@include file= "component/footer.jsp" %>