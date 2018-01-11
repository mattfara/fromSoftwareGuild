// home js

$(document).ready(function () {
    $('#pageContent').children().not('#mainInfoDiv').hide();
});

$('#akronButton').on('click', function (){
  $('#akronInfoDiv').show();
  $('#pageContent').children().not('#akronInfoDiv').hide();
  $('#akronWeather').hide();
});

$('#minneapolisButton').on('click', function (){
  $('#minneapolisInfoDiv').show();
  $('#pageContent').children().not('#minneapolisInfoDiv').hide();
  $('#minneapolisWeather').hide();
});

$('#louisvilleButton').on('click', function (){
  $('#louisvilleInfoDiv').show();
  $('#pageContent').children().not('#louisvilleInfoDiv').hide();
  $('#louisvilleWeather').hide();
});

$('#akronWeatherButton').on('click', function() {
  $('#akronWeather').show();
});

$('#minneapolisWeatherButton').on('click', function() {
  $('#minneapolisWeather').show();
});

$('#louisvilleWeatherButton').on('click', function() {
  $('#louisvilleWeather').show();
});

$('tr:not(:first-child)').hover(

  function(){
    $(this).css('background-color', 'WhiteSmoke');
  },
  function(){
    $(this).css('background-color', '');
  }
);
/*
Page Load
  Only the content in the Main section should display when the page is loaded.*
Navigation Button Behavior
  When the Akron button is clicked, only the content in the Akron section should display; the weather information for Akron should be hidden initially.
  When the Minneapolis button is clicked, only the content in the Minneapolis section should display; the weather information for Minneapolis should be hidden initially.
  When the Louisville button is clicked, only the content in the Louisville section should display; the weather information for Louisville should be hidden initially.
Show/Hide Weather Behavior
  When the Show/Hide Weather button is clicked, the page should display the associated weather information if it was hidden or hide the associated weather information if it was showing. It should default to hidden.
Table Row Behavior
  The background color of any table row should change to “WhiteSmoke” when the mouse pointer is hovering over the row.
  The background color of the row should return to white when the mouse pointer is no longer hovering over the row.
  This applies to all rows in all tables except the first (header) row in a given table. The first (header) row in any table should not change appearance when the mouse pointer hovers over it.
*/
