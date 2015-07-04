<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:import href="Scripts.xslt" /> 
<xsl:param name="editable" select="true"/>

<xsl:output method="html"
               doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
               doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"/>
                          
<xsl:template match="/">
<html>
<head>

  <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>
   

    <title>Starter Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="resources/css/common.css" rel="stylesheet"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
  <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#"><xsl:value-of select="document('Bulgarian.xml')/text/projectname"/></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#"><xsl:value-of select="document('Bulgarian.xml')/text/home"/></a></li>
            <li><a href="#about"><xsl:value-of select="document('Bulgarian.xml')/text/account"/></a></li>
            <li><a href="#contact"><xsl:value-of select="document('Bulgarian.xml')/text/contact"/></a></li>
            <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><xsl:value-of select="document('Bulgarian.xml')/text/my_studies"/> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li class="dropdown-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
   
<div class="container">
       
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-7 col-lg-9">  
			<xsl:apply-templates/>
		</div>
	</div> 


		  <div id ="versesPanel" class="panel panel-primary versesPanel ui-widget-content" style="background-color:#f9f9f9">
		        <div id="versesPanelHeading" class="panel-heading panel-title">
			        <a data-toggle="collapse" data-target="#collapseOne" href="#collapseOne" aria-expanded="false" class="clickable">
			       		<xsl:value-of select="document('Bulgarian.xml')/text/verses"/>	       		
			        </a>        
		        </div>
		        
		         <div id="collapseOne" class="panel-collapse collapse" aria-expanded="false">
		        		<div id="verses" class="panel-body verses"></div>
		        </div>
		  </div>
	</div>


<xsl:call-template name = "scripts"/>
</body>
</html>
</xsl:template>




<xsl:template match="title">
<hr/>
<h2>
<xsl:apply-templates/>
</h2>
</xsl:template>

<xsl:template match="subtitle">
<h4>
<xsl:apply-templates/>
</h4>
<hr/>
</xsl:template>


<xsl:template match="meta"></xsl:template>

<xsl:template match="question">
<hr/>
<div class="well">
<xsl:apply-templates/>
</div>
</xsl:template>
<xsl:template match="instructions">
<div>
<xsl:apply-templates/>
</div>
</xsl:template>
	<xsl:template match="answer">
		<div>
			<textarea name ="{@id}" id="{@id}" class="form-control" rows="3" style="width:80%"></textarea> 
		</div>
	</xsl:template>

	<xsl:template match="table">
		<div class="table-responsive">
  			<table class="table">
				<xsl:apply-templates />
			</table>
		</div>
	</xsl:template>


	<xsl:template match="tr">
		<tr>
			<xsl:apply-templates />
		</tr>
	</xsl:template>


	<xsl:template match="td">
		<td width="33%">
			<xsl:apply-templates />
		</td>
	</xsl:template>


	<xsl:template match="b">
		<b>
			<xsl:apply-templates />
		</b>
	</xsl:template>

	<xsl:template match="i">
		<i>
			<xsl:apply-templates />
		</i>
	</xsl:template>

	<xsl:template match="u">
		<u>
			<xsl:apply-templates />
		</u>
	</xsl:template>
	
		<xsl:template match="verse">
		<xsl:text> (</xsl:text><a class="verse">
		<xsl:attribute name = "href">
			<xsl:text>http://localhost:8080/getVerseContent</xsl:text>
			<xsl:text>?book=</xsl:text><xsl:value-of select="@osis"/>
			<xsl:text disable-output-escaping="yes">&#38;</xsl:text><xsl:text>chapter=</xsl:text><xsl:value-of select="@chapter"/>
			<xsl:text disable-output-escaping="yes">&#38;</xsl:text><xsl:text>number=</xsl:text><xsl:value-of select="@number"/>
			<xsl:text disable-output-escaping="yes">&#38;</xsl:text><xsl:text>seq=</xsl:text><xsl:value-of select="@seq"/>
			<xsl:text disable-output-escaping="yes">&#38;</xsl:text><xsl:text>list=</xsl:text><xsl:value-of select="@list"/>
		</xsl:attribute>
		
		<xsl:value-of select="@book"/><xsl:text> </xsl:text>
		<xsl:value-of select="@chapter"/>
		<xsl:if test="@number !='' or @list != '' or (@seq != '' and  @seq != 'all')">
			<xsl:text>:</xsl:text>
		</xsl:if>
		
		
		<xsl:value-of select="@number"/><xsl:value-of select="@list"/>
		<xsl:if test="@seq != 'all'">
			<xsl:value-of select="@seq"/>
		</xsl:if>
		
		</a><xsl:text>) </xsl:text>
	</xsl:template>

	<xsl:template match="hi">
		<i>
			<xsl:apply-templates/>
		</i>
	</xsl:template>

</xsl:stylesheet>
