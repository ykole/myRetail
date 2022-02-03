# myRetail

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 

Build an application that performs the following actions
•	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
Example product IDs: 13860428, 54456119, 13264003, 12954218)

•	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}

•	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail) 

•	Example: 
https://redsky-uat.perf.target.com/redsky_aggregations/v1/redsky/case_study_v1?key=key&tcin=13860428

•	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. 

•	Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 



**Technology Stack:**

Spring Boot : https://start.spring.io/

MongoDB: https://www.mongodb.com/try/download/community

Maven: https://maven.apache.org/

Postman: https://www.postman.com/

**Set up instruction**

In Eclipse or any other development IDE for java, Import as Maven Project, either build manually or by default auto build is enabled. I have used spring boot application, when the maven build is done, simply run as Java Application on the main class. 

Download & install MongoDB community edition & compass on local machine. Start MongoDB on localhost on port 27017. Create Products database and ProductPrice collection.

Update api end point, api key name & value in application.properties file in Java project.

**Start Application**


1) Run Java Application from IDE.
2) Add ProductPrice collection in MongoCompass and add below collections.

    {"_id":{"$numberLong":"13860428"},"cost":"23.80","currencyCode":"USD"}
    
    {"_id":{"$numberLong":"12954218"},"cost":"5.49","currencyCode":"USD"}
    
    {"_id":{"$numberLong":"13264003"},"cost":"6.85","currencyCode":"USD"}
    
    or
    
 3) Open Postman and run individual Post API call http://localhost:8080/myRetail/products/ for each of below json data.
 
       {"id": 13860428,"current_price":{"value": 23.80,"currency_code": "USD"}}
       
       {"id": 12954218,"current_price":{"value": 5.49,"currency_code": "USD"}}
       
       {"id": 13264003,"current_price":{"value": 6.85,"currency_code": "USD"}}
     
       <img width="610" alt="PostAPI" src="https://user-images.githubusercontent.com/96628061/152403545-16014710-0f34-4417-9f1d-d33bf18798c9.png">
       
       
   3) Run GET API call http://localhost:8080/myRetail/products/ from Postman for sample product ID (13860428, 13264003, 12954218).
      Result will be displayed only if Product title is retrieved from third party API and product price is retrieved from ProductPrice collection. If product id is not available in MongoDB  it wil return 404 and if not retrieved sucessfully from third party API it will return 500.
      
      
      <img width="457" alt="GETAPI" src="https://user-images.githubusercontent.com/96628061/152404432-bd7dd9cc-f9b9-41dd-8317-5fc8fffff639.png">


       <img width="571" alt="GETFailure" src="https://user-images.githubusercontent.com/96628061/152405342-5e3af7e4-5dcc-45ba-9c67-c40b9b83f636.png">

      
  4) Run PUT API call http://localhost:8080/myRetail/products/ from Postman for sample product ID (13860428, 13264003, 12954218)

      If product is available in MongoDB, price will be updated in collection.
      
      
        <img width="506" alt="POSTAPISuccess" src="https://user-images.githubusercontent.com/96628061/152406781-36da6c7b-c058-4833-b9c9-e45a081ac9d5.png">
        
        if product value sent as negative or 0, API sends 406 Not acceptable response.
        
        <img width="665" alt="PUTFailure" src="https://user-images.githubusercontent.com/96628061/152416660-ec705745-67c2-4b0c-8e4c-832d30d8609d.png">

        If proct value not sent, API sends 406 response
        
        <img width="670" alt="PutFailureEmpty" src="https://user-images.githubusercontent.com/96628061/152417478-cc8e3251-64e3-4886-a8ca-88c8eb2cb7fa.png">


        

   


