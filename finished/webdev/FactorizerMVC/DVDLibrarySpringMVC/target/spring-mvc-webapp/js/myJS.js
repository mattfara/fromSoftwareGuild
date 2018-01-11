/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(".confirmDelete").on("click", function(){
   var dvdToDelete = $(this).data("dvd-id");
   // need to send this piece of info on a query string to the controller
   //which controller method? @RequestMapping(value="/deleteDvd"
   $(".modal-body #dvdId").val(dvdToDelete);
});