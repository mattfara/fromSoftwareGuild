<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Delete Organization</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1>Delete Organization</h1>
            </div>
            <hr/>
            <h2>Are you sure you want to perform this action?</h2>
        </div>
        <div class="col-xs-12">
            <div class="row">
                <div class="col-md-1 col-md-offset-3">
                    <sf:form class="form-horizontal" role="form" modelAttribute="button"
                             action="redirectToOrganizationsPage" method="GET">                             
                        <input type="submit" class="btn btn-default" name="button" value="Cancel" />
                    </sf:form>
                </div>
                <div class="col-md-8">
                    <sf:form class="form-horizontal" role="form" modelAttribute="button"
                             action="deleteOrganization" method="POST">
                        <input type="hidden" name="organizationToDelete" value="${organizationToDelete}" />
                        <input type="submit" class="btn btn-default" name="button" value="Confirm" />
                    </sf:form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>