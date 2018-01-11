//home.js for VENDING MACHINE//

//https://github.com/MikeMcl/big.js/ for big.js
//script called in home.html

var userMoney = new Big(0);

$(document).ready(function () {
    loadAllProducts();
});

function loadAllProducts(){

  $.ajax({
    type:'GET',
    url:'http://localhost:8080/items',
    success: function(itemArray){

      var rowCount=0;
      var newProduct; //flesh this out
      //for each JSON object, check whether the productCount is divisible by 3
      $.each(itemArray, function(index, item){
        if (index%3===0){
          rowCount++;
          var newRow = makeNewRow(rowCount);
          $('#main-product-col').append(newRow);
        }
//bootstrap will do the formatting of products into "rows" for you
        var id = item.id;
        var name = item.name;
        var price = item.price;
        var quantity = item.quantity;

        //maybe make a separate method to create the html?

        newProduct = makeNewProductCol(rowCount, id, name, price, quantity);
                            //not sure if this is right selector....
        $('#main-product-col').children().last().append(newProduct);

      });
    },
    error: function(){
      alert("Something went wrong contacting the server");
    }
  });
}
function makeNewRow(rowCount){
  return `<div class="row" id="product-row-${rowCount}"></div>`;
}
function makeNewProductCol(rowCount, id, name, price, quantity){
//with col-X-12 could display as single column on phone
  var fullProductHTML=`<div class='col-md-4' id='row${rowCount}-col${id}' onmouseover='hoverOverItem(this)' onmouseout='stopHovering(this)'>`;
      fullProductHTML+=`<a onclick='processItemClick(${id})'>`;
      fullProductHTML+=`<div class='product' id='product${id}'>`;
      fullProductHTML+=`<p class='idNum'>${id}</p>`;
      fullProductHTML+=`<div class='row text-center prodName'>${name}</div>`;
      fullProductHTML+=`<div class='row text-center prodPrice'>$${price.toFixed(2)}</div>`;
      fullProductHTML+=`<div class='row text-center prodQuantity'> Quantity Left: ${quantity}</div>`;
      fullProductHTML+="</div>";
      fullProductHTML+="</a>";
      fullProductHTML+='</div>'; //last one closing the outer tag

  return fullProductHTML;
}
//not sure how to get this to work....
function hoverOverItem(thing){
  thing.children().addClass('hovering-on-item');
}
function stopHovering(thing){
  thing.children().removeClass('hovering-on-item');
}

function addMoney(money){
  userMoney = userMoney.plus(money);
  updateMoneyInDisplay();
  //validateReturnChange();
  validatePurchase();
}
// coin buttons
function addDollar(){
  userMoney=userMoney.plus(1);
  updateMoneyInDisplay();
  //validateReturnChange();
  validatePurchase();
}
function addQuarter(){
  userMoney=userMoney.plus(0.25);
  updateMoneyInDisplay();
  //validateReturnChange();
  validatePurchase();
}
function addDime(){
  userMoney=userMoney.plus(0.10);
  updateMoneyInDisplay();
  //validateReturnChange();
  validatePurchase();
}
function addNickel(){
  userMoney=userMoney.plus(0.05);
  updateMoneyInDisplay();
  //validateReturnChange();
  validatePurchase();
}

function updateMoneyInDisplay(){
  $('#money-in-view').val((userMoney).toFixed(2));
}

function validatePurchase(){
  var isAnItemSelected = $('#current-item').val().length > 0;
  var didUserAddMoney = $('#money-in-view').val().length > 0;
  var wasAPurchasePlaced = $('#change-return').val().length >0;

  if (isAnItemSelected && didUserAddMoney && !wasAPurchasePlaced){
    $('#purchase-item-button').prop("disabled",false);
  }
  else { //i guess the else switches it back for future rounds
     $('#purchase-item-button').prop("disabled",true);
  }
}


//click purchase item
function processItemClick(id){
  $('#current-item').val(id);
  validatePurchase();
}



function purchaseItem(){

  var currentId = $('#current-item').val();
  //url /money/userMoney/item/id
  var currentMoney = userMoney;
  var url = `http://localhost:8080/money/${currentMoney}/item/${currentId}`;
  clearMessage();


  $.ajax({
    type:'GET',
    url: url, //money url
    success: function(returned){

        var quarters = returned.quarters;
        var dimes = returned.dimes;
        var nickels = returned.nickels;
        var pennies = returned.pennies;

        var changeString = "";

        if (quarters>0){
          changeString+=`${quarters} quarters `;
        }
        if (dimes>0){
          changeString+=`${dimes} dimes `;
        }
        if (nickels>0){
          changeString+=`${nickels} nickels `;
        }
        if (pennies>0){
          changeString+=`${pennies} pennies `;
        }

        if (changeString===""){
          changeString="No change to return"
        }

        $('#change-return').val(changeString);
        enableReturnChangeButton();
        disablePurchaseItemButton();
        $('#messages-view').val("Thank You!!!");
    },
    statusCode: {
      422: function(data){
        var errorMessage = $.parseJSON(data.responseText);
        var problem = errorMessage.message;
        $('#messages-view').val(problem);

      }
    }
  });
  //validateReturnChange();
  disablePurchaseItemButton();
}

function clearMessage(){
  $('#messages-view').val('');
}

function enableReturnChangeButton(){
  $('#return-change-button').prop("disabled",false);
}
function disablePurchaseItemButton(){
  $('#purchase-item-button').prop("disabled",true);
}





function returnChange(userMoney){

  clearUserMoney();
  clearMessage();
  clearItem();
  clearChange();
  clearAllProducts();

  loadAllProducts();
  disableReturnChangeButton();
}

function clearUserMoney(){
  userMoney=0;
  $('#money-in-view').val('');
}

function clearItem(){
  $('#current-item').val('');
}
function clearChange(){
  $('#change-return').val('');
}
function clearAllProducts(){
  $('#main-product-col').empty();
}
function disableReturnChangeButton(){
  $('#return-change-button').prop("disabled",true);
}


function validateReturnChange(){
  var isThereChangeToReturn = $('#money-in-view').val().length > 0;

  if (isThereChangeToReturn){
    $('#return-change-button').prop("disabled",false);//this works but logic seems backwards
  }
  // else {
  //   $('#return-change-button').prop("disabled",false);
  // }
}
