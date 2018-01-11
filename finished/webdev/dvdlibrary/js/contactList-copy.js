$(document).ready(function () {

    // Add Button onclick handler
    $('#get-weather-button').click(function (event) {

        // check for errors and display any that we have
        // pass the input associated with the add form to the validation function
        var haveValidationErrors = checkAndDisplayValidationErrors($('#input-form').find('input'));
        //this portion looks in the whole form for any tags of input, and checks that
        // if we have errors, bail out by returning false
        if (haveValidationErrors) {
            return false;
        }

        // if we made it here, there are no errors so make the ajax call
        var myKey = '4fd6546e03854def178a9111c12ff40e';
        var zip = $('#enter-zipcode').val();
        var units = $('#select-units').val();
        var currentWeatherURL='http://api.openweathermap.org/data/2.5/weather?zip='+zip+',us&units='+units+'&APPID='+myKey;
        var forecastURL='http://api.openweathermap.org/data/2.5/weather?zip='+zip+',us&cnt=5&units='+units+'&APPID='+myKey;
        $.ajax ({
            type: 'GET',
            url: currentWeatherURL,
            dataType:'JSON',
            success: function (data, status) {
                $.each(data, function (index, today) {
                    console.log("In the each loop");
                    var cityName = today.name;
                    var condition = today.weather.description;
                    var pic = today.weather.icon;
                    var picURL = 'http://openweathermap.org/img/w/'+pic+'.png';
                    var temp = today.main.temp;
                    var humidity = today.main.humidity;
                    var wind = today.wind.speed;

                    $('#currentConditionsHeader').text(cityName);

                    var row = '<tr>';
                        row += "<td> <img src='" + picURL+ "'>" + condition '</td>';
                        row += '</tr>';
                    contentRows1.append(row);

                    row = '<tr>';
                    row += '<td>' + temp + '</td>';
                    row += '<td>' + humidity + '</td>';
                    row += '<td>' + wind + '</td>';

                    contentRows2.append(row);
                    showResults();
                });
            },
            error: function() {
                $('#errorMessages')
                    .append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service.  Please try again later.'));
            }

        });

        $.ajax ({
            type: 'GET',
            url: forecastURL,
            success: function (weatherArray) {
                var dayCounter = 1;
                var d = new Date();
                var todayDateMonth = d.getMonth();
                var todayDateNum = d.getDate();

                // var map = {
                //       1:"January",
                //       2:"February",
                //       3:"March",
                //       4:"April",
                //       5:"May",
                //       6:"June",
                //       7:"July",
                //       8:"August",
                //       9:"September",
                //       10:"October",
                //       11:"November",
                //       12:"December"
                //     };
                //

                var

                $.each(data, function (index, day) {

                    var dayMonth = ;
                    var pic = day.;
                    var condition = ;
                    var high = ;
                    var low = ;

                    var row = '<tr>';
                        row += '<td>' + name + '</td>';
                        row += '<td>' + company + '</td>';
                        row += '<td><a onclick="showEditForm(' + id + ')">Edit</a></td>';
                        row += '<td><a onclick="deleteContact(' + id + ')">Delete</a></td>';
                        row += '</tr>';
                    contentRows.append(row);
                });
            },
            error: function() {
                $('#errorMessages')
                    .append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service.  Please try again later.'));
            }
        });

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

function showResults(){
  $('#results').show();
}
