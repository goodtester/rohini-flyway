<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Flyway (Admin panel)" />
    <title>Flyway - Booking site </title>
    <jsp:include page="includes/common.jsp" />
</head>

<body class="d-flex">

    <form class="form-signin" method="POST" action="${pageContext.request.contextPath}/register">
      <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">Register to Flyway website</h1>
      </div>

      <div class="form-label-group text-center">
        <% if (request.getAttribute("RESP_STATUS") != null) {
            final String RESP_STATUS = (String) request.getAttribute("RESP_STATUS");
            if (RESP_STATUS.equals("FAILED")) {
         %>
         <div class="alert alert-danger" role="alert"><%=request.getAttribute("RESP_MSG").toString()%></div>
         <% } else {
         %>
         <div class="alert alert-success" role="alert"><%=request.getAttribute("RESP_MSG").toString()%></div>
         <% }
         }
         %>
      </div>

      <div class="form-label-group">
        <input type="email" name="email" id="email" class="form-control" placeholder="Email address" required autofocus>
        <label for="email">Email address</label>
      </div>

      <div class="form-label-group">
        <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
        <label for="password">Password</label>
      </div>

      <div class="form-label-group">
          <input type="text" name="firstName" id="firstName" class="form-control" placeholder="First Name" required>
          <label for="firstName">First Name</label>
      </div>

      <div class="form-label-group">
          <input type="text" name="lastName" id="lastName" class="form-control" placeholder="Last Name" required>
          <label for="lastName">Last Name</label>
      </div>

      <div class="form-label-group">
          <input type="text" name="address" id="address" class="form-control" placeholder="Address" required>
          <label for="address">Address</label>
      </div>

      <div class="form-label-group">
          <input type="text" name="phone" id="phone" class="form-control" placeholder="Phone" required>
          <label for="phone">Phone</label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
      <div class="form-label-group mt-4 text-center">
        <p>Already a member? <a href="${pageContext.request.contextPath}/login">Login</a></p>
      </div>
      <p class="mt-5 mb-3 text-muted text-center">Flyway Inc. &copy; 2022</p>
    </form>

</body>
</html>
