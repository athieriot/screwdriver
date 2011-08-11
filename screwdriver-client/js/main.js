require(["order!libs/jquery-1.6.2.min",
         "order!libs/underscore-1.1.7.min",
         "order!libs/modernizr-2.0.min",
         "order!libs/jquery-ui-1.8.15.custom.min"], function() {
   $(document).ready(function() {
      $("#idea-save").button().click(function() {
	$.ajax({
	   type: "POST",
	   url: "http://localhost:3000/idea",
	   data: {title: $("#idea-title").val(), data: $("#idea-message").val()},
	   success: function(msg){
	        alert( "Data Saved: " + msg );
	      }
	});
      });
   });
});
