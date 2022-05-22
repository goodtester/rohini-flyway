<!DOCTYPE html>
<%@ page import="com.rohini.flyway.models.Airline"%>
<%@ page import="com.rohini.flyway.models.Airport"%>
<%@ page import="java.util.List"%>

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
                        <h1 class="fw-bolder mb-1">Add Flight</h1>
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
                        <form action="${pageContext.request.contextPath}/admin/add-flight" method="POST">
                            <div class="mb-3 row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <span class="form-label">Airline</span>
                                        <select class="form-control" id="airlineCode" name="airlineCode" aria-label="From">
                                            <%
                                                List<Airline> airlines = (List<Airline>) request.getAttribute("airlines");
                                                for(Airline airline:airlines) {
                                            %>
                                                    <option value="<%= airline.getAirlineCode() %>" name="<%= airline.getAirlineCode() %>" ><%= airline.getAirlineCode() + " (" + airline.getAirlineName() + ")"  %></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <span class="form-label">Flight Number</span>
                                        <input type="text" class="form-control" id="flightNumber" name="flightNumber">
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <span class="form-label">Flying from</span>
                                        <select class="form-control" id="sourceAirport" name="sourceAirport" aria-label="From">
                                            <%
                                                List<Airport> sourceAirports = (List<Airport>) request.getAttribute("airports");
                                                for(Airport airport:sourceAirports) {
                                            %>
                                                    <option value="<%= airport.getAirportCode() %>" name="<%= airport.getAirportCode() %>" ><%= airport.getAirportCode() %></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <span class="form-label">Flying To</span>
                                        <select class="form-control" id="destinationAirport" name="destinationAirport" aria-label="From">
                                            <%
                                                List<Airport> destAirports = (List<Airport>) request.getAttribute("airports");
                                                for(Airport airport:destAirports) {
                                            %>
                                                    <option value="<%= airport.getAirportCode() %>" name="<%= airport.getAirportCode() %>"><%= airport.getAirportCode() %></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <span class="form-label">Start Time</span>
                                        <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                                            <input type="text" name="startTime" class="form-control datetimepicker-input" data-target="#datetimepicker1"/>
                                            <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                                                <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <span class="form-label">End Time</span>
                                        <div class="input-group date" id="datetimepicker2" data-target-input="nearest">
                                            <input type="text" name="endTime" class="form-control datetimepicker-input" data-target="#datetimepicker2"/>
                                            <div class="input-group-append" data-target="#datetimepicker2" data-toggle="datetimepicker">
                                                <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <span class="form-label">Number of Stops</span>
                                        <select class="form-control" id="stops" name="stops">
                                            <option value="0">Non-stop</option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <span class="form-label">Total Seats</span>
                                        <input type="text" class="form-control" id="totalSeats" name="totalSeats">
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <span class="form-label">Price</span>
                                        <input type="text" class="form-control" id="price" name="price">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <span class="form-label">&nbsp;</span>
                                        <button type="submit" class="btn btn-primary float-right">Add Flight</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <br />

            </div>
        <div>
    </div>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker1').datetimepicker({format: 'MM/DD/YYYY HH:mm'});
            $('#datetimepicker2').datetimepicker({format: 'MM/DD/YYYY HH:mm'});
        });
    </script>
</body>
</html>

