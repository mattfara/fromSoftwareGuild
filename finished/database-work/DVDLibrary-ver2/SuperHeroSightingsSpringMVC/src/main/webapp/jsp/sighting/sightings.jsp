<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HERO - Sightings Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/herostyle.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/herostyling.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>HERO - Super Person Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"> <!-- not sure about page names yet -->
                        <a href="${pageContext.request.contextPath}/">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/superperson/superpersons">
                            Super Persons
                        </a>
                    </li>
                    <li role="presentation" class="active">
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
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <div class="col-md-12">
                        <div class="row">
                            <h2>Selected Sighting</h2>
                            <hr>
                            Date: <c:out value="${svm.sighting.date}"/><br/>
                            Location: <c:out value="${svm.location.name}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Super Persons</h3>
                            <div><!-- should be a table -->
                                <table id="sightingTable" class="table table-hover">
                                    <tr>
                                        <th width="5%">#</th>
                                        <th width="25%">Name</th>
                                        <th width="70%"></th>
                                    </tr>
                                    <tbody> <!-- will this throw a null pointer if the initial page load doesn't have svm on the model?  if so, just put an empty svm on the model in the initial page load.... -->
                                        <c:forEach 
                                            items="${svm.superPersons}" 
                                            var="currentSuperPerson" 
                                            varStatus="loop">
                                            <tr> <!-- should include links to the locations page, sightings page, and superperson page -->
                                                <td><c:out value="${loop.count}"/></td>
                                                <td><c:out value="${currentSuperPerson.name}"/></td><!-- name -->                                           
                                                <td><img src="${pageContext.request.contextPath}/images/superpersons/${currentSuperPerson.superPersonId}.jpg" width="100%"/></td><!-- image -->                                          
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <h3>Description</h3>
                            <c:out value="${svm.sighting.description}"/> <!-- big box of text -->
                        </div>
                    </div>
                </div> <!-- End col div -->
                <!-- 
                    Add col to hold the search form - have it take up the other 
                    half of the row
                -->
                <div class="col-md-6">
                    <sf:form class="form-horizontal" role="form" modelAttribute="button"
                             action="${pageContext.request.contextPath}/sighting/displayCreateSightingPage" method="POST">                             
                        <input type="submit" class="btn btn-default" name="button" value="Report Sighting" />
                    </sf:form>
                    <h2>Sightings</h2>
                    <div><!-- should be a table -->
                        <table id="sightingTable" class="table table-hover">
                            <tr>
                                <th width="5%">#</th>
                                <th width="20%">Date</th>
                                <th width="35%">Super Persons</th>
                                <th width="25%">Location</th>
                                <th width="15%"></th>
                            </tr>
                            <tbody>
                                <c:forEach 
                                    items="${svmList}" 
                                    var="currentSVM" 
                                    varStatus="loop">
                                    <tr> 
                                        <td><a href="chooseSighting?sightingClicked=${currentSVM.sighting.sightingId}"><c:out value="${loop.count}"/></a></td>
                                        <td><c:out value="${currentSVM.sighting.date}"/></td>                                           
                                        <td>
                                            <c:forEach 
                                                items = "${currentSVM.superPersons}"
                                                var = "currentSuperPerson"
                                                varStatus = "innerLoop">
                                                <c:out value="${innerLoop.count}. ${currentSuperPerson.name}" /><br/>
                                            </c:forEach>
                                        </td>                                         
                                        <td><c:out value="${currentSVM.location.name}"/></td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/sighting/displayUpdateSightingPage?sightingToUpdate=${currentSVM.sighting.sightingId}">Edit</a><br/>
                                            <a href="${pageContext.request.contextPath}/sighting/delete_sighting?sightingToDelete=${currentSVM.sighting.sightingId}">Delete</a>
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
    </body>
</html>