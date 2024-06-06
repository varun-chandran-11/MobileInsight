Basic Information:   
Database Used—MongoDB Atlas—The details for connecting to the database are in the application.properties. Robo3T can also be used to connect to the database.  
Documenting - Implemented using Open API 3.0. You can get the details from http://localhost:8080/api-docs. 
Swagger Page can be accessed using the URL http://localhost:8080/mobile-insight-swagger-ui.html. 
A Docker container is being used in this application.  
Auditing—A model-level audit has been added for the UserDetails class. I have also created a Trigger in MongoDB Atlas to enter the audit details into a database ‘audit_db’ and collection ‘audit_logs’.  
Config Server - Implemented and successfully tested locally. However, the application properties were reverted to work with Docker because of time constraints.  
Postman Collection - Included within the code.  
IDE used - IntelliJ IDEA. 



My thought process in Service Design.  
1. Initially, I spent some time understanding the requirements.  
2. I then tried to come up with the database design and decided on the indexes. I thought about building relationships between the NoSQL collections but haven’t worked on such a thing before. So, I decided to use an approach where the foreign key will be treated as a string. I can keep the entries without nesting them with relationships, as we expect millions of entries.  
3. I then decided which fields (especially the PII data - in this case, mdn and nam; need to be encrypted (currently, I have only encoded them). I always prefer that any PII be encrypted and stored in the database.  
4. Next, I decided on the layers I would use. I decided to go ahead with the standard controller, service, and repo layers approach. I also decided to use custom exceptions for exception handling.  
5. I created the spring application using the spring initializer within the IntelliJ IDEA and included the required dependencies. I started making the classes, starting with the model classes.   
6. I then separated the Controller classes into two types: one where we do the insertion and modification of data and the other controller where we do the query (for following CQRS).  
7. Next, I created the Service and the repository layers. Next, I connected with MongoDB Atlas and successfully tested some DB operations.   
8. I added a DTO class for use with the GET methods. For the POST methods, I used the models themselves. However, I handled the visitability using a View class that I created.  
9. Since there would be millions of data, I wanted to ensure that the GET methods have implemented pagination.  
10. Unit testing is implemented with success and failure scenarios.  


Improvements I like to make  
1. While working on the database and indexing, I came across @DocumentReference. I prefer to use this to build relationships between 2 collections. This would help Lazy load the data.  
2. A robust encryption mechanism can be included within the application to store the PII data at rest.  
3. Configuration can be moved to a config server, which gets the data from the repo. Also, a vault can be implemented to store essential data securely so that GitHub doesn’t become a point of data leak.  
4. Test coverage can be improved.  
