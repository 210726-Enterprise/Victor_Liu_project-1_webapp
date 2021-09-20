# Project 1 Webapp

## Project Description

A custom web application to demo my custom ORM found here - https://github.com/210726-Enterprise/Victor_Liu_p1_ORM.

## Technologies Used

* Java 8
* Java EE Servlet API
* Jackson Library
* Apache Maven
* JUnit 5
* Mockito
* Tomcat 8.5
* Postman

## Features

* Endpoints for all CRUD operations (Create, Read, Update, and Delete)

## Getting Started
   
This project uses Tomcat 8.5 to locally host the server on http://localhost:8080/Revagames. Please make sure you have this installed beforehand.

1. To start, please clone the project - git clone https://github.com/210726-Enterprise/Victor_Liu_project-1_webapp.git
2. Update the application.properties file with the url and credentials of the database you'd like to use
  - You will need to rebuild the database tables yourself, as my ORM does not yet support table creation. Please reference VideoGame.java under Models for this.
3. Configure your Tomcat server. The defaults should have the context path as /RevaGames and the server port on 8080. You may change these if you'd like, just replace any future instructions with your context path and port.
![Screenshot (148)](https://user-images.githubusercontent.com/23224121/133951244-7366869f-ad48-452f-8773-e118ef6eeeb6.png)
4. Run the application using your Tomcat configuration. Make sure that the dropdown box in the top right says "Tomcat 8.5" and then click the green run button immediately next to it.

The servlet should now be running. See "Usage" on how to interact with it.

## Usage

We will be communicating with our endpoints using Postman. Input the request URL as http://localhost:8080/RevaGames/videogames. GET, POST, PUT, and DELETE requests are all supported on this endpoint. For POST and PUT requests, add a JSON body of a video game object including the id, name, developer, publisher and genre. For delete requests, add request parameter for the id.
