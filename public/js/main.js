require(["order!libs/jquery-1.6.2.min",
         "order!libs/underscore-1.1.7.min",
         "order!libs/modernizr-2.0.min",
         "order!libs/jquery-ui-1.8.15.custom.min"], function() {
   $(document).ready(function() {
      var url = "";

      $("#fullText-check").click(function() {
         $.ajax({
            type: "GET",
            url: url + "/fullTexts",
            dataType: 'json',
            success: function(fullTexts) {
               $("#fullText-list").append(fullTexts[0].title + " " + fullTexts[0].fullText);
            },
            error: function() {
               $("fullText-list").html("error");
            }
         });
      });

      $("#fullText-save").click(function() {
         $.ajax({
	    type: "POST",
	    url: url + "/fullText",
	    dataType: 'json',
	    data: {title: $("#fullText-title").val(), fullText: $("#fullText-fullText").val(), source: $("#fullText-source").val()},
	    success: function(msg) {
               alert( "Data Saved: " + msg );
            }
	});

         return false;
      });
   });
});
