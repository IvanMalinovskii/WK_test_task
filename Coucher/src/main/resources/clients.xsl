<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text"/>

    <xsl:template match="client">
            <xsl:value-of select="client_id"/>/<xsl:value-of select="balance"/>
    </xsl:template>
</xsl:stylesheet>