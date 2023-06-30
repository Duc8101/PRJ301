<%@page import="Entity.Staff"%>
<%@page import="java.util.List"%>
<%@include file= "component/header.jsp" %>
<%
    List<Staff> list = (List<Staff>) request.getAttribute("list");
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
                <p style="margin-left: 50%"><a class="btn btn-primary" href="ManagerStaff?service=AddStaff">Add Staff</a></p>
                <p style="margin-left: 3%;color:green"><%=mess == null ? "" : mess%></p>
                <div class="row" style="margin-left: 1%">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Active</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Staff staff : list) {
                            %>
                            <tr>
                                <td><%=staff.getUsername()%></td>
                                <td><%=staff.getFirstName()%></td> 
                                <td><%=staff.getLastName()%></td> 
                                <td><%=staff.getPhone() == null ? "" : staff.getPhone()%></td> 
                                <td><%=staff.getEmail()%></td> 
                                <td>
                                    <form action="ManagerStaff" method="POST">
                                        <input type="hidden" name="service" value="EditStaff">
                                        <input type="hidden" name="StaffID" value="<%=staff.getStaffID()%>">
                                        <input type="checkbox" name="active" value="" <%=staff.isActive() ? "checked" : ""%> onchange="this.form.submit()">
                                    </form>
                                </td> 
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