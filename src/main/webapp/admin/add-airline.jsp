<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Flyway (Admin panel)" />
    <title>Flyway - Booking site </title>
    <jsp:include page="../includes/common.jsp" />
</head>

<body>
    <jsp:include page="../nav.jsp" />
	<!-- Page content-->
	<div class="container mt-5">
	    <div class="row">
	        <div class="col-lg-12">
                <!-- Post content-->
                <article>
                    <!-- Post header-->
                    <header class="mb-4">
                        <!-- Post title-->
                        <h1 class="fw-bolder mb-1">Add Airline</h1>
                    </header>
                </article>

                <div class="card w-75">
                    <div class="card-head">
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
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/admin/add-airline" method="POST">
                            <div class="mb-3 row">
                                <label for="airlineCode" class="col-sm-2 col-form-label">Airline Code</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="airlineCode" name="airlineCode">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="airlineName" class="col-sm-2 col-form-label">Airline Name</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="airlineName" name="airlineName">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label class="col-sm-2 col-form-label">&nbsp;</label>
                                <div class="col-sm-10">
                                    <button type="submit" class="btn btn-primary">Add Airline</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <br />

            </div>
        <div>
    </div>
</body>
</html>

