<%@page import="com.rohini.flyway.models.User"%>
<%
    int isLoggedIn = 0;
    int userType = 0;
    if (session.getAttribute("SESSION_USER") != null) {
        isLoggedIn = 1;
        User user = (User) session.getAttribute("SESSION_USER");
        userType = user.getUserType();
    }
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-xl">
    <a class="navbar-brand" href="#">Flyway - Travel Site</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample07XL" aria-controls="navbarsExample07XL" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExample07XL">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item" id="index">
          <a class="nav-link" href="${pageContext.request.contextPath}/index">Search Flights</a>
        </li>
        <% if (isLoggedIn == 1 && userType == 1) { %>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown07XL" data-toggle="dropdown" aria-expanded="false">Admin</a>
          <div class="dropdown-menu" aria-labelledby="dropdown07XL">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/add-airline">Add Airline</a>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/add-airport">Add Airport</a>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/add-flight">Add Flight</a>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/change-password">Change Password</a>
          </div>
        </li>
        <%  } %>
      </ul>
      <ul class="navbar-nav px-3">
          <li class="nav-item text-nowrap">
            <% if (isLoggedIn == 1) { %>
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
             <%  } else { %>
                <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
             <% } %>
          </li>
      </ul>
    </div>
  </div>
</nav>

<script type="text/javascript">
    $(document).ready(function(){
        var current = window.location.pathname;
        console.log(current);
        console.log("index".indexOf(current));
        if("change-password".indexOf(current) !== -1)
            $("#changePassword").addClass("active");
        else if("admin".indexOf(current) !== -1)
            $("#dropdown07XL").addClass("active");
        else if("index".indexOf(current) !== -1)
            $("#index").addClass("active");
    });
</script>
