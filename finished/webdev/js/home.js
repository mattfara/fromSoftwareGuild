$(document).ready(function () {
      $('H1').addClass('text-center');
      $('H2').addClass('text-center');
      $('H1').removeClass('myBannerHeading').addClass('page-header');
      $('#yellowHeading').text('Yellow Team');
      $('#orangeTeamList').css('background-color', 'orange');
      $('#blueTeamList').css('background-color', 'blue');
      $('#redTeamList').css('background-color', 'red');
      $('#yellowTeamList').css('background-color', 'yellow');
      $('#yellowTeamList').append('<li>Joseph Banks</li>');
      $('#yellowTeamList').append('<li>Simon Jones</li>');
      $('#oops H1').hide();
      $('#footerPlaceholder').remove();
      $('#footer').append("<p id='forSize'>Matt Farabaugh, mattfarabaugh50@gmail.com</p>");
      $('#forSize').css({'font-size':'24px','font-family':'Courier'});


});

/*
Center all H1 elements*
Center all H2 elements*
Replace the class that is on the element containing the text “Team Up!” with the class page-header*
Change the text of “The Squad” to “Yellow Team”*
Change the background color for each team list to match the name of the team*
Add Joseph Banks and Simon Jones to the Yellow Team list*
Hide the element containing the text “Hide Me!!!”*
Remove the element containing the text “Bogus Contact Info” from the footer*
Add a paragraph element containing your name and email to the footer. The text must be in Courier font and be 24 pixels in height

*/
