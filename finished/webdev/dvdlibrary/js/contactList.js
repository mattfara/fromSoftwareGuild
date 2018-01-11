$(document).ready(function () {

    //don't think this is necessary
    //loadContacts();
    console.log("The page is loaded");
    //from main page -- NOT for POST


    $('#searchButton').click(function (event) {
        clearDvdTable();
        console.log("Inside click handler for searchButton");
        // check for errors and display any that we have
        // pass the input associated with the add form to the validation function
        var haveValidationErrors = checkAndDisplayValidationErrors($('#search-form').find('input'));
        console.log("Errors were checked");
        // if we have errors, bail out by returning false
        if (haveValidationErrors) {
          console.log("Errors were found");
          return false;
        }

        // if we made it here, there are no errors so make the ajax call
        var searchField = $('#select-search-term').val();
        console.log("searchField: " + searchField);
        var userText = $('#search-term').val();
        console.log("userText: " + userText);
        var url = 'http://localhost:8080/dvds/'+searchField+'\/'+userText;
        console.log("url: " +url);
        //filling the table with search matches
        var contentRows = $('#contentRows');
        $.ajax({
            type: 'GET',
            url: url,
            success: function (dvdArray, status) {
                $.each(dvdArray, function (index, dvd) {
                    var id = dvd.dvdId;
                    var title = dvd.title;
                    var releaseYear = dvd.releaseYear;
                    var director = dvd.director;
                    var rating = dvd.rating;

                    var row = '<tr>';
                        row += '<td><a onclick="showDvdInfo(' + id + ');">' + title + '</a></td>';
                        row += '<td>' + releaseYear + '</td>';
                        row += '<td>' + director + '</td>';
                        row += '<td>' + rating + '</td>';
                        row += '<td><a onclick="showEditForm(' + id + ')">Edit</a></td>';
                        row += '<td><a onclick="deleteDvd(' + id + ')">Delete</a></td>';
                        row += '</tr>';
                    contentRows.append(row);
                });
              }, //end success
              error: function() {
                  $('#errorMessages')
                      .append($('<li>')
                      .attr({class: 'list-group-item list-group-item-danger'})
                      .text('Error calling web service.  Please try again later.'));
              }
        });
    });

    $('#create-dvd-button').click(function (event){

      $('#basicMenu').hide();
      $('.initial').hide();
      $('.other-header').append('<h1> Create DVD </h1>');
      $('.other-header').show();
      $('#dvd-info-form').show();
      $('#add-buttons').show();

    });

    $('#add-cancel-button').click(function(event){
      hideAddForm();
    });

    //to cancel an edit -- this is in the html already -- who wins?
    $('#edit-cancel-button').click(function(event){
      hideEditForm();
    });

    //for saving an edit
    $('save-changes-button').click(function(event){
      var haveValidationErrors = checkAndDisplayValidationErrors($('#big-form').find('input'));

              // if we have errors, bail out by returning false
              if (haveValidationErrors) {
                  return false;
                  console.log("We have errors");
              }

              // if we get to here, there were no errors, so make the Ajax call
              $.ajax({
                 type: 'PUT',
                 url: 'http://localhost:8080/dvd/' + $('#dvdId').val(),
                 data: JSON.stringify({
                   dvdId: $('#edit-dvd-id').val(),
                   title: $('#dvd-title').val(),
                   releaseYear: $('#release-year').val(),
                   director: $('#director').val(),
                   rating: $('#rating').val(),
                   notes: $('#notes').val()
                 }),
                 headers: {
                   'Accept': 'application/json',
                   'Content-Type': 'application/json'
                 },
                 'dataType': 'json',
                  success: function() {
                      // clear errorMessages
                      console.log("data found");
                      $('#errorMessages').empty();
                      hideAddForm();
                      clearDvdTable();
                      showMainMenu();
                      alert("DVD successfully edit");
                 },
                 error: function() {
                   console.log("There was a problem");
                   $('#errorMessages')
                      .append($('<li>')
                      .attr({class: 'list-group-item list-group-item-danger'})
                      .text('Error calling web service.  Please try again later.'));
                 }
             })
          });
    });

function showMainMenu(){
  $('#basicMenu').show();
  $('.initial').show();

}

function clearDvdTable() {
    $('#contentRows').empty();
}

function goBack(){
  $('info-table').empty();
  $('.other-header').empty();
  $('#show-info-dvd').hide();
  //need to empty rows in main table
  $('#basicMenu').show();
  $('.initial').show();
}

