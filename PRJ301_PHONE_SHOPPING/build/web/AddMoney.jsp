<%@include file= "component/header.jsp" %>
<%
    String message = (String) request.getAttribute("message");
    String mess = (String) request.getAttribute("mess");
%>
<section class="products section bg-gray" style="height: 100vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title" style="margin-left: 4%">
                <p style="color:red"><%=message == null ? "" : message%></p>
                <p style="color:green"><%=mess == null ? "" : mess%></p>
                <h2 class="text-center">Current Money: <%=(double) Math.round(user.getMoney() * 100) / 100%></h2>
                <form action="AddMoney" class="text-left clearfix" method="POST">
                    <div class="form-group">
                        <input type="number" class="form-control" placeholder="Input your money to add" name="money" required step="any">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" name="password" required>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-main text-center" onclick="return confirm('Are you sure you want to add?')">Add</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</section>
<%@include file= "component/footer.jsp" %>