<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HERO Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/mapstyling.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>HERO - Super Person Sightings</h1>
            
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"> <!-- not sure about page names yet -->
                        <a href="${pageContext.request.contextPath}/">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/superperson/superpersons">
                            Super Persons
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/sighting/sightings">
                            Sightings
                        </a> 
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/organization/organizations">
                            Organizations
                        </a> 
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/location/locations">
                            Locations
                        </a> 
                    </li>
                </ul>    
            </div>
            <!-- Main Page Content Start -->

            <ul class="list-group" id="errorMessages"></ul>
            <div class="row">
                
                <div class="col-md-6">
                    <h2>HERO</h2>
                    <hr>
                    <p>Superhero and supervillain sightings have been increasing at an alarming rate. Supervillains have brought about chaos, fear, and destruction. Superheroes have been doing their part to stop them, while also serving the greater good every day while working with armed forces and first responders. We live in a new world where a heightened sense of awareness is necessary to stay safe. </p>
                    <p>Please use this site to keep track of super person sightings. You can look over information on super persons, sightings, super person organizations, and locations. However, we need your help! Please report information related to super persons and sightings whenever you can. The more information we have, the better. </p>
                    <div id="map"></div> <!-- Google map -->
                </div> <!-- End col div -->
                
                
                <div class="col-md-6">
                    <h2>Most Recent Sightings</h2>

                    <div><!-- should be a table -->
                        <table id="sightingTable" class="table table-hover">
                            <tr>
                                <th width="5%">#</th>
                                <th width="15%">Date</th>
                                <th width="25%">Super Persons</th>
                                <th width="25%">Location</th>
                                <th width="30%"></th>
                            </tr>
                            <tbody>
                                <c:forEach 
                                    items="${svmList}" 
                                    var="currentSVM" 
                                    varStatus="loop">
                                    <tr> <!-- should include links to the locations page, sightings page, and superperson page -->
                                        <td><c:out value="${loop.count}"/></td>
                                        <td><c:out value="${currentSVM.sighting.date}"/></td>                                           
                                        <td>
                                            <c:forEach 
                                                items = "${currentSVM.superPersons}"
                                                var = "currentSuperPerson"
                                                varStatus = "innerLoop">
                                                <c:out value="${innerLoop.count}. ${currentSuperPerson.name}" /><br>
                                            </c:forEach>
                                        </td>                                         

                                        <td><c:out value="${currentSVM.location.name}"/></td>        
                                        <td>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div> <!-- End col div -->

                

            </div> <!-- End row div -->
            <!-- Main Page Content Stop -->                 
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script>
            function initMap() {
                var lati = 41.0695166;
                var long = -81.518073;
                var map = new google.maps.Map(document.getElementById("map"),
                        {zoom: 12, center: new google.maps.LatLng(lati, long)});

            <c:forEach
                items="${svmList}"
                var="currentSVM">

                var marker = new google.maps.Marker({
                    position: {lat: <c:out value='${currentSVM.location.latitude}'/>, lng: <c:out value='${currentSVM.location.longitude}'/>},
                    map: map
                });
            </c:forEach>
            }
        </script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC84zAyiffzxBzIApQHoin_5xQUp83eHSo&callback=initMap"></script>    
    </body>
</html>

<!-- NOTES 
To do 
    -add links to sightings page on the MostRecent list



-->