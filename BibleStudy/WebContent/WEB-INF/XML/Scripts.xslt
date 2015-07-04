<xsl:stylesheet version="1.0"
       xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template name="scripts">
  
  

<script src="resources/script/jquery-2.1.4.min.js"></script>
<script src="resources/script/jquery-ui.min.js"></script>
<script src="resources/script/jquery.ui.touch-punch.min.js"></script>
<script src="resources/script/bootstrap.min.js"></script>

<script>
<![CDATA[
var pathname = window.location.pathname; 

$(document).ready(function(){

	$( window ).scroll(function() {
	 $('a.note').tooltip('hide'); 
	 console.log("scroll");
	});
	
	$("a.verse").click(function(){
			$("a.clickable").addClass("in");
			$("a.clickable").attr("aria-expanded","true");	
			$("#collapseOne").addClass("in");
			$("#collapseOne").css("display","block");
			$("#collapseOne").css("height","");
			$("#collapseOne").attr("aria-expanded","true");
			
	});
	
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

			   $('a.note').tooltip({ html: true, placement: "bottom"}); 
			 
		});
	});
	
	
	  $("#versesPanel").draggable({
	    
	  });
	
	$( window ).resize(function() {
  		$( "#versesPanel" ).removeAttr( 'style' );
  	
	});

});
]]>

</script>
  
  
  </xsl:template>
  
  </xsl:stylesheet>
