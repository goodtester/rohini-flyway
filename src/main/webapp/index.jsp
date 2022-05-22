<!DOCTYPE html>
<%@ page import="com.rohini.flyway.models.Airport"%>
<%@ page import="com.rohini.flyway.models.Flight"%>
<%@ page import="java.util.List"%>

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Flyway (Admin panel)" />
    <title>Flyway - Booking site </title>
    <jsp:include page="includes/common.jsp" />
</head>

<body>
    <jsp:include page="nav.jsp" />
    <jsp:include page="payment.jsp" />
    <div class="container mt-5">
        <article>
            <header class="mb-4">
                <h1 class="fw-bolder mb-1">Search Flights</h1>
            </header>
        </article>
        <div class="row">
            <div class="col-md-12 p-3 border">
                <form action="${pageContext.request.contextPath}/index" method="POST">
                    <div class="row py-2">
                        <div class="col-md-6">
                            <div class="form-group">
                                <span class="form-label">Flying from</span>
                                <select class="form-control" id="fromAirport" name="fromAirport" aria-label="From">
                                    <%
                                        List<Airport> sourceAirports = (List<Airport>) request.getAttribute("airports");
                                        for(Airport airport:sourceAirports) {
                                    %>
                                            <option value="<%= airport.getAirportCode() %>" name="<%= airport.getAirportCode() %>" <%= airport.getAirportCode().equals(request.getAttribute("fromAirport"))?"selected":"" %> ><%= airport.getAirportCode() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <span class="form-label">Flying To</span>
                                <select class="form-control" id="toAirport" name="toAirport" aria-label="From">
                                    <%
                                        List<Airport> destAirports = (List<Airport>) request.getAttribute("airports");
                                        for(Airport airport:destAirports) {
                                    %>
                                            <option value="<%= airport.getAirportCode() %>" name="<%= airport.getAirportCode() %>" <%= airport.getAirportCode().equals(request.getAttribute("toAirport"))?"selected":"" %> ><%= airport.getAirportCode() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row py-2">
                        <div class="col-md-4">
                            <div class="form-group">
                                <span class="form-label">Start Time</span>
                                <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                                    <input type="text" name="departureDate" class="form-control datetimepicker-input" data-target="#datetimepicker1" value="<%= request.getAttribute("departureDate") %>"/>
                                    <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <span class="form-label"># of Passengers</span>
                                <select class="form-control" id="passengers" name="passengers">
                                    <option <%= "1".equals(request.getAttribute("passengers"))?"selected":"" %>>1</option>
                                    <option <%= "2".equals(request.getAttribute("passengers"))?"selected":"" %>>2</option>
                                    <option <%= "3".equals(request.getAttribute("passengers"))?"selected":"" %>>3</option>
                                    <option <%= "4".equals(request.getAttribute("passengers"))?"selected":"" %>>4</option>
                                    <option <%= "5".equals(request.getAttribute("passengers"))?"selected":"" %>>5</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <span class="form-label">&nbsp;</span><br/>
                                <button class="btn btn-success shadow" id="button-search" type="submit">Search Flights</button>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
        </div>

        <div class="row mt-2">
            <div class="col-md-12 p-3 border">
                <h5 class="mb-3">Available flights</h5>
                <%
                    if (request.getAttribute("flights") != null)
                    {
                        List<Flight> flights = (List<Flight>) request.getAttribute("flights");
                        for(Flight f:flights) {
                %>
                <div class="card mb-3">
                    <div class="card-header">
                        <h5 class="float-left"><%= f.getAirlineCode() %> <%= f.getFlightNumber() %></h5>
                        <h5 class="float-right">Price: <b class="text-success"><%= f.getPrice() %></b></h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">Departure time: <%= f.getStartTime() %> </p>
                        <p class="card-text">Duration: <%= f.getDuration() %> </p>
                        <p class="card-text">Number of stops:  <%= f.getStops() %> </p>
                    </div>
                    <div class="card-footer">
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#exampleModal" data-whatever="<%= f.getAirlineCode() + " " + f.getFlightNumber() + " @ " + f.getPrice() %>">Book Flight</button>
                    </div>
                </div>
                <%
                    }
                }
                %>

            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker1').datetimepicker({format: 'MM/DD/YYYY HH:mm'});
        });
    </script>
</body>
</html>
