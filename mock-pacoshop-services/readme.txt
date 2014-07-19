------------------------------------------------------------------------------
Application URLs

1) servicebus
    /mock-pacoshop-services/rest/servicebus/single
    /mock-pacoshop-services/rest/servicebus/subscription

2) sso
    /mock-pacoshop-services/rest/sso
    /mock-pacoshop-services/rest/sso/readAccount?UserID=&wsToken=

examples:
    http://127.0.0.1:8080/mock-pacoshop-services/rest/sso/readAccount?UserID=U001&wsToken=token1
    ...
    http://127.0.0.1:8080/mock-pacoshop-services/rest/sso/readAccount?UserID=U014&wsToken=token1

3) uag
    /mock-pacoshop-services/uag.jsp


------------------------------------------------------------------------------
Configuration

1. Default configuration are placed inside file mock-pacoshop-default.properties
If user want to overwrite them, he should place to classpath file mock-pacoshop.properties that takes precedence over default.

As for tomcat, it is tomcat/lib folder

------------------------------------------------------------------------------