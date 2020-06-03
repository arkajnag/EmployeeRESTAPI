# EmployeeRESTAPI
Implementing Representational State Transfer Protocol for building Employee API using JAXRS and Jersey and RestAssured for Client Testing.

There are different ways, we can create a Restful (Representational State Transfer Protocol) web service using JAX-RS () framework and Jersey API.

Let’s discuss, what are the basic needs to create a Server and its components.

We can use the basic web-app archetype/template for creating the skeleton of the Project. This will give us basic modularization of creating a backend application.
 

Another most important for running and testing your server, is to configure the Server.
Here, I have used Apache Tomcat 8.5 (Web Server and partial Application server) to host my Application.
Hosting port: 8888
Host: localhost or 127.0.0.1
 

As this is a Maven Project, hence we can utilize all the Build Management tool’s feature of managing compilation, modularizing profiles for creating respective artifacts for deployment, running expected tests and handling all required dependencies for the project.

Most important dependencies, we need for creating our Services & Resources:
  

media-moxy dependency will give us all the required APIs for parsing JSON.
jaxrs-ri will give us all the required dependencies needed to build a backend Restful WebService. 
Below are the set of jars that comes with jaxrs-ri package.
 


For proper modularization of project, I have broken down the structure into different short modules.
Each module has its own purpose to fulfil the need of the Project.
 
com.CarRental.Utilities: Provides us with all Utility classes that can help in reducing redundant common activities.
com.CarRental.Services: Provides us with actual implementation of Server Response. This is the layer where we actually construct and combine all our logic to return or execute certain response by Server to Client’s requests.
com.CarRental.Resources: Under this package, we create our Server endpoints and create and enable the service URLs and also customize the final response to client.
com.CarRental.Reporting: Provides us with different classes that helps in final test reporting.
com.CarRental.ModelClass: Under this package, we create the model classes (POJO) which defines structure of WebService. Starting from defining datatypes, variables, setters and getters, constructors and any specific methods for the class/object.
com.CarRental.JSONMapper: Under this package, we can create basic Client utility classes for marshalling and un-marshalling the JSON Object and POJO. This serialization and de-serialization classes can be implemented using different open source frameworks like JAXRS, GSON, Jackson, JSONSimple etc.
com.CarRental.Filters: Under this package, we define all the Filters & Interceptors, we would like to design for manipulating request/response headers or request/response body.
com.CarRental.ErrorHandler: Under this package, we define all our customized Exception Handling classes.
com.CarRental.ExceptionMapper: Under this package, we need to define all the ExceptionMapper classes for all the customized Exception Handling classes, we created. This mapper is required for mapping the resource response and overrides Tomcat’s own exception.
com.CarRental.DataSource: Under this package, we need to create our in memory Database or data source, when we don’t have any database to fetch and insert our data.
com.CarRental.Client: Under this package, we write our client code for connecting the server.
This can be implemented using different APIs and frameworks. RestAssured, HttpClient, JAX-RS etc.
Basic Requirement for building the project. Maven helps us in doing that.
 



We will design our small sample project using below JSON structure:
 

We have created three model classes, based on the above requirement:
 
When we are creating Restful APIs without using web.xml configuration and without configuring servlet-name and servlet-mapper, then we need to create a dedicated resource class, which will extend Application class and define a Decorator (@ApplicationPath(“ ”)) for creating part of HostURL.

 

In normal resource class, we will define ServiceURL and response.
 
There are different annotations are used to specify certain functionalities which we want our code to follow. These annotations are similar behavior to Decorators in Python. Associating new rules or protocol on your function by declaring with Annotations or Decorators.
@Path (“<resource-path>”): This signifies the service URL for particular WebService call. Generally when declared above the class, it signifies as Global path annotation, whereas when defined above any specific method, then global + local path gets amended.
@Consumes (“<MediaType>”): This signifies what format or type, the request will be accepted by Server from Client. We can define locally as well global. Multiple MediaType(s) can be declared based on need.
@Produces (“<MediaType>”): This signifies what format or type, Server is going to send the response to Client.  We can define locally as well global. Multiple MediaType(s) can be declared based on need.
@GET, @POST, @PUT, @PATCH, @DELETE, @HEAD: These annotations signifies what http verb, a particular method to be recognized.
When a request is presented by Client, then request will navigate using the @Path and then will match the Http Verb annotations, if matched, execute particular method and sends back the response.
@QueryParam: This behaves same as where clause in SQL. It helps in creating a Service URL with pagination parameters, where the return objects can be single or multiple based on type of parameter set.
 
Over here, schoolname is designed as QueryParam and this will be included in ServiceURL as below:
 
We can fetch QueryParam value either using @QueryParam as method argument as well as we can get it from @Context annotation.
 



@PathParam: This helps to create a ServiceURL which generally returns single record of information/object.
 
This path gets amended with main resource URL.
 

GitHub Link: https://github.com/arkajnag/BasicRestClient.git









Filters help in manipulating certain part of the Request and Response.
Mainly used for adding specific headers in Response or put Authentication Process when client triggers any request.

Implements two types of Interfaces: ContainerRequestFilter and ContainerResponseFilter.

ContainerResponseFilter has one Abstract Method: filter
 

Implementing Basic Authentication Using ContainerRequestFilters:
 

