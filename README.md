# For Running the app on local

### Recommended IDE and JDK version- 
   Intellij Idea and JDK 11 

### Use of Maven Wrapper
Generate necessary files for Maven Wrapper running from IDE (optionally provide version)
```
mvn wrapper:wrapper 
``` 

### Run the below command inside the project folder: (from terminal)

```
./mvnw clean package
```
For Windows machine
```
mvnw.cmd clean package
```
It will generate a target folder with application jar in it

## RUN LOCALLY (NON-CONTAINERISED VERSION)
### Run locally without containerised version

We can run locally (Require Mongo DB and Java  to be installed) running following command

```
./mvnw spring-boot:run
```
## RUN CONTAINERISED VERSION
### Run the below command to tag and build the docker image

```
docker build -t <app-name>:<tag-name> .
```
e.g. 
```
docker build -t theaterservice:1.0.0 .
```

### Create network
```
docker network create theater-net
```
### Run Mongo DB as container
```
docker run -d --name mongodb -p 27017:27017 --net theater-net mongo:4.4
```

### To run the app on your local docker instance execute below command
```
docker run -d --name <container-name> -p 8082:8082 <image-name>:<tag-name>
```


## Step 4: Now you can access the app on your browser or rest client on localhost:<App Port>. Sample endpoint is 
```
POST: localhost:8082/v1/defineShows
```

# API Documentation
### API Documentation can be browsed and executed locally after running the app at the below URL [ Mind your App running Port]
```
http://localhost:8080/swagger-ui.html
```
# Run locally using Docker Compose
### Run below commands
```
docker compose build
```
```
docker compose -f docker-compose.yml up -d
```
### Important: Please note in Mac M1 when logging into the app container, it is showing cannot connect to mongo db. However after few seconds it works fine, although depends on is present in the docker-compose file

# Postman collection
### Postman collection can be imported in Postman. There is no specific Environment file. The collection is present in the below project source code folder
```
postman_collection
```

# Helm Charts
### Helm charts are inside below folder. It has to be configured as per need

```
charts/theater_app
```