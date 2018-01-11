
//Tried this way of stopping the reset, but couldn't pull it off
//function submit(){
//	document.getElementById("place-order").addEventListener("click", function(event){event.preventDefault()});
//{


function start(){
	document.forms["order-form"].style.display="none"; //this hides the order form until the user selects pickup or delivery
	document.getElementById("upon-completion").style.display="none"; //this hides the outcome displayed once the order is complete; it is there but invisible to user
}


// both pickup() and delivery() display the same form with once exception: the on-delivery component of the form
function pickup(){
	document.forms["order-form"].style.display="block";
	document.getElementById("on-delivery").style.display="none";
	document.getElementById("startImg").style.display="none";
}

function delivery(){
	document.forms["order-form"].style.display="block";
	document.getElementById("on-delivery").style.display="block"; //needed to include this in case user changes mind from pickup to delivery (otherwise time field remains invisible
	document.getElementById("startImg").style.display="none";

}


//trying to do the functions below with jQuery
/*
function submit(){
	var entree = "";
	var side = "";
	var beverage = "";
	
	
	//make form invisible, show something like "order complete. your food will be ready/arrive at x:xx (ready or arrive depends on whether time value is -1

}
*/

/*
function reset(){
	//not sure how to reset checkboxes yet. found something with jquery, but don't know how to call it with a button or within js
	clearErrors();
	document.forms["order-form"]["yourName"].value="";
	document.forms["order-form"]["yourAddress"].value="";
	document.forms["order-form"]["yourPhone"].value="";
	
	document.forms["order-form"]["yourName"].focus();	
}
*/

/*
function validate(){}


function clearErrors(){
	var formFields = document.forms["order-form"].elements
	for (var el in formFields){
		if (el.parentElement.className.indexOf("has-") != -1) {
			el.parentElement.className="form-group";	
		}
	}
}

*/
