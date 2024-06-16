Basic Information:   
Database Used—MongoDB Atlas—The details for connecting to the database are in the application.properties. Robo3T can also be used to connect to the database.  
Documenting - Implemented using Open API 3.0. You can get the details from http://localhost:8080/api-docs. 
Swagger Page can be accessed using the URL http://localhost:8080/mobile-insight-swagger-ui.html. 
A Docker container is being used in this application.  
Auditing—A model-level audit has been added for the UserDetails class. I have also created a Trigger in MongoDB Atlas to enter the audit details into a database ‘audit_db’ and collection ‘audit_logs.’  
Config Server - Implemented and successfully tested locally. However, the application properties were reverted to work with Docker because of time constraints.  
Postman Collection - Included within the code.  
IDE used - IntelliJ IDEA. 



This project was part of the test requirement for one of the companies I interviewed for. As of today, I haven't included the spring security module in this.  
My next target will be to enhance this project to depict my skills with different tech stacks.

