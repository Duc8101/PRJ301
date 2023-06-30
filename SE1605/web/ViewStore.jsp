<%@page import="java.util.List"%>
<%@page import="Model.DAOStore"%>
<%@page import="Entity.Store"%>
<%@include file= "component/header.jsp" %>
<%
    Store store = (Store) session.getAttribute("store");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title" style="margin-left: 4%">
                <h2 class="text-center">Information</h2>
                <form class="text-left clearfix" action="" method="POST">
                    <div class="form-group">
                        <label>Store Name</label><br>
                        <input type="text" class="form-control" disabled value="<%=store.getName()%>">
                    </div>
                    <div class="form-group">
                        <label>Phone</label><br>
                        <input type="text" class="form-control" disabled value="<%=store.getPhone()%>">
                    </div>
                    <div class="form-group">
                        <label>Email</label><br>
                        <input type="email" class="form-control" disabled value="<%=store.getEmail() %>">
                    </div>
                    <div class="form-group">
                        <label>Street</label><br>
                        <input type="text" class="form-control" disabled value="<%=store.getStreet()%>">
                    </div>
                    <div class="form-group">
                        <label>City</label><br>
                        <input type="text" class="form-control"  disabled value="<%=store.getCity()%>">
                    </div>
                    <div class="form-group">
                        <label>Zip Code</label><br>
                        <input type="text" class="form-control"  disabled value="<%=store.getZipCode()%>">
                    </div>
                </form>
            </div>

        </div>
    </div>
</section>
<%@include file= "component/footer.jsp" %>