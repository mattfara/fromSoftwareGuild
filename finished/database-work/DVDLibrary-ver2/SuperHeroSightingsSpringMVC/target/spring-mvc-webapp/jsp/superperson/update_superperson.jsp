<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Update Super Person</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-2">
                    <h1>Update Super Person</h1>
                </div>
            </div>
            <hr>
            <div class="row">

                <!-- FORM START -->

                <sf:form id="updateSuperPersonForm" class="form-horizontal" commandName="uspcm"
                         action="${pageContext.request.contextPath}/superperson/updateSuperPerson" method="POST" >

                    <!-- set which model to use for each variable -->

                    <input type="hidden" value="${uspcm.superPersonId}" name="superPersonId">                    

                    <!-- NAME -->

                    <div class="form-group">
                        <label class="col-xs-3 control-label"> Name </label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" name="name"
                                   value="${uspcm.name}" path="name"/><span><sf:errors path="name" cssclass="error"></sf:errors></span>
                        </div>
                    </div>

                    <!-- POWERS -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label"> Powers </label>
                            <div class="col-xs-5">
                                <select multiple name="powers" form="updateSuperPersonForm" path="powers">
                                <c:forEach
                                    items="${spvm.powers}" 
                                    var="currentPower" 
                                    varStatus="loop">
                                    <option value="${currentPower.powerId}"

                                            <c:forEach
                                                items="${uspcm.powers}"
                                                var="currentSelectedPowerId">
                                                <c:if test="${currentPower.powerId == currentSelectedPowerId}">
                                                    selected
                                                </c:if>
                                            </c:forEach>

                                            >${currentPower.name}</option>
                                </c:forEach>
                            </select><span><sf:errors path="powers" cssclass="error"></sf:errors></span>
                            </div>
                        </div>

                        <!-- Organizations -->

                        <div class="form-group">
                            <label class="col-xs-3 control-label"> Organizations </label>
                            <div class="col-xs-5">
                                <span><sf:errors path="organizations" cssclass="error"></sf:errors></span>
                                <select multiple name="organizations" form="updateSuperPersonForm" path="organizations">
                                <c:forEach
                                    items="${spvm.organizations}" 
                                    var="currentOrganization">
                                    <option value="${currentOrganization.organizationId}"

                                            <c:forEach
                                                items="${uspcm.organizations}"
                                                var="currentCMOrg"
                                                >
                                                <c:if test="${currentOrganization.organizationId == currentCMOrg}">
                                                    selected
                                                </c:if>
                                            </c:forEach>

                                            >
                                        ${currentOrganization.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                <!-- DESCRIPTION -->

                <div class="form-group">
                    <label class="col-xs-3 control-label"> Description </label>
                    <div class="col-xs-5">
                        <span><sf:errors path="description" cssclass="error"></sf:errors></span>
                        <textarea rows ="10" type="textarea" class="form-control" name="description" path="description"
                                  >${uspcm.description}</textarea>
                    </div>
                </div>

                <!-- REPUTATION -->

                <div class="form-group">
                    <label class="col-xs-3 control-label">Reputation </label>
                    <div class="col-xs-5">
                        <sf:errors path="reputation" cssclass="error"></sf:errors>
                            <div class="input-group">
                            <c:choose>
                                <c:when test="${not empty uspcm.reputation}">
                                    <c:choose>
                                        <c:when test="${cspcm.reputation eq 'good'}">
                                            <input name="reputation" type="radio" path="reputation" value="good" checked/> Good <br>
                                            <input name="reputation" type="radio" path="reputation"value = "evil"/> Evil <br>
                                        </c:when>
                                        <c:otherwise>
                                            <input name="reputation" type="radio" path="reputation" value="good"/> Good <br>
                                            <input name="reputation" type="radio" path="reputation"value = "evil" checked/> Evil <br>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <input name="reputation" type="radio" path="reputation" value="good"/> Good <br>
                                    <input name="reputation" type="radio" path="reputation"value = "evil"/> Evil <br>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>

                <!-- BUTTONS -->

                <div class="form-group">
                    <div class="col-xs-5 col-xs-offset-3">
                        <button type="submit" class="btn btn-default" id="btnUpdateSuperPerson">Save Edit</button>
                        <button type="submit" class="btn btn-default" id="btnCancel" formaction="${pageContext.request.contextPath}/superperson/superpersons" formmethod="GET">Cancel</button>
                    </div>
                </div>
            </sf:form>
            <!-- FORM END -->                   

        </div>
    </div>
    <!-- Main Page Content Start -->
    <!-- Main Page Content Stop -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>