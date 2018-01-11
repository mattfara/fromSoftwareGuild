<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HERO - Super Persons Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/herostyling.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>HERO - Super Persons</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"> <!-- not sure about page names yet -->
                        <a href="${pageContext.request.contextPath}/">
                            Home
                        </a>
                    </li>
                    <li role="presentation" class="active">
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
                <div class="col-md-9">
                    <h2>Selected Super Person: <c:out value="${spvm.superPerson.name}"/> </h2>
                    <hr>
                    <div class="row">
                        <div class="col-md-4"> <!-- left side with pic -->
                            <!-- pic -->
                            <img src="${pageContext.request.contextPath}/images/superpersons/${spvm.superPerson.superPersonId}.jpg" width="200px" 
                                 style="border: solid black;"/><br/><br/>
                            <!-- good or evil -->
                            <p>Reputation: 
                                <c:choose>
                                    <c:when test="${empty spvm.superPerson.isGood}">
                                        <c:out value="Undetermined"/>
                                    </c:when>
                                    <c:when test="${not spvm.superPerson.isGood}">
                                        <c:out value="Evil"/>
                                    </c:when>
                                    <c:when test="${spvm.superPerson.isGood}">
                                        <c:out value="Good"/>
                                    </c:when>
                                </c:choose>
                            </p>
                            <!-- Description -->
                            <p>Description: 
                                <c:out value="${spvm.superPerson.description}"/>
                            </p>
                            <!-- Orgs-->
                        </div>
                        <div class="col-md-4">
                            <h4> Powers </h4>
                            <table class="table table-hover">
                                <tr>
                                    <th width="5%">#</th>
                                    <th width="95%">Power</th>
                                </tr>
                                <c:forEach
                                    items="${spvm.powers}"
                                    var="currentPower"
                                    varStatus="loop">
                                    <tr>
                                        <td>
                                            <c:out value="${loop.count}"/>
                                        </td>
                                        <td>
                                            <c:out value="${currentPower.name}"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>                                         
                            <h4> Organizations </h4>
                            <table class="table table-hover">
                                <tr>
                                    <th width="5%">#</th>
                                    <th width="70%">Organization</th>
                                    <th width="25%">Reputation</th>
                                </tr>
                                <c:forEach
                                    items="${spvm.organizations}"
                                    var="currentOrg"
                                    varStatus="loop">
                                    <tr>
                                        <td>
                                            <c:out value="${loop.count}"/>
                                        </td>
                                        <td>
                                            <c:out value="${currentOrg.name}"/>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${empty currentOrg.isGood}">
                                                    <c:out value="Undetermined"/>
                                                </c:when>
                                                <c:when test="${not currentOrg.isGood}">
                                                    <c:out value="Evil"/>
                                                </c:when>
                                                <c:when test="${currentOrg.isGood}">
                                                    <c:out value="Good"/>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>    
                        </div>
                        <div class="col-md-4"> <!-- right side with sighting list -->
                            <h4>Recent Sightings</h4>
                            <table class="table table-hover">
                                <tr>
                                    <th width="5%">#</th>
                                    <th width="60%">Location</th>
                                    <th width="35%">Date</th>
                                </tr>
                                <tbody> 
                                    <c:forEach 
                                        items="${spvm.sightings}" 
                                        var="currentSighting" 
                                        varStatus="loop">
                                        <tr> 
                                            <td><c:out value="${loop.count}"/></td>
                                            <td><c:out value="${currentSighting.location.name}"/></td>                                           
                                            <td><c:out value="${currentSighting.date}"/></td>                                           
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div> <!-- End col div -->
                <div class="col-md-3">
                    <sf:form class="form-horizontal" role="form" modelAttribute="button"
                             action="${pageContext.request.contextPath}/superperson/displayCreateSuperPersonPage" method="POST">                             
                        <input type="submit" class="btn btn-default" name="button" value="Add Super Person" />
                    </sf:form>                       
                    <h2>Super Persons</h2>
                    <div><!-- should be a table -->
                        <table id="superPersonTable" class="table table-hover">
                            <tr>
                                <th width="5%">#</th>
                                <th width="50%">Super Person</th>
                                <th width="45%"></th>
                            </tr>
                            <tbody>
                                <c:forEach 
                                    items="${spvmList}" 
                                    var="currentSPVM" 
                                    varStatus="loop">
                                    <tr> <!-- should include links to the locations page, sightings page, and superperson page -->
                                        <td><a href="chooseSuperPerson?superPersonClicked=${currentSPVM.superPerson.superPersonId}"><c:out value="${loop.count}"/></a></td>
                                        <td><c:out value="${currentSPVM.superPerson.name}"/></td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/superperson/displayUpdateSuperPersonPage?superPersonToUpdate=${currentSPVM.superPerson.superPersonId}">Edit</a>
                                            <a href="${pageContext.request.contextPath}/superperson/delete_superperson?superPersonToDelete=${currentSPVM.superPerson.superPersonId}">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <sf:form class="form-horizontal" role="form" modelAttribute="button"
                             action="${pageContext.request.contextPath}/superperson/pageOver?pageOffset=${incomingOffset+10}" method="GET">                             
                            <input type="submit" class="btn btn-default" name="button" value="Next Page" <c:if test="limitReached">disabled</c:if>/>
                    </sf:form>
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
<!-- NOTES 
To do 
-->