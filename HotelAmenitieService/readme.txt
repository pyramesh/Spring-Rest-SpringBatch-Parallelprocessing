Application: Hotel Room Amenities
Purpose : Exposed REST services to manage the resources

********************************************************************

Technologies used for the project
***************************************************
Spring Boot
REST
Hibernate/JPA
Spring data repository
Spring Batch
Swagger API

non-functional
--------------------------------
Swagger documentation
Spring Transaction management
Exception Handling
Logger
SPring Junit test cases

Build
------------------
Maven

Package Stracture
******************************************************
src/main/java
---------------
com.ramesh.controller --> Controller
com.ramesh.service --> Service Layer classes
com.ramesh.repository --> Repository classes
com.ramesh.domain --> JPA Entity classes
com.ramesh.exception --> Exception classes

com.ramesh.batch* ---> Spring batch related classes

src/test/java
-------------
com.ramesh.controller -> test cases


Access the Swaggar UI
--------------------------------
http://localhost:9999/swagger-ui.html

Spring Batch Controller
-------------------------------
http://localhost:9999/importfile/