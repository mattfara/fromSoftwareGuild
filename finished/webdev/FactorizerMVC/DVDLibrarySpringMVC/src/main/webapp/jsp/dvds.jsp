<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>All DVDs Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <!-- top half -->
            <div class="row">
                <!-- stick this button into a form -->
                <form role="form" method="POST"> <!-- this needs to show differnt info -->
                <button formaction="createDVD">Create DVD</button>
                </form>
                <form class="form-inline">
                    <div class="form-group">
                        <button >Search</button>
                        <select>
                            <option value="" disabled>Search Category</option>
                            <option value="title">Title</option>
                            <option value="releaseYear">Release Year</option>
                            <option value="director">Director</option>
                            <option value="rating">Rating</option>
                        </select>
                        <input type="search" placeholder="search term"/>
                    </div>
                </form>
                
            </div>    
            
            
            <hr/>
            
            <div class="row"> <!-- for error messages -->
                
            </div>
        
        
        <!-- bottom half -->
        
                <div class="row">
    <!-- 
        Add a col to hold the summary table - have it take up half the row 
    -->
    <div class="col-md-6">
        <h2>My DVDs</h2>
        <table id="contactTable" class="table table-hover">
            <tr>
                <th width="20%">Title</th>
                <th width="20%">Release Year</th>
                <th width="10%">Director</th>
                <th width="10%">Rating</th>
                <th width="20%"></th>
            </tr>
            <c:forEach var="currentDVD" items="${dvdList}"> <!-- set up to receive list-->
        <tr>
            <td>
                <a href="displayDVDDetails?dvdId=${currentDVD.dvdId}">
             <c:out value="${currentDVD.title}"/> 
            </td>
            <td>
                <c:out value="${currentDVD.releaseYear}"/>
            </td>
            <td>
                <c:out value="${currentDVD.director}"/>
            </td>
            <td>
                <c:out value="${currentDVD.rating}"/>
            </td>
            <td>
                <a href="editDVD?dvdId=${currentDVD.dvdId}">Edit</a> | <a href="#" class="confirmDelete" data-toggle="modal" data-target="#myModal" data-dvd-id="${currentDVD.dvdId}">Delete</a> 
            </td>
        </tr>
    </c:forEach>
        </table>                    
    </div> <!-- End col div -->
    <!-- 
        Add col to hold the new contact form - have it take up the other 
        half of the row
    -->
    <div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Confirmation</h4>
      </div>
      <div class="modal-body">
        <p>Are you sure you want to delete this Dvd from your collection?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a id="confirmDelete" href="displayDVDsPage"><button type="button">Yes</button></a> <!-- not sure how the currentDVD.dvdId can get here. Can I use the data-dvd-id trick i found? -->
      </div>
    </div>

  </div>
</div>
</div> <!-- End row div -->
</div>
                
                
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/myJS.js"></script>

    </body>
</html>