function showEditForm(dvdId) {
    // clear errorMessages
    $('#errorMessages').empty();
    // get the contact details from the server and then fill and show the
    // form on success
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/dvd/' + dvdId,
        success: function(dvd, status) {
              $('.other-header').append('<h1>Edit DVD: ' + dvd.title+'</h1>');
              $('#edit-dvd-id').val(dvd.dvdId);
              $('#dvd-title').val(dvd.title);
              $('#director').val(dvd.director);
              $('#release-year').val(dvd.releaseYear);
              $('#rating select').val(dvd.rating).change();
              $('#notes').val(dvd.notes);
          },
          error: function() {
            $('#errorMessages')
               .append($('<li>')
               .attr({class: 'list-group-item list-group-item-danger'})
               .text('Error calling web service.  Please try again later.'));
          }
    });
    $('#basicMenu').hide();
    $('.initial').hide();
    $('.other-header').show();
    $('#dvd-info-form').show();
    $('#edit-buttons').show();
    //here should be something for hiding/showing edit related classes
    // $('#contactTableDiv').hide();
    // $('#editFormDiv').show();
}

function hideAddForm() {
    // clear errorMessages
    $('#errorMessages').empty();
    // clear the form and then hide it
    $('#dvd-title').val('');
    $('#director').val('');
    $('#rating').val('');
    $('#notes').val('');
    $('#basicMenu').show();
    $('.initial').show();
    $('.other-header').empty();
    $('.other-header').hide();
    $('#dvd-info-form').hide();
    $('#add-buttons').hide();
}


function hideEditForm() {
    // clear errorMessages
    $('#errorMessages').empty();
    // clear the form and then hide it
    $('#dvd-title').val('');
    $('#director').val('');
    $('#rating').val('');
    $('#notes').val('');
    $('#basicMenu').show();
    $('.initial').show();
    $('.other-header').empty();
    $('.other-header').hide();
    $('#dvd-info-form').hide();
    $('#edit-buttons').hide();
}

function showDvdInfo(dvdId){
  $('#basicMenu').hide();
  $('.initial').hide();
  $('.other-header').append('<h1>test header</h1>');
  $('.other-header').show();



  var infoContentRows = $('#infoContentRows');
  //need to populate fields in html
  $.ajax({
    type:'GET',
    url: "http://localhost:8080/dvd/" + dvdId,
    success: function(dvd, status) {
          console.log("Got back dvd info");
          var title = dvd.title;
          var releaseYear = dvd.releaseYear;
          var director = dvd.director;
          var rating = dvd.rating;
          var notes = dvd.notes;

          $('.other-header').append('<h1>'+title+'</h1>');
          $('#show-info-dvd').show();
          var row = '<tr>';
              row += '<td>Release Year: </td>';
              row += '<td>' + releaseYear + '</td>';
              row += '</tr>';
          infoContentRows.append(row);

          console.log("First row appended");

          row = '<tr>';
              row += '<td>Director: </td>';
              row += '<td>' + director + '</td>';
              row += '</tr>';
          infoContentRows.append(row);

          console.log("Second row appended");

          row = '<tr>';
              row += '<td>Rating: </td>';
              row += '<td>' + rating + '</td>';
              row += '</tr>';
          infoContentRows.append(row);

          console.log("Third row appended");

          row = '<tr>';
              row += '<td>Notes: </td>';
              row += '<td>' + notes + '</td>';
              row += '</tr>';
          infoContentRows.append(row);

          console.log("Fourth row appended");
    },
    error: function() {
        $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service.  Please try again later.'));
    }
  });
}

function deleteDvd(dvdId) {

    var userChoice = confirm("Are you sure you want to delete this DVD?");
    if (userChoice){

    $.ajax ({
        type: 'DELETE',
        url: "http://localhost:8080/dvd/" + dvdId,
        success: function (status) {
            //loadContacts();
        }
    });
  }
    clearDvdTable();
    hideEditForm();
}

// processes validation errors for the given input.  returns true if there
// are validation errors, false otherwise
function checkAndDisplayValidationErrors(input) {
    // clear displayed error message if there are any
    $('#errorMessages').empty();
    // check for HTML5 validation errors and process/display appropriately
    // a place to hold error messages
    var errorMessages = [];

    // loop through each input and check for validation errors
    input.each(function() {
        // Use the HTML5 validation API to find the validation errors
        if(!this.validity.valid)
        {
            var errorField = $('label[for='+this.id+']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }
    });

    // put any error messages in the errorMessages div
    if (errorMessages.length > 0){
        $.each(errorMessages,function(index,message){
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
        });
        // return true, indicating that there were errors
        return true;
    } else {
        // return false, indicating that there were no errors
        return false;
    }
}
