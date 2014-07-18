<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="no" />

    <xsl:variable name="basePath" select="/files/@basedir" />

    <xsl:variable name="all-files">
        <xsl:for-each select="/files/file">
            <xsl:call-template name="file" />
        </xsl:for-each>
    </xsl:variable>

    <xsl:variable name="item-types">
        <xsl:for-each-group select="$all-files/file/items/itemtypes//itemtype" group-by="@code">
            <itemtype code="{current-grouping-key()}" />
        </xsl:for-each-group>
    </xsl:variable>

    <xsl:variable name="enum-types">
        <xsl:for-each-group select="$all-files/file/items/enumtypes/enumtype" group-by="@code">
            <enumtype code="{current-grouping-key()}" />
        </xsl:for-each-group>
    </xsl:variable>

    <xsl:template match="/">
        <html>
            <head>
                <title>Hybris Data Model</title>
            <style type="text/css">
                body { background-color:#E8E8E8; font-family:Arial; font-size:120%; }
                ul { margin-top:0px; padding-top:2px; }
                div { margin-bottom:4px; }
                .itemtype { background-color:#c0c0c0; }
                .enumtype { background-color:#ddb0b0; }
                .collectiontype { background-color:#b0ddb0; }
                .relation { background-color:#b0b0dd; }
                .maptype { background-color:#ddddb0; }
                .atomictype { background-color:#dddddd; }
            </style>
            </head>
            <body>
                <xsl:call-template name="item-types"/>
                <xsl:call-template name="enum-types"/>
                <xsl:call-template name="relations"/>
                <xsl:call-template name="collection-types"/>
                <xsl:call-template name="map-types"/>
                <xsl:call-template name="atomic-types"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="file" match="file">
        <xsl:variable name="filename" select="concat($basePath, .)" />
        <xsl:variable name="extname" select="replace(., '.*/([a-zA-Z0-9]*)-items.xml','$1')" />

        <file extension="{$extname}" name="{$filename}">
            <xsl:copy-of select="document($filename)" />
        </file>
    </xsl:template>

    <xsl:template name="item-types">
        <xsl:for-each select="$item-types/itemtype">
            <xsl:sort select="@code"/>
            <xsl:variable name="code" select="@code"/>
            <xsl:variable name="types" select="$all-files/file/items/itemtypes//itemtype[@code=$code]"/>
            <div class="itemtype">
                Item type <strong><a name="{$code}"><xsl:value-of select="$code"/></a></strong>
                <xsl:if test="$types/@extends">
                    <xsl:text> extends: </xsl:text>
                    <xsl:for-each select="$types/@extends">
                        <a href="#{.}"><xsl:value-of select="."/></a>
                        <xsl:text> </xsl:text>
                    </xsl:for-each>
                </xsl:if>
                <br/>
                <xsl:if test="$types/description">
                    <xsl:value-of select="$types/description"/>
                    <br/>
                </xsl:if>
                <xsl:variable name="children" select="$all-files/file/items/itemtypes//itemtype[@extends=$code]"/>
                <xsl:if test="$children">
                    <xsl:text>children: </xsl:text>
                    <xsl:for-each select="$children">
                        <a href="#{@code}"><xsl:value-of select="@code"/></a>
                        <xsl:if test="position() != last()">
                            <xsl:text>, </xsl:text>
                        </xsl:if>
                    </xsl:for-each>
                    <br/>
                </xsl:if>
                <xsl:if test="$types/deployment">
                    <xsl:text>table: </xsl:text>
                    <xsl:value-of select="$types/deployment/@table"/>
                    <xsl:text>, type code: </xsl:text>
                    <xsl:value-of select="$types/deployment/@typecode"/>
                    <br/>
                </xsl:if>
                <xsl:text>extensions: </xsl:text>
                <xsl:for-each select="$types">
                    <xsl:value-of select="ancestor::file/@extension"/>
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <br/>
                <xsl:variable name="relations" select="$all-files/file/items/relations/relation[sourceElement/@type=$code or targetElement/@type=$code]"/>
                <xsl:if test="$relations">
                    <xsl:text>relations: </xsl:text>
                    <xsl:for-each select="$relations">
                        <a href="#{@code}"><xsl:value-of select="@code"/></a>
                        <xsl:if test="position() != last()">
                            <xsl:text>, </xsl:text>
                        </xsl:if>
                    </xsl:for-each>
                    <br/>
                </xsl:if>
                <ul>
                    <xsl:for-each select="$types/attributes/attribute">
                        <xsl:sort select="@qualifier"/>
                        <li>
                            <strong><xsl:value-of select="@qualifier"/></strong>
                            <xsl:text>: </xsl:text>
                            <a href="#{@type}"><xsl:value-of select="@type"/></a>
                            <xsl:text> (extension: </xsl:text>
                            <xsl:value-of select="ancestor::file/@extension"/>
                            <xsl:text>)</xsl:text>
			                <xsl:if test="description">
			                    <br/>
                                <xsl:text>description: </xsl:text>
			                    <xsl:value-of select="description"/>
			                </xsl:if>
                            <xsl:if test="persistence">
                                <br/>
                                <xsl:text>persistence type: </xsl:text>
                                <xsl:value-of select="persistence/@type"/>
                            </xsl:if>
                            <xsl:if test="@qualifier">
                                <br/>
                                <xsl:text>qualifier: </xsl:text>
                                <xsl:value-of select="@qualifier"/>
                            </xsl:if>
                            <xsl:if test="defaultvalue">
                                <br/>
                                <xsl:text>default value: </xsl:text>
                                <xsl:value-of select="defaultvalue"/>
                            </xsl:if>
                            <!-- <modifiers read="false" write="false" search="false" initial="false" optional="true" partof="true"/> -->
                            <xsl:if test="modifiers[@read='false' or @write='false' or @search='true']">
                                <br/>
                                <xsl:text>modifiers: </xsl:text>
                                <xsl:if test="modifiers[@search='true']">
                                    <xsl:text>searchable </xsl:text>
                                </xsl:if>
                                <xsl:choose>
                                    <xsl:when test="modifiers[@read='false' and @write='false']">
                                        <xsl:text>disabled</xsl:text>
                                    </xsl:when>
                                    <xsl:when test="modifiers[@read='false']">
                                        <xsl:text>writeOnly</xsl:text>
                                    </xsl:when>
                                    <xsl:when test="modifiers[@write='false']">
                                        <xsl:text>readOnly</xsl:text>
                                    </xsl:when>
                                </xsl:choose>
                            </xsl:if>
                        </li>
                    </xsl:for-each>
                </ul>
            </div>
        </xsl:for-each>
    </xsl:template>

    <xsl:template name="enum-types">
        <xsl:for-each select="$enum-types/enumtype">
            <xsl:sort select="@code"/>
            <xsl:variable name="code" select="@code"/>
            <xsl:variable name="types" select="$all-files/file/items/enumtypes/enumtype[@code=$code]"/>
            <div class="enumtype">
                Enum type <strong><a name="{$code}"><xsl:value-of select="$code"/></a></strong>
                <br/>
                <xsl:if test="$types/description">
                    <xsl:value-of select="$types/description"/>
                    <br/>
                </xsl:if>
                <xsl:text>extensions: </xsl:text>
                <xsl:for-each select="$types">
                    <xsl:value-of select="ancestor::file/@extension"/>
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <br/>
                <ul>
                    <xsl:for-each select="$types/value">
                        <xsl:sort select="@code"></xsl:sort>
                        <li>
                            <strong><xsl:value-of select="@code"/></strong>
                            <xsl:text> (extension: </xsl:text>
                            <xsl:value-of select="ancestor::file/@extension"/>
                            <xsl:text>)</xsl:text>
                        </li>
                    </xsl:for-each>
                </ul>
            </div>
        </xsl:for-each>
    </xsl:template>

    <!-- <relation code="StoresForCMSSite" generate="true" localized="false" autocreate="true"> -->
    <!--     <deployment table="Stores4Site" typecode="1092" /> -->
    <!--     <sourceElement qualifier="cmsSites" type="BaseSite" cardinality="many" /> -->
    <!--     <targetElement qualifier="stores" type="BaseStore" cardinality="many" collectiontype="list" ordered="true" /> -->
    <!-- </relation> -->
    <xsl:template name="relations">
        <xsl:for-each select="$all-files/file/items/relations/relation">
            <xsl:sort select="@code"/>
            <div class="relation">
                Relation <strong><a name="{@code}"><xsl:value-of select="@code"/></a></strong>
                <br/>
                <xsl:call-template name="all-types"/>
                <ul>
                    <li>
                        <xsl:text>source: </xsl:text>
                        <strong><xsl:value-of select="sourceElement/@qualifier"/></strong>
                        <xsl:text>: </xsl:text>
                        <a href="#{sourceElement/@type}"><xsl:value-of select="sourceElement/@type"/></a>
                        <xsl:text> (</xsl:text>
                        <xsl:value-of select="sourceElement/@cardinality"/>
                        <xsl:text>)</xsl:text>
                        <br/>
                        <xsl:if test="sourceElement/@collectiontype">
                            <xsl:text>collectiontype: </xsl:text>
                            <xsl:value-of select="sourceElement/@collectiontype"/>
                            <br/>
                        </xsl:if>
                        <xsl:if test="sourceElement/@ordered">
                            <xsl:text>ordered: </xsl:text>
                            <xsl:value-of select="sourceElement/@ordered"/>
                            <br/>
                        </xsl:if>
                    </li>
                    <li>
                        <xsl:text>target: </xsl:text>
                        <strong><xsl:value-of select="targetElement/@qualifier"/></strong>
                        <xsl:text>: </xsl:text>
                        <a href="#{targetElement/@type}"><xsl:value-of select="targetElement/@type"/></a>
                        <xsl:text> (</xsl:text>
                        <xsl:value-of select="targetElement/@cardinality"/>
                        <xsl:text>)</xsl:text>
                        <br/>
                        <xsl:if test="targetElement/@collectiontype">
                            <xsl:text>collectiontype: </xsl:text>
                            <xsl:value-of select="targetElement/@collectiontype"/>
                            <br/>
                        </xsl:if>
                        <xsl:if test="targetElement/@ordered">
                            <xsl:text>ordered: </xsl:text>
                            <xsl:value-of select="targetElement/@ordered"/>
                            <br/>
                        </xsl:if>
                    </li>
                </ul>
            </div>
        </xsl:for-each>
    </xsl:template>

    <!-- <collectiontype code="UserCollection" elementtype="User" autocreate="true" generate="true"/> -->
    <!-- <collectiontype code="ProductCollection" elementtype="Product" autocreate="true" generate="true"/> -->
    <xsl:template name="collection-types">
        <xsl:for-each select="$all-files/file/items/collectiontypes/collectiontype">
            <xsl:sort select="@code"/>
            <div class="collectiontype">
                Collection type <strong><a name="{@code}"><xsl:value-of select="@code"/></a></strong>
                <br/>
                <xsl:call-template name="all-types"/>
                <ul>
                    <li>
                        <xsl:text>elementtype: </xsl:text>
                        <strong>
                            <a href="#{@elementtype}"><xsl:value-of select="@elementtype"/></a>
                        </strong>
                    </li>
                </ul>
            </div>
        </xsl:for-each>
    </xsl:template>

    <!-- <maptype code="localized:java.lang.String" argumenttype="Language" returntype="java.lang.String" autocreate="true" generate="false"/> -->
    <xsl:template name="map-types">
        <xsl:for-each select="$all-files/file/items/maptypes/maptype">
            <xsl:sort select="@code"/>
            <div class="maptype">
                Map type <strong><a name="{@code}"><xsl:value-of select="@code"/></a></strong>
                <br/>
                <xsl:call-template name="all-types"/>
                <ul>
                    <li>
                        <xsl:text>argument type: </xsl:text>
                        <strong>
                            <a href="#{@argumenttype}"><xsl:value-of select="@argumenttype"/></a>
                        </strong>
                    </li>
                    <li>
                        <xsl:text>return type: </xsl:text>
                        <strong>
                            <a href="#{@returntype}"><xsl:value-of select="@returntype"/></a>
                        </strong>
                    </li>
                </ul>
            </div>
        </xsl:for-each>
    </xsl:template>

    <!-- <atomictype class="java.lang.Object" autocreate="true" generate="false"/> -->
    <!-- <atomictype class="java.lang.Number" extends="java.lang.Object" autocreate="true" generate="false"/> -->
    <xsl:template name="atomic-types">
        <xsl:for-each select="$all-files/file/items/atomictypes/atomictype">
            <xsl:sort select="@class"/>
            <div class="atomictype">
                Atomic type <strong><a name="{@class}"><xsl:value-of select="@class"/></a></strong>
                <xsl:if test="@extends">
                    <xsl:text> extends: </xsl:text>
                    <a href="#{@extends}"><xsl:value-of select="@extends"/></a>
                </xsl:if>
                <br/>
                <xsl:call-template name="all-types"/>
            </div>
        </xsl:for-each>
    </xsl:template>

    <xsl:template name="all-types">
        <xsl:if test="description">
            <xsl:text>description: </xsl:text>
            <xsl:value-of select="description"/>
            <br/>
        </xsl:if>
        <xsl:if test="deployment">
            <xsl:text>table: </xsl:text>
            <xsl:value-of select="deployment/@table"/>
            <xsl:text>, type code: </xsl:text>
            <xsl:value-of select="deployment/@typecode"/>
            <br/>
        </xsl:if>
        <xsl:text>extension: </xsl:text>
        <xsl:value-of select="ancestor::file/@extension"/>
        <br/>
    </xsl:template>

</xsl:stylesheet>
