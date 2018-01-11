<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HERO - Organization Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/herostyle.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/herostyling.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>HERO - Super Person Organizations</h1>
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/sighting/sightings">
                            Sightings
                        </a> 
                    </li>
                    <li role="presentation" class="active">
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
                <div class="col-md-8">
                    <h2>Selected Organization: <c:out value="${ovm.organization.name}"/> </h2>
                    <hr>
                    <div class="col-md-6">
                        <div class="row">
                            <img src="${pageContext.request.contextPath}/images/organizations/${ovm.organization.organizationId}.jpg" width="200px" 
                                 style="border: solid black;"/><br/><br/>
                            <p>Location: <c:out value="${ovm.location.name}     "/></p>
                            <p>Reputation: 
                                <c:choose>
                                    <c:when test="${empty ovm.organization.isGood}">
                                        <c:out value="Undetermined"/>
                                    </c:when>
                                    <c:when test="${not ovm.organization.isGood}">
                                        <c:out value="Evil"/>
                                    </c:when>
                                    <c:when test="${ovm.organization.isGood}">
                                        <c:out value="Good"/>
                                    </c:when>
                                </c:choose>
                            </p>
                            <p>Description:<br/>
                                <c:out value="${ovm.organization.description}"/><br/>
                            </p>
                            <p>Address:<br/>
                                <c:out value="${ovm.address.street}"/><br/>
                                <c:out value="${ovm.address.city}, ${ovm.address.state}"/><br/>
                                <c:out value="${ovm.address.zipcode}"/><br/>
                            </p>
                            <p>
                                Contact: <c:out value="${ovm.organization.phone}"/>
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Super Members</h3>
                            <div><!-- should be a table -->
                                <table id="sightingTable" class="table table-hover">
                                    <tr>
                                        <th width="5%">#</th>
                                        <th width="25%">Name</th>
                                        <th width="50%">Image</th>
                                        <th width="20%">Reputation</th>
                                    </tr>
                                    <tbody> <!-- will this throw a null pointer if the initial page load doesn't have svm on the model?  if so, just put an empty svm on the model in the initial page load.... -->
                                        <c:forEach 
                                            items="${ovm.superPersons}" 
                                            var="currentSuperPerson" 
                                            varStatus="loop">
                                            <tr> <!-- should include links to the locations page, sightings page, and superperson page -->
                                                <td><c:out value="${loop.count}"/></td>
                                                <td><c:out value="${currentSuperPerson.name}"/></td><!-- name -->                                           
                                                <td><img src="${pageContext.request.contextPath}/images/superpersons/${currentSuperPerson.superPersonId}.jpg" width="80px" 
                                                         style="border: solid black;"/></td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${empty currentSuperPerson.isGood}">
                                                            <c:out value="Undetermined"/>
                                                        </c:when>
                                                        <c:when test="${not currentSuperPerson.isGood}">
                                                            <c:out value="Evil"/>
                                                        </c:when>
                                                        <c:when test="${currentSuperPerson.isGood}">
                                                            <c:out value="Good"/>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div> <!-- End col div -->
                <!-- 
                    Add col to hold the search form - have it take up the other 
                    half of the row
                -->
                <div class="col-md-4">
                    <sf:form class="form-horizontal" role="form" modelAttribute="button"
                             action="${pageContext.request.contextPath}/organization/displayCreateOrganizationPage" method="POST">                             
                        <input type="submit" class="btn btn-default" name="button" value="Add Organization" />
                    </sf:form>
                    <h2>Organizations</h2>
                    <div><!-- should be a table -->
                        <table id="organizationTable" class="table table-hover">
                            <tr>
                                <th width="5%">#</th>
                                <th width="35%">Organization</th>
                                <th width="15%">Reputation</th>
                                <th width="45%"></th>
                            </tr>
                            <tbody>
                                <c:forEach 
                                    items="${ovmList}" 
                                    var="currentOVM" 
                                    varStatus="loop">
                                    <tr> 
                                        <td><a href="chooseOrganization?organizationClicked=${currentOVM.organization.organizationId}"><c:out value="${loop.count}"/></a></td>
                                        <td><c:out value="${currentOVM.organization.name}"/></td>                                           
                                        <td>
                                            <c:choose>
                                                <c:when test="${empty currentOVM.organization.isGood}">
                                                    <c:out value="Undetermined"/>
                                                </c:when>
                                                <c:when test="${not currentOVM.organization.isGood}">
                                                    <c:out value="Evil"/>
                                                </c:when>
                                                <c:when test="${currentOVM.organization.isGood}">
                                                    <c:out value="Good"/>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/organization/displayUpdateOrganizationPage?organizationToUpdate=${currentOVM.organization.organizationId}">Edit</a> |
                                            <a href="${pageContext.request.contextPath}/organization/delete_organization?organizationToDelete=${currentOVM.organization.organizationId}">Delete</a>
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