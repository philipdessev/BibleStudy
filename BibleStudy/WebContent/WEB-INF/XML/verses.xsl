<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" omit-xml-declaration="yes" indent="no"/>
     
	<xsl:template match="/">
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="verse">
		<span>
			<xsl:attribute name="class">listverse</xsl:attribute>
			<xsl:attribute name="osisID"><xsl:value-of select="./@osisID"/></xsl:attribute>
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	
	
	<xsl:template match="note[@type = 'footnote']">
		<a style="font-weight:bold; color:#ff0000" class="note" data-container="body">
		<xsl:attribute name="title">
			<xsl:apply-templates/>
		</xsl:attribute>
		<xsl:text> (*) </xsl:text>
		</a>
	</xsl:template>
	

	<xsl:template match="hi[@type = 'italic']">
		<xsl:text> </xsl:text>
			<i>
					<xsl:apply-templates/>
			</i>
		<xsl:text> </xsl:text>
	</xsl:template>

</xsl:stylesheet>


