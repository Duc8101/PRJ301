<%@page import="java.util.List"%>
<%@page import="Entity.Customer"%>
<%@include file= "component/header.jsp" %>
<%
    List<Customer> list = (List<Customer>) request.getAttribute("list");
    int pageSelected = (Integer) request.getAttribute("pageSelected");
    int numberPage = (Integer) request.getAttribute("number");
    String preURL = (String) request.getAttribute("previous");
    String nextURL = (String) request.getAttribute("next");
    String firstURL = (String) request.getAttribute("first");
    String lastURL = (String) request.getAttribute("last");
%>
<section class="products section bg-gray" style="height: 160vh">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 title">
                <div class="row" style="margin-left: 3%">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Full Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Street</th>
                                <th>City</th>
                                <th>Zip code</th>
                                <th>Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Customer customer : list) {
                            %>
                            <tr>
                                <td><%=customer.getUsername()%></td>
                                <td><%=customer.getFirstName() + " " + customer.getLastName()%></td>
                                <td><%=customer.getPhone() == null ? "" : customer.getPhone()%></td>
                                <td><%=customer.getEmail()%></td>
                                <td><%=customer.getStreet()%></td>
                                <td><%=customer.getCity()%></td>
                                <td><%=customer.getZipCode() == null ? "" : customer.getZipCode()%></td>
                                <td><a class="btn btn-success" href="ManagerCustomer?service=EditCustomer&username=<%=customer.getUsername()%>">Edit</a></td>
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
            <ul class="pagination justify-content-center list-group" style="margin-left: 40%">
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