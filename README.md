# Bookstore App
The application is created for a Case Study.
## Run
Open terminal in project `root` folder. 
Start the application with `docker-compose up` command.
This will launch three containers and start the webserver. 
The application will start on  http://localhost:8080/

## Start
You can access **MongoDB** admin interface with http://localhost:8081/
Database will be created after first request. If database is not created automatically, create database by using** Mongo Express**

After application successfully build;
You must create new user to test the application due to enpoints is secured with JWT.

1. Sent POST request to ` http://localhost:8080/api/v1/customer/register` with body below.

        "userName" : "test1",
        "password" : "123456",
        "firstName" : "testname1",
        "lastName" : "testlastname2",
        "email" : "test1@mail.com",
        "phone" : "+90543210000",
        "roles" : ["ROLE_CUSTOMER", "ROLE_USER"] 

2. After registered successfully, Sent POST request to `http://localhost:8080/api/v1/customer/login` with body below.

        "userName" : "test1",
        "password" : "123456"
3.  After login successfully you get security token. Use this token for further request.

## API Documentation
Postman: https://documenter.getpostman.com/view/9528068/UVeDrSCh
Swagger: http://localhost:8080/swagger-ui/index.html
Raw Data http://localhost:8080/v3/api-docs/

