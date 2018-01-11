<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Report Sighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/herostyling.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Report Sighting</h1>
                    <p>NOTE: If the new organization also has a new location, you must <a href="${pageContext.request.contextPath}/location/displayCreateLocationPage">register the location first</a></p>
                </div>
            </div>
            <hr/>
            <div class="row"> <!-- form wide errors? -->

            </div>
            <div class="row">
                <sf:form id="createSightingForm" class="form-horizontal" commandName="cscm"
                         action="${pageContext.request.contextPath}/sighting/createSighting" method="POST">

                    <!-- DATE -->

                    <div class="form-group">

                        <label class="col-xs-3 control-label">Date </label>
                        <div class="col-xs-5">

                            <input id="date" name="date" type="date" path="date" value="${cscm.date}"/><span><sf:errors path="date" cssclass="error"></sf:errors></span>
                            </div>
                        </div>

                        <!-- LOCATION -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Location </label>
                            <div class="col-xs-5">
                                <select name="locationId" id="locationId" form="createSightingForm" path="locationId">
                                <c:forEach
                                    items="${svm.locations}" 
                                    var="currentLocation" 
                                    varStatus="loop">
                                    <option value="${currentLocation.locationId}"
                                            <c:if test="${currentLocation.locationId == cscm.locationId}"> selected </c:if>
                                            >${currentLocation.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- SUPER PERSONS -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Super Persons </label>
                        <div class="col-xs-5">
                            <select multiple name="superPersons" form="createSightingForm" path="superPersons">
                                <c:forEach
                                    items="${svm.superPersons}" 
                                    var="currentSuperPerson" 
                                    >
                                    <option value="${currentSuperPerson.superPersonId}"
                                            <c:forEach
                                                items="${cscm.superPersons}"
                                                var="currentSelectedSpId">
                                                <c:if test="${currentSuperPerson.superPersonId == currentSelectedSpId}">
                                                    selected
                                                </c:if>
                                            </c:forEach>
                                            >${currentSuperPerson.name}</option>
                                </c:forEach>
                            </select><span><sf:errors path="superPersons" cssclass="error"></sf:errors></span>
                            </div>
                        </div>

                        <!-- DESCRIPTION -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Description</label>
                            <div class="col-xs-5">
                            <sf:errors path="description" cssclass="error"></sf:errors>
                                <textarea name="description" id="description" class="form-control" rows="5"
                                          placeholder="Enter Description" path="description">${cscm.description}</textarea>
                        </div>
                    </div>

                    <!-- BUTTONS -->

                    <div class="form-group">
                        <div class="col-xs-5 col-xs-offset-3">
                            <button type="submit" class="btn btn-default" id="btnCreateSighting">Report Sighting</button>
                            <button type="submit" class="btn btn-default" id="btnCancel" formaction="${pageContext.request.contextPath}/sighting/sightings" formmethod="GET">Cancel</button>
                        </div>
                    </div>
                </sf:form>
            </div>
        </div>

        <!-- Main Page Content Start -->
        <!-- Main Page Content Stop -->
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>