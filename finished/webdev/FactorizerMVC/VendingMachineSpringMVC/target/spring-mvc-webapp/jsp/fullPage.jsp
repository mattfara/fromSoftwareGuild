<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Slots</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/vendingStyles.css" rel="stylesheet">
        <!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <a type="button" id="modalButton" data-toggle="modal" data-target="#myModal" style="color:white;">Hidden Modal</a>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        
        <h4 class="modal-title">Admin login</h4>
      </div>
      <div class="modal-body">
          <form role="form">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <div class="form-group">
                  <label for="pwBox">Enter password: </label>
                  <input type="password"/>
                  <div class="btn-group">
                      <button type="password" placeholder="password">Submit</button>
                    </div>
                  
                  </div>
              </div>
              
          </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
        
        
        <header>
            <div class="container-fluid" id="header-bg-image">
                <h1 class="text-center" id="header-h1"> Vending Machine </h1>
            </div>
        </header>

        <hr>
        <div class="container-fluid" id="main-container"> <!--supposedly don't need to nest containers within containers, though you can have separate containers-->
            <div class="row" id="main-row"> <!-- MAIN ROW-->



                <div class="col-md-9" id="main-product-col"><!-- MAIN PRODUCT COL -->
                    
                        <c:forEach var="currentSlot" items="${allSlots}" >
                            <div class="col-md-4 slot">
                                <a href="clickSlot?slotNum=${currentSlot.slotNum}">
                                    <p class="idNum">${slotNum}</p>
                                    <div class="row text-center prodName"><c:out value="${currentSlot.productName}"/></div>
                                    <div class="row text-center prodName"><c:out value="Calories: ${currentSlot.calories}"/></div>
                                    <div class="row text-center prodName"><c:out value="$${currentSlot.price}"/></div>
                                    <div class="row text-center prodName"><c:out value="${currentSlot.stock} left" /></div>
                                </a>
                            </div>
                        </c:forEach>
                    
                </div><!-- end main product col-->



                <div class="col-md-3" id="main-control-col"><!-- MAIN CONTROL COL -->
                    <!-- should have three rows -->
                    <div class="row" id="money-in-row"><!-- MONEY IN ROW -->
                        <form class="form-horizontal" role="form">
                                                        
                            <div class="form-group">
                                <label for="money-in-view">Total $ In</label>
                                <input class="narrowInputDisplay" type="text" id="money-in-view" value="${moneyIn}" readonly />
                            </div>
                            <div class="btn-group">
                            <a class="asButton" href="insertMoney?coinClicked=dollar" type="button">Add Dollar</a>
                            <a class="asButton" href="insertMoney?coinClicked=quarter" type="button">Add Quarter</a>
                            </div>
                            <div class="btn-group">
                            <a class="asButton" href="insertMoney?coinClicked=dime" type="button">Add Dime</a>
                            <a class="asButton" href="insertMoney?coinClicked=nickel" type="button">Add Nickel</a>
                            </div>
                        </form>
                    </div><!-- end money in row -->
                    <hr>
                    <div class="row" id="messages-row"><!-- MESSAGES ROW -->
                        <!-- needs four rows -->

                        <form role="form" id="purchase-form">
                            <div class="form-group">
                                <label for="messages-view"> Messages </label>
                                <input type="text" id="messages-view" value="${message}"readonly/>
                            </div>
                            <div class="form-group">
                                <label for="current-item">Item: </label>
                                <input type="text"
                                       
                                       class="form-control narrowInputDisplay"
                                       id="current-item"
                                       value="${currentItem}"
                                       required/>
                                <a class="asButton" href="vend" type="button"> Make Purchase </a>
                            </div>
                        </form>


                    </div><!-- end messages row -->
                    <!-- END MESSAGES PORTION -->

                    <hr>
                    <div class="row" id="change-row"><!-- CHANGE ROW -->
                        <!--needs three rows--><!-- this shoudl probably be a form too -->
                        <div class="row">Change</div>

                        <form role="form" id="return-change-form">
                            <div class="form-group">
                                <c:if test="${quarters > 0}">
                                    <c:out value="${quarters} quarters"/><br>
                                </c:if>
                                <c:if test="${dimes > 0}">
                                    <c:out value="${dimes} dimes"/><br>
                                </c:if>
                                <c:if test="${nickels > 0}">
                                    <c:out value="${nickels} nickels"/><br>
                                </c:if>
                                <c:if test="${pennies > 0}">
                                   <c:out value="${pennies} pennies"/>     
                                </c:if>
                                <a class="asButton" type="button"
                                   href="coinReturn"                                        
                                        >
                                    
                                    Change Return
                                </a>
                            </div>
                        </form>
                    </div><!-- end change row -->
                </div> <!-- end main control col -->
                
            </div> <!-- end main row -->
        </div> <!-- end main container -->
        
    </div>
</body>
</html>

<!-- 
For the form which contains the coin buttons, how could you use 
the formaction attribute with real html5 button tags?


-->