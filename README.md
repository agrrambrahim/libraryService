# libraryService
the service is published under the following path /libraryservice/search?=input
ex: 
http://localhost:8080/libraryservice/search?input=moon

To run this app for the first time : 

1) Create a new database in your SGDB
2) Setup database properties in application.properties files (database name, url, username, password, driver-class-name,...)
3) Change spring.jpa.hibernate.ddl-auto property to create
4) Create your proper Google books API KEY in https://console.developers.google.com/apis/credentials/key/
5) Update the configuration files with the Google & iTunes API properties

To visualise metrics, please download :
https://prometheus.io/docs/introduction/first_steps/

Or go directly to Actuator management endpoints = http://localhost:8080/manage

user.name=monitor
password=Monitor@123
