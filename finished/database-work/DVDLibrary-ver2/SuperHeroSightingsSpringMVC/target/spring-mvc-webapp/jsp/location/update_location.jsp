<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Update Location</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/herostyling.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Update Location</h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-xs-9 col-xs-offset-3">
                </div>
            </div>
            <div class="row">
                <sf:form id="updateLocationForm" class="form-horizontal" commandName="ulcm"
                         action="${pageContext.request.contextPath}/location/updateLocation" method="POST">
                         


                    <input type="hidden" name="locationId" value="${ulcm.locationId}" path="locationId"/>
                    <input type="hidden" name="addressId" value="${ulcm.addressId}" path="addressId"/>

                    <!-- NAME -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Name </label>
                        <div class="col-xs-5">
                            <sf:errors path="name" cssclass="error"></sf:errors>
                            <input  type="text" value="${ulcm.name}" name="name" id="name" placeholder="Enter Name" form="updateLocationForm" path="name"/>
                        </div>
                    </div>

                    <!-- STREET -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Street </label>
                        <div class="col-xs-5">
                            <sf:errors path="street" cssclass="error"></sf:errors>
                            <input  type="text" value="${ulcm.street}" name="street" id="street" placeholder="Enter Street" form="updateLocationForm" path="street"/>
                        </div>
                    </div>

                    <!-- CITY -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">City </label>
                        <div class="col-xs-5">
                            <sf:errors path="city" cssclass="error"></sf:errors>
                            <input  type="text" value="${ulcm.city}" name="city" id="city" placeholder="Enter City" form="updateLocationForm" path="city"/>
                        </div>
                    </div>

                    <!-- STATE -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">State </label>
                        <div class="col-xs-5">
                            <sf:errors path="state" cssclass="error"></sf:errors>
                            <input  type="text" value="${ulcm.state}" name="state" id="state" placeholder="Enter State (2-Letters)" form="updateLocationForm" path="state"/>
                        </div>
                    </div>

                    <!-- ZIPCODE -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Zip Code </label>
                        <div class="col-xs-5">
                            <sf:errors path="zipcode" cssclass="error"></sf:errors>
                            <input type="text" value="${ulcm.zipcode}" name="zipcode" placeholder="Enter Zip Code" id="zipcode" form="updateLocationForm" path="zipcode"/>
                        </div>
                    </div>

                    <!-- DESCRIPTION -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Description</label>
                        <div class="col-xs-5">
                            <sf:errors path="description" cssclass="error"></sf:errors>
                                <textarea name="description" id="description" class="form-control" rows="5" form="updateLocationForm"  
                                          placeholder="Enter Description" path="description">${ulcm.description}</textarea>         
                        </div>
                    </div>

                    <!-- LATITUDE -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Latitude </label>
                        <div class="col-xs-5">
                            <sf:errors path="latitude" cssclass="error"></sf:errors>
                            <input  type="text" name="latitude"  value="${ulcm.latitude}" id="latitude" placeholder="Enter Latitude" form="updateLocationForm" path="latitude"/>
                        </div>
                    </div>

                    <!-- LONGITUDE -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Longitude </label>
                        <div class="col-xs-5">
                            <sf:errors path="longitude" cssclass="error"></sf:errors>
                            <input  type="text"  value="${ulcm.longitude}" name="longitude" id="longitude" placeholder="Enter Longitude" form="updateLocationForm" path="longitude"/>
                        </div>
                    </div>

                    <!-- BUTTONS -->

                    <div class="form-group">
                        <div class="col-xs-5 col-xs-offset-3">
                            <button type="submit" class="btn btn-default" id="btnUpdateLocation">Update Location</button>
                            <button type="submit" class="btn btn-default" id="btnCancel" formaction="${pageContext.request.contextPath}/location/locations" formmethod="GET">Cancel</button>
                        </div>
                    </div>
                </sf:form>
                </div>
            </div>
            <!-- Main Page Content Start -->
            <!-- Main Page Content Stop -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>