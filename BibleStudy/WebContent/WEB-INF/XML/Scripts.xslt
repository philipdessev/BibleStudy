<xsl:stylesheet version="1.0"
       xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template name="scripts">
  
  
<script src="resources/script/jquery-1.11.3.min.js"></script>
<script src="resources/script/bootstrap.min.js"></script>


<script>
<![CDATA[
var pathname = window.location.pathname; 

$(document).ready(function(){

	$("a.verse").click(function(e){
	 	e.preventDefault();
	 	 $("div.verses").html("");
	 	console.log(this.href.split("?")[1]);
	
			 	$.getJSON( "getVerses?"+ this.href.split("?")[1] , function(data ) {
					  //var items = [];
					 // console.log(data);
					  $.each( data, function( key, val ) {
					    $("div.verses").append("<div id="+ val.OSISID +">" + val.OSISID.split(".")[2] + ". " + val.text + "</div>");
					  });
					 
				});
	 	
/*
	 	
	    $.get("getVerses", function(data, status){
	        alert("Data: " + data + "\nStatus: " + status);
	    });
  */ 
	});

});
]]>

</script>
  
  
  </xsl:template>
  
  </xsl:stylesheet>