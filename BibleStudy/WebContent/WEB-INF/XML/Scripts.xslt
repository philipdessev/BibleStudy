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
	 	console.log(this.href.split("?")[1]);
	
			 	$.getJSON( "getVerses?"+ this.href.split("?")[1] , function(data ) {
					  //var items = [];
					 // console.log(data);
					  $.each( data, function( key, val ) {
					  
					  
					    alert(val.OSISID + ": "+ val.text)
					 
					  });
					 
					  /*
					  $( "<ul/>", {
					    "class": "my-new-list",
					    html: items.join( "" )
					  }).appendTo( "body" );
					  */
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