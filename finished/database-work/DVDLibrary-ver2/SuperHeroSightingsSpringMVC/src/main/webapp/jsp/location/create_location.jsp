<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Location</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/herostyling.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/mapstyling.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Add Location</h1>
                </div>
            </div>
            <hr/>

            <div class="row">
                <sf:form id="createLocationForm" class="form-horizontal" commandName="clcm"
                         action="${pageContext.request.contextPath}/location/createLocation" method="GET">


                    <!-- text NAME -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Name </label>
                        <div class="col-xs-5">

                            <input id="name" name="name" type="text" path="name" placeholder="Enter name" value="${clcm.name}"/><span><sf:errors path="name" cssclass="error"></sf:errors></span>
                            </div>
                        </div>



                        <!-- textarea DESCRIPTION -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Description</label>
                            <div class="col-xs-5">
                            <sf:errors path="description" cssclass="error"></sf:errors>
                            <textarea name="description" id="description" class="form-control" rows="5" path="description" placeholder="Enter description">${clcm.description}</textarea>
                        </div>
                    </div>



                    <!-- text STREET -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Street </label>
                        <div class="col-xs-5">

                            <input id="street" name="street" type="text" path="street" placeholder="Enter street" value="${clcm.street}"/>
                            <span><sf:errors path="street" cssclass="error"></sf:errors></span>
                            </div>
                        </div>



                        <!-- text CITY-->

                        <div class="form-group">
                            <label class="col-xs-3 control-label">City </label>
                            <div class="col-xs-5">

                                <input id="city" name="city" type="text" path="city" placeholder="Enter city" value="${clcm.city}"/>
                            <span><sf:errors path="city" cssclass="error"></sf:errors></span>
                            </div>
                        </div>

                        <!-- text STATE -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label">State </label>
                            <div class="col-xs-5">

                                <input id="state" name="state" type="text" path="state" placeholder="Enter state" value="${clcm.state}"/>
                            <span><sf:errors path="state" cssclass="error"></sf:errors></span>
                            </div>
                        </div>

                        <!-- text ZIPCODE -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Zipcode </label>
                            <div class="col-xs-5">

                                <input id="zipcode" name="zipcode" type="text" path="zipcode" placeholder="Enter zipcode" value="${clcm.zipcode}"/>
                            <span><sf:errors path="zipcode" cssclass="error"></sf:errors></span>
                            </div>
                        </div>

                            
                            <input id="submit" type="button" value="Geocode" style="margin-left:350px; margin-bottom: 10px;">


                        <!-- text LATITUDE -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Latitude </label>
                            <div class="col-xs-5">

                                <input id="latitude" name="latitude" type="text" path="latitude" placeholder="Enter latitude" value="${clcm.latitude}"/>
                            <span><sf:errors path="latitude" cssclass="error"></sf:errors></span>
                            </div>
                        </div>

                        <!-- text LONGITUDE -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Longitude </label>
                            <div class="col-xs-5">

                                <input id="longitude" name="longitude" type="text" path="longitude" placeholder="Enter longitude" value="${clcm.longitude}"/>
                            <span><sf:errors path="longitude" cssclass="error"></sf:errors></span>
                            </div>
                        </div>


                        <!-- BUTTONS -->

                        <div class="form-group">

                            <div class="col-xs-5 col-xs-offset-3">

                                <button type="submit" class="btn btn-default" id="btnCreateLocation">Submit Location</button>

                                <button type="submit" class="btn btn-default" id="btnCancel" formaction="${pageContext.request.contextPath}/location/locations" formmethod="GET">Cancel</button>

                        </div>

                    </div>

                </div>
            </sf:form>

            <div id="map"></div>
            <!-- Main Page Content Start -->
            <!-- Main Page Content Stop -->
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script>
                function initMap() {
                    var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 8,
                        center: {lat: 41.0695166, lng: -81.518073}
                    });
                    var geocoder = new google.maps.Geocoder();

                    document.getElementById('submit').addEventListener('click', function () {
                        geocodeAddress(geocoder, map);
                    });
                }

                function geocodeAddress(geocoder, resultsMap) {
                    var address = document.getElementById('street').value + ' ' + document.getElementById('city').value + ' ' + document.getElementById('state').value + ' ' + document.getElementById('zipcode').value;
                    geocoder.geocode({'address': address}, function (results, status) {
                        if (status === 'OK') {
                            var coords = results[0].geometry.location;
                            resultsMap.setCenter(coords);
                            var marker = new google.maps.Marker({
                                map: resultsMap,
                                position: coords
                            });
                            
                          
                            document.getElementById('latitude').value = coords.lat();
                            document.getElementById('longitude').value = coords.lng();
                            
                            
                        } else {
                            alert('Geocode was not successful for the following reason: ' + status);
                        }
                    });
                }
            </script>
            <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC84zAyiffzxBzIApQHoin_5xQUp83eHSo&callback=initMap"></script>
    </body>
</html>